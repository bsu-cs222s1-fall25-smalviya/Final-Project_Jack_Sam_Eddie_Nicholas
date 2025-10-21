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

        tm.terminalController.printWelcomeMessage();

        if (tm.preferences[0].equals("true")){
            weatherData = tm.getInitialWeatherDataFromPreferences();
        } else {
            String response = tm.terminalController.getPreferencePreferences();
            if (response.equals("true")){
                tm.preferences = tm.getPreferences();
                tm.fileController.savePreferences(tm.preferences);
                weatherData = tm.getInitialWeatherDataFromPreferences();
            } else {
                weatherData = tm.getInitialWeatherData();
            }
        }

        //hourly forecast
        String hourlyForcastURLString = tm.dataParser.parseWeatherAPILink(weatherData, "forecastHourly");
        InputStream hourlyForecastData = tm.api.getConnectionFromURL(hourlyForcastURLString).getInputStream();
        tm.dataParser.setWeatherData(hourlyForecastData);
        tm.dataParser.forecastData();
        HashMap<String, ArrayList<String>> hourlyForecast = tm.dataParser.getDailyForecast();
        int i;
        for (i=1;i<7;i++){
            ArrayList<String> forecast = hourlyForecast.get(Integer.toString(i));
            System.out.println(tm.dataFormatter.formatWeatherData(forecast));
        }


        weatherData = tm.getInitialWeatherData();

        //current forecast
        String forecastURLString = tm.dataParser.parseWeatherAPILink(weatherData, "forecast");
        InputStream forecastData = tm.api.getConnectionFromURL(forecastURLString).getInputStream();
        tm.dataParser.setWeatherData(forecastData);
        tm.dataParser.forecastData();
        HashMap<String, ArrayList<String>> dailyForecast = tm.dataParser.getDailyForecast();
        ArrayList<String> actualArray2 = dailyForecast.get("1");
        System.out.println(actualArray2);

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
}
