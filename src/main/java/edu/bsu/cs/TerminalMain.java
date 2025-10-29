package edu.bsu.cs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class TerminalMain {
    TerminalController terminalController = new TerminalController();
    FileController fileController = new FileController();
    WeatherServiceAPI api = new WeatherServiceAPI();
    APIDataParser dataParser = new APIDataParser();
    DataFormatter dataFormatter = new DataFormatter();
    ArrayList<Pair> locations;

    public TerminalMain() throws FileNotFoundException {
        this.locations = this.fileController.loadCities();
    }

    static void main() throws IOException {
        TerminalMain tm = new TerminalMain();
        boolean keepGoing = true;

        tm.terminalController.printWelcomeMessage();
        while (keepGoing) {
            String choice = tm.terminalController.getUserChoice();
            if (choice.equals("0")) {
                keepGoing = false;
            } else if (choice.equals("1")) {
                tm.resetPreferences();
            } else if (choice.equals("2")) {
                tm.setPreferences();
            }  else if (choice.equals("3")){
                tm.terminalController.printPreferences();
            } else if (choice.equals("4")) {
                tm.getHourlyForecast();
            } else if (choice.equals("5")) {
                tm.getDailyForecast();
            } else {
                tm.terminalController.printInvalidResponse();
            }
        }
    }

    protected void setPreferences() throws IOException {
        String location = terminalController.getLocationPreference(this.locations);
        String units = terminalController.getUnitPreference();
        fileController.savePreferences(new String[] {"true",location,units});
    }

    protected void getHourlyForecast() throws IOException {
        String link;
        InputStream weatherData;
        String units;
        String[] preferences = this.fileController.loadPreferences();
        if (preferences[0].equals("true")) {
            link = api.createURLString(preferences[1]);
            units = preferences[2];
        } else {
            String location = terminalController.getLocationPreference(this.locations);
            units = terminalController.getUnitPreference();
            link = api.createURLString(location);
        }

        weatherData = api.getInputStreamFromURL(link);
        String hourlyForecastURLString = this.dataParser.parseWeatherAPILink(weatherData, "forecastHourly");
        InputStream hourlyForecastData = this.api.getInputStreamFromURL(hourlyForecastURLString);
        this.dataParser.setWeatherData(hourlyForecastData);
        this.dataParser.hourlyForecastData();
        HashMap<Integer, ArrayList<String>> hourlyForecast = this.dataParser.getHourlyForecast();
        int i;
        for (i=1;i<=7;i++){
            ArrayList<String> forecast = hourlyForecast.get(i);
            System.out.println(this.dataFormatter.formatWeatherData(forecast, units, "Hourly"));
        }
    }

    protected void getDailyForecast() throws IOException {
        InputStream weatherData;
        String units;
        String[] preferences = this.fileController.loadPreferences();
        if (preferences[0].equals("true")) {
            String link = api.createURLString(preferences[1]);
            weatherData = api.getInputStreamFromURL(link);
            units = preferences[2];
        } else {
            String location = terminalController.getLocationPreference(this.locations);
            units = terminalController.getUnitPreference();
            String link = api.createURLString(location);
            weatherData = api.getInputStreamFromURL(link);
        }

        String forcastURLString = this.dataParser.parseWeatherAPILink(weatherData, "forecast");
        InputStream forecastData = this.api.getInputStreamFromURL(forcastURLString);
        this.dataParser.setWeatherData(forecastData);
        this.dataParser.forecastData();
        HashMap<Integer, ArrayList<String>> weeklyForecast = this.dataParser.getDailyForecast();
        int i;
        for (i=1;i<=13;i=i+2){
            ArrayList<String> forecast = weeklyForecast.get(i);
            System.out.println(this.dataFormatter.formatWeatherData(forecast,units,"Daily"));
        }
    }

    protected void resetPreferences() throws IOException {
        fileController.savePreferences(new String[] {"false","40.1933,-85.3863","I"});
    }
}