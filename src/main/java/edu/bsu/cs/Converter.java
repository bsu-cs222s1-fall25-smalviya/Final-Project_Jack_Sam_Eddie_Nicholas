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
    public String convertWindSpeed(String windSpeed, String targetUnit) {
        if (targetUnit.equalsIgnoreCase("Metric") || targetUnit.equalsIgnoreCase("M")) {
            // Extract number and direction from strings like "8 mph WNW"
            String[] parts = windSpeed.trim().split("\\s+");

            if (parts.length >= 2) {
                try {
                    // Parse the speed value
                    int mph = Integer.parseInt(parts[0]);
                    int kmh = milesToKilometers(mph);

                    // Get direction
                    String direction = parts[parts.length - 1];
                    return kmh + " km/h " + direction;
                } catch (NumberFormatException e) {
                    // If parsing fails, return original
                    return windSpeed;
                }
            }
        }
        return windSpeed;
    }

}
