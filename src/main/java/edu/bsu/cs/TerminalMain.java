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
    String[] preferences;

    public TerminalMain() throws FileNotFoundException {
        this.locations = this.fileController.loadCities();
        this.preferences = this.fileController.loadPreferences();
    }

    static void main() throws IOException {
        TerminalMain tm = new TerminalMain();
        boolean keepGoing = true;

        //TODO: remove this line
        tm.resetPreferences();

        tm.terminalController.printWelcomeMessage();
        while (keepGoing) {
            String choice = tm.terminalController.getUserChoice();
            if (choice.equals("0")) {
                keepGoing = false;
            } else if (choice.equals("1")) {
                tm.setPreferences();
            } else if (choice.equals("2")) {
                tm.getHourlyForecast();
            } else if (choice.equals("3")) {
                tm.getDailyForecast();
            } else {
                tm.terminalController.printInvalidResponse();
            }
        }
        //TODO: remove this line
        tm.resetPreferences();
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
        if (this.preferences[0].equals("true")) {
            link = api.createURLString(this.preferences[1]);
            units = this.preferences[2];
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
        HashMap<String, ArrayList<String>> hourlyForecast = this.dataParser.getHourlyForecast();
        int i;
        for (i=1;i<7;i++){
            ArrayList<String> forecast = hourlyForecast.get(Integer.toString(i));
            System.out.println(this.dataFormatter.formatWeatherData(forecast, units));
        }
    }

    protected void getDailyForecast() throws IOException {
        InputStream weatherData;
        String units;
        if (this.preferences[0].equals("true")) {
            String link = api.createURLString(this.preferences[1]);
            weatherData = api.getInputStreamFromURL(link);
            units = this.preferences[2];
        } else {
            String location = terminalController.getLocationPreference(this.locations);
            units = terminalController.getUnitPreference();
            String link = api.createURLString(location);
            weatherData = api.getInputStreamFromURL(link);
        }

        String hourlyForcastURLString = this.dataParser.parseWeatherAPILink(weatherData, "forecastHourly");
        InputStream hourlyForecastData = this.api.getInputStreamFromURL(hourlyForcastURLString);
        this.dataParser.setWeatherData(hourlyForecastData);
        this.dataParser.forecastData();
        HashMap<String, ArrayList<String>> weeklyForecast = this.dataParser.getDailyForecast();
        int i;
        for (i=1;i<7;i++){
            ArrayList<String> forecast = weeklyForecast.get(Integer.toString(i));
            System.out.println(this.dataFormatter.formatWeatherData(forecast, units));
        }
    }

    protected void resetPreferences() throws IOException {
        fileController.savePreferences(new String[] {"false","muncie, in","imperial"});
    }
}