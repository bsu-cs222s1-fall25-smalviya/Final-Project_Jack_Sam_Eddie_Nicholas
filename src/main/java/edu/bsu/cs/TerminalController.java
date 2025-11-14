package edu.bsu.cs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TerminalController {
    CitiesDatabaseParser citiesDatabase = new CitiesDatabaseParser();

    public TerminalController() throws IOException {
    }

    protected void printWelcomeMessage(){
        System.out.println("Welcome to the CS220 Weather App!");
    }

    protected String getUserChoice(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                0: Exit the program.
                1: Reset preferences.
                2: Set preferences.
                3: Print preferences.
                4: Get hourly weather conditions.
                5: Get daily weather conditions.
                6: Get severe weather alerts.
                7: Get outfit recommendations.""");
        return scanner.nextLine();
    }

    protected void printPreferences() throws FileNotFoundException {
        FileController fileController = new FileController();
        String[] preferences = fileController.loadPreferences();
        System.out.println("Current preferences:");
        for (String i: preferences){
            System.out.println(i);
        }
    }

    protected void printInvalidResponse(){
        System.out.println("Invalid response. ");
    }

    protected String getPreferencePreferences(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to set default preferences? (Y/N) ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("Y")){
            return "true";
        } else if (response.equalsIgnoreCase("N")){
            return "false";
        } else {
            System.out.println("Invalid response. Defaulted to no preferences. ");
            return "false";
        }
    }

    protected String getLocationPreference(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the zipcode you would like to get weather for ");
        String response = scanner.nextLine();
        String zipcode = citiesDatabase.getCoordinates(response);
        if (zipcode.equals("Error")||response.isEmpty()){
            System.out.println("Invalid input. Defaulted to Muncie");
            return "40.1933,-85.3863";
        }
        return zipcode;

    }

    protected String getUnitPreference() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like units to be in Imperial or Metric? (I/M)");
        String unitPreference = scanner.nextLine();

        if (unitPreference.equalsIgnoreCase("imperial") || unitPreference.equalsIgnoreCase("I")) {
            return "imperial";
        } else if (unitPreference.equalsIgnoreCase("metric") || unitPreference.equalsIgnoreCase("M")) {
            return "metric";
        } else {
            System.out.println("Invalid response. Defaulted to Imperial");
            return "imperial";
        }

    }

    protected String getForecastType(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to see weather for the next few days or for the next few hours? (D/H)");
        String forecastType = scanner.nextLine();
        if (forecastType.equalsIgnoreCase("D")){
            return "daily";
        } else if (forecastType.equalsIgnoreCase("H")){
            return "hourly";
        } else {
            System.out.println("Invalid response. Defaulting to daily. ");
            return "daily";
        }
    }

    protected boolean isUserDone(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to exit the program? (Y/N)");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("Y")){
            return true;
        } else if (response.equalsIgnoreCase("N")){
            return false;
        } else {
            System.out.println("Invalid response. Defaulted to continue program. ");
            return false;
        }
    }
}