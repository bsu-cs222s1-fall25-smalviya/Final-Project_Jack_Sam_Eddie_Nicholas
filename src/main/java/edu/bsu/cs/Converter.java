package edu.bsu.cs;

public class Converter {

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

    public int milesToKilometers(int mUnit){
        float kUnit = (float) (mUnit * 1.609344);
        return Math.round(kUnit);
    }
}
