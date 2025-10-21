package edu.bsu.cs;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class TerminalMain {
    TerminalController terminalController = new TerminalController();
    FileController fileController = new FileController();
    WeatherServiceAPI api = new WeatherServiceAPI();
    APIDataParser dataParser = new APIDataParser();
    FormatData dataFormatter = new FormatData();
    ArrayList<Pair> locations;
    String[] preferences;

    //TODO: separate into main with preferences and main without
    static void main() throws IOException {
        TerminalMain tm = new TerminalMain();

        //TODO: remove
        tm.resetPreferences();

        tm.locations = tm.fileController.loadCities();
        tm.preferences = tm.fileController.loadPreferences();
        InputStream weatherData;
        boolean keepGoing = true;

        tm.terminalController.printWelcomeMessage();

        while (keepGoing) {
            if (tm.preferences[0].equals("true")) {
                weatherData = tm.getInitialWeatherDataFromPreferences();
            } else {
                String response = tm.terminalController.getPreferencePreferences();
                if (response.equals("true")) {
                    tm.preferences = tm.getPreferences();
                    tm.fileController.savePreferences(tm.preferences);
                    weatherData = tm.getInitialWeatherDataFromPreferences();
                } else {
                    weatherData = tm.getInitialWeatherData();
                }
            }
            String forecastType = tm.terminalController.getForecastType();
            if (forecastType.equalsIgnoreCase("hourly")) {
                tm.tempGetHourlyForecast(weatherData);
            } else if (forecastType.equalsIgnoreCase("daily")) {
                tm.tempGetDailyForecast(weatherData);
            }
            keepGoing = !tm.terminalController.isUserDone();
        }
        //TODO: remove
        tm.resetPreferences();
    }

    private String[] getPreferences(){
        String location = terminalController.getLocationPreference(this.locations);
        String units = terminalController.getUnitPreference();
        return new String[]{"true", location, units};
    }

    private void resetPreferences() throws IOException {
        fileController.savePreferences(new String[] {"false","muncie, in","imperial"});
    }

    private InputStream getInitialWeatherDataFromPreferences() throws IOException {
        String link = api.createURLString(this.preferences[2]);
        return api.getConnectionFromURL(link).getInputStream();
    }

    private InputStream getInitialWeatherData() throws IOException {
        String latLong = terminalController.getLocationPreference(this.locations);
        String link = api.createURLString(latLong);
        return api.getConnectionFromURL(link).getInputStream();
    }

    private void tempGetHourlyForecast(InputStream weatherData) throws IOException {
        String hourlyForcastURLString = this.dataParser.parseWeatherAPILink(weatherData, "forecastHourly");
        InputStream hourlyForecastData = this.api.getConnectionFromURL(hourlyForcastURLString).getInputStream();
        this.dataParser.setWeatherData(hourlyForecastData);
        this.dataParser.forecastData();
        HashMap<String, ArrayList<String>> hourlyForecast = this.dataParser.getDailyForecast();
        int i;
        for (i=1;i<7;i++){
            ArrayList<String> forecast = hourlyForecast.get(Integer.toString(i));
            System.out.println(this.dataFormatter.formatWeatherData(forecast));
        }
    }

    private void tempGetDailyForecast(InputStream weatherData) throws IOException {
        String forecastURLString = this.dataParser.parseWeatherAPILink(weatherData, "forecast");
        InputStream forecastData = this.api.getConnectionFromURL(forecastURLString).getInputStream();
        this.dataParser.setWeatherData(forecastData);
        this.dataParser.forecastData();
        HashMap<String, ArrayList<String>> dailyForecast = this.dataParser.getDailyForecast();
        ArrayList<String> actualArray2 = dailyForecast.get("1");
        System.out.println(actualArray2);

    }
}
