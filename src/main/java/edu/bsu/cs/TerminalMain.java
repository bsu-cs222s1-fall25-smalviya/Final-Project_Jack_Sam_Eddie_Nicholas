package edu.bsu.cs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TerminalMain {
    TerminalController terminalController = new TerminalController();
    FileController fileController = new FileController();
    WeatherServiceAPI api = new WeatherServiceAPI();
    APIDataParser dataParser = new APIDataParser();

    //TODO: separate into main with preferences and main without
    public static void main(String[] args) throws IOException {
        TerminalMain tm = new TerminalMain();
        ArrayList<Pair> locations = tm.fileController.loadCities();
        String[] preferences = tm.fileController.loadPreferences();
        tm.terminalController.printWelcomeMessage();
        InputStream weatherData;

        if (preferences[0].equals("true")){
            System.out.println("Got weather data from preferences. ");
            weatherData = tm.getWeatherDataFromPreferences(preferences);
        } else {
            String response = tm.terminalController.getPreferencePreferences();
            if (response.equals("true")){
                preferences = tm.getPreferences(locations);
                tm.fileController.savePreferences(preferences);
                System.out.println("Got Weather data from preferences. ");
                weatherData = tm.getWeatherDataFromPreferences(preferences);
            } else {
                tm.terminalController.printLocations(locations);
                System.out.println("Got weather data.");
                weatherData = tm.getWeatherData(locations);
            }
        }
        tm.dataParser.WeatherApiParser(weatherData);

        tm.resetPreferences();
    }

    private String[] getPreferences(ArrayList<Pair> locations){
        String location = terminalController.getLocationPreference(locations);
        String units = terminalController.getUnitPreference();
        return new String[]{"true", location, units};
    }

    private void resetPreferences() throws IOException {
        fileController.savePreferences(new String[] {"false","muncie, in","imperial"});
    }

    private InputStream getWeatherDataFromPreferences(String[] preferences){
        String link = api.createURLString(preferences[1]);
        return api.getConnctionFromURL(link);
    }

    private InputStream getWeatherData(ArrayList<Pair> locations){
        String location = terminalController.getLocationPreference(locations);
        String link = api.createURLString(location);
        return api.getConnctionFromURL(link);
    }
}
