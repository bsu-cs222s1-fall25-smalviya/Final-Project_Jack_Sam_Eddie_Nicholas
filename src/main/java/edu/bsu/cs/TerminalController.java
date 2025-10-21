package edu.bsu.cs;

import java.util.ArrayList;
import java.util.Scanner;

public class TerminalController {
    protected void printWelcomeMessage(){
        System.out.println("Welcome to the CS220 Weather App!");
    }

    protected void printLocations(ArrayList<Pair> locations){
        for (int i = 0; i<locations.size(); i++){
            System.out.println(locations.get(i).getName());
        }
        System.out.println();
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

        System.out.println("What would you like your default location to be? ");
        System.out.println("Choose from these options: ");
        this.printLocations(locations);


        Scanner scanner = new Scanner(System.in);
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
        scanner.close();

        if (unitPreference.equalsIgnoreCase("I")) {
            return "imperial";
        } else if (unitPreference.equalsIgnoreCase("M")) {
            return "metric";
        } else {
            System.out.println("Invalid response. Defaulted to Imperial");
            return "imperial";
        }
    }
}