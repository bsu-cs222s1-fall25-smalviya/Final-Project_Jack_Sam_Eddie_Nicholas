package edu.bsu.cs;

import java.util.ArrayList;
import java.util.Scanner;

public class TerminalController {
    protected void printWelcomeMessage(){
        System.out.println("Welcome to the CS220 Weather App!");
    }

    protected String getUserChoice(){
        Scanner scanner = new Scanner(System.in);

        //TODO: clarify hourly vs daily
        System.out.println("""
                0: Exit the program.
                1: Set preferences.
                2: Get hourly weather conditions
                3: Get daily weather conditions""");
        return scanner.nextLine();
    }

    protected void printInvalidResponse(){
        System.out.println("Invalid response. ");
    }

    protected void printLocations(ArrayList<Pair> locations){
        for (Pair i: locations) {
            System.out.println(i.getName());
        }
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

    protected String getLocationPreference(ArrayList<Pair> locations){
        Scanner scanner = new Scanner(System.in);

        System.out.println("What would you like your default location to be? ");
        System.out.println("Choose from these options: ");
        this.printLocations(locations);

        String response = scanner.nextLine();
        for (Pair i: locations){
            if (response.equalsIgnoreCase(i.getName())){
                return i.getLatLong();
            }
        }
        System.out.println("Invalid response. Defaulted to Muncie. ");
        return "40.1933,-85.3863";
    }

    protected String getUnitPreference() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like units to be in Imperial or Metric? (I/M)");
        String unitPreference = scanner.nextLine();

        if (unitPreference.equalsIgnoreCase("I")) {
            return "I";
        } else if (unitPreference.equalsIgnoreCase("M")) {
            return "C";
        } else {
            System.out.println("Invalid response. Defaulted to Imperial");
            return "I";
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