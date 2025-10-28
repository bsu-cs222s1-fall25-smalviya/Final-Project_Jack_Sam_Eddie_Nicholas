package edu.bsu.cs;

import java.util.ArrayList;

public class Converter {
    ArrayList<Pair> locationPairs;

    public Converter(ArrayList<Pair> locationPairs){
        this.locationPairs = locationPairs;
    }

    public int fahrenheitToCelsius(int fUnit){
        float cUnit = (float) (fUnit - 32) * 5 / 9;
        return Math.round(cUnit);
    }

    public int fahrenheitToCelsius(double fUnit){
        double cUnit = (fUnit - 32) * 5.0 / 9.0;
        return (int) Math.round(cUnit);
    }

    public int celsiusToFahrenheit(int cUnit) {
        float fUnit = (float) (cUnit * 9) / 5 + 32;
        return Math.round(fUnit);
    }

    public int celsiusToFahrenheit(double cUnit) {
        double fUnit = (cUnit * 9.0) / 5.0 + 32;
        return (int) Math.round(fUnit);
    }

    public String locationToLatLong(String name){
        int i;
        for(i = 0; i < this.locationPairs.size(); i++){
            if(name.equals(this.locationPairs.get(i).getName())){
                return this.locationPairs.get(i).getLatLong();
            }
        }
        return null;
    }

    public int milesToKilometers(int mUnit){
        float kUnit = (float) (mUnit * 1.609344);
        return Math.round(kUnit);
    }
}
