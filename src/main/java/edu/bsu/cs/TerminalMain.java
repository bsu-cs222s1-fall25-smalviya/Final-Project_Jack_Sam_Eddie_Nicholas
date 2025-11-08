package edu.bsu.cs;

import javax.swing.*;
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

    public TerminalMain() throws IOException {
    }

    static void main() throws IOException {
        TerminalMain tm = new TerminalMain();
        boolean keepGoing = true;

        tm.terminalController.printWelcomeMessage();
        while (keepGoing) {
            String choice = tm.terminalController.getUserChoice();
            switch (choice) {
                case "0" -> keepGoing = false;
                case "1" -> tm.fileController.resetPreferences();
                case "2" -> tm.setPreferences();
                case "3" -> tm.terminalController.printPreferences();
                case "4" -> tm.getHourlyForecast();
                case "5" -> tm.getDailyForecast();
                case "6" -> tm.getWeatherAlerts();
                default -> tm.terminalController.printInvalidResponse();
            }
        }
    }

    protected void setPreferences() throws IOException {
        String location = terminalController.getLocationPreference();
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
            String location = terminalController.getLocationPreference();
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
            String location = terminalController.getLocationPreference();
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

    protected void getWeatherAlerts() throws IOException {
        String location;
        String[] preferences = this.fileController.loadPreferences();
        if (preferences[0].equals("true")) {
            location = preferences[1];
        } else {
            location = terminalController.getLocationPreference();
        }

        String alertsURL = api.createAlertsURLString(location);
        InputStream alertsData = api.getInputStreamFromURL(alertsURL);
        this.dataParser.setWeatherData(alertsData);
        this.dataParser.alertsData();
        ArrayList<String> alerts = this.dataParser.getAlerts();
        System.out.println(this.dataFormatter.formatAlerts(alerts));
    }
}