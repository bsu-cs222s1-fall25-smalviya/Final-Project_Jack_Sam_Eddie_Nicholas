package edu.bsu.cs;

import java.util.Scanner;

public class TerminalController {

    protected String getPreferencePreferences(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to set default preferences? (Y/N) ");
        String response = scanner.nextLine();
        scanner.close();

        if (response.equalsIgnoreCase("Y")){
            return "true";
        } else if (response.equalsIgnoreCase("N")){
            return "false";
        } else {
            System.out.println("Invalid response. Defaulted to no preferences. ");
            return "false";
        }
    }

    /*protected String getLocationPreference(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("What would you like your default location to be? ";
        System.out.println("Choose from these options: ");
        ///for
        String response = scanner.nextLine();

    }*/

    protected String getTempPreference() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like temperature to be in Fahrenheit or Celsius? (F/C)");
        String unitPreference = scanner.nextLine();
        scanner.close();

        if (unitPreference.equalsIgnoreCase("F")) {
            return "fahrenheit";
        } else if (unitPreference.equalsIgnoreCase("C")) {
            return "celsius";
        } else {
            System.out.println("Invalid response. Defaulted to Fahrenheit");
            return "fahrenheit";
        }
    }
}