package edu.bsu.cs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TerminalMain {
    TerminalController terminalController = new TerminalController();
    FileController fileController = new FileController();
    //WeatherServiceAPI api = new WeatherServiceAPI();

    //TODO: separate into main with preferences and main without
    public static void main(String[] args) throws IOException {
        TerminalMain tm = new TerminalMain();
        ArrayList<Pair> locations = tm.fileController.loadCities();
        String[] preferences = tm.fileController.loadPreferences();
        tm.terminalController.printWelcomeMessage();

        if (preferences[0].equals("true")){
            System.out.println("Got weather data from preferences. ");//InputStream weatherData = tm.getWeatherDataFromPreferences(preferences);
        } else {
            String response = tm.terminalController.getPreferencePreferences();
            if (response.equals("true")){
                preferences = tm.getPreferences(locations);
                tm.fileController.savePreferences(preferences);
                System.out.println("Got Weather data from preferences. ");//InputStream weatherData = tm.getWeatherDataFromPreferences(preferences);
            } else {
                tm.terminalController.printLocations(locations);
                System.out.println("Got weather data.");//InputStream weatherData = tm.getWeatherData(locations);
            }
        }

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

    /*private InputStream getWeatherDataFromPreferences(String[] preferences){
        String link = api.createLink(preferences[1]);
        return api.getInputStreamFrom(link);
    }

    private InputStream getWeatherData(ArrayList<Pair> locations){
        String location = terminalController.getLocationPreference(locations);
        String link = api.createLin(location);
        return api.getInputStreamFrom(link);
    }*/
}
