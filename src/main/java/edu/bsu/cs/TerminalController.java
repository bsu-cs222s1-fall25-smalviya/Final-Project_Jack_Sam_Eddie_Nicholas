package edu.bsu.cs;

import java.util.Scanner;

public class TerminalController {

    protected String getPreferencePreferences(){
        Scanner scanner = new Scanner(System.in);
        String preferences;

        System.out.println("Would you like to set default preferences? (Y/N) ");
        String response = scanner.nextLine();
        scanner.close();

        if (response.equalsIgnoreCase("Y")){
            return preferences;
        } else if (response.equalsIgnoreCase("N")){
            return "blank";
        } else {
            System.out.println("Invalid response. Defaulted to no preferences. ");
            return "blank";
        }
    }

    /*protected String getLocationPreference(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to set a default location? (Y/N");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("Y")){

        return "";
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