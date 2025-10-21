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
    //APIDataFormatter dataFormatter = new APIDataFormatter();
    ArrayList<Pair> locations;
    String[] preferences;

    //TODO: separate into main with preferences and main without
    public static void main(String[] args) throws IOException {
        TerminalMain tm = new TerminalMain();

        //TODO: remove
        tm.resetPreferences();

        tm.locations = tm.fileController.loadCities();
        tm.preferences = tm.fileController.loadPreferences();
        InputStream weatherData;

        tm.terminalController.printWelcomeMessage();

        if (tm.preferences[0].equals("true")){
            weatherData = tm.getWeatherDataFromPreferences();
        } else {
            String response = tm.terminalController.getPreferencePreferences();
            if (response.equals("true")){
                tm.preferences = tm.getPreferences();
                tm.fileController.savePreferences(tm.preferences);
                weatherData = tm.getWeatherDataFromPreferences();
            } else {
                weatherData = tm.getWeatherData();
            }
        }

        tm.dataParser.setWeatherData(weatherData);
        tm.dataParser.forecastData();
        HashMap<String, ArrayList<String>> dailyForecast = tm.dataParser.getDailyForecast();
        ArrayList<String> actualArray = dailyForecast.get("1");
        System.out.println(actualArray);

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

    private InputStream getWeatherDataFromPreferences() throws IOException {
        String link = api.createURLString(this.preferences[2]);
        return api.getConnectionFromURL(link).getInputStream();
    }

    private InputStream getWeatherData() throws IOException {
        String latLong = terminalController.getLocationPreference(this.locations);
        String link = api.createURLString(latLong);
        return api.getConnectionFromURL(link).getInputStream();
    }
}
