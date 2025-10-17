package edu.bsu.cs;

import java.util.ArrayList;
import java.util.List;

public class APIDataFormatter {

    private static final int REQUIRED_DATA_POINTS = 5; // Minimize the number of entities, if we add more data types, we only need to change it here


    // clean easy to read main method
    public String formatWeatherData(ArrayList<String> weatherData) {
        validateWeatherData(weatherData);
        return buildFormattedString(weatherData);
    }

    // separate from the formatting logic, making the code cleaner and easier to test
    private void validateWeatherData(List<String> weatherData) {
        if (weatherData == null) {
            throw new IllegalArgumentException("Weather data cannot be null.");
        }
        if (weatherData.size() != REQUIRED_DATA_POINTS) {
            throw new IllegalArgumentException("Weather data must contain exactly " + REQUIRED_DATA_POINTS + " data points.");
        }
    }

    //
    private String buildFormattedString(List<String> weatherData) {
        StringBuilder formattedData = new StringBuilder();
        formattedData.append("Current Weather:\n");
        appendDataPoint(formattedData, "Temperature", weatherData.get(0), "°F");
        appendDataPoint(formattedData, "Precipitation", weatherData.get(1), "");
        appendDataPoint(formattedData, "Dew Point", weatherData.get(2), "°C");
        appendDataPoint(formattedData, "Humidity", weatherData.get(3), "");
        appendDataPoint(formattedData, "Wind", weatherData.get(4), "");
        return formattedData.toString();
    }
    // This helper method eliminates the repeated code for appending each line of weather data
    // abstracting the pattern of "label: value unit" into a single, reusable function
    private void appendDataPoint(StringBuilder builder, String label, String value, String unit) {
        builder.append("  • ")
                .append(label)
                .append(": ")
                .append(value)
                .append(unit)
                .append("\n");
    }
}