package edu.bsu.cs;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataFormatter {

    private final Converter converter;

    public DataFormatter() {
        this.converter = new Converter(new ArrayList<>());
    }

    public String formatWeatherData(ArrayList<String> weatherData, String desiredUnit) {
        return buildCurrentWeatherString(weatherData, desiredUnit);
    }

    public String formatForecastData(LinkedHashMap<Integer, LinkedHashMap<String, String>> forecastData, String forecastType, String desiredUnit) {
        return buildForecastString(forecastData, forecastType, desiredUnit);
    }

    private String buildCurrentWeatherString(List<String> weatherData, String desiredUnit) {
        StringBuilder formattedData = new StringBuilder();
        formattedData.append("Current Weather:\n");

        double tempValue = Double.parseDouble(weatherData.get(0));
        String precipValue = weatherData.get(1);
        double dewPointValue = Double.parseDouble(weatherData.get(2));
        String humidityValue = weatherData.get(3);
        String windValue = weatherData.get(4);

        String tempUnitLabel;
        String dewPointUnitLabel;

        int displayTemp;
        int displayDewPoint;

        if (desiredUnit.equalsIgnoreCase("C")) {
            displayTemp = converter.fahrenheitToCelsius(tempValue);
            tempUnitLabel = "°C";
            displayDewPoint = (int) Math.round(dewPointValue);
            dewPointUnitLabel = "°C";
        } else {
            displayTemp = (int) Math.round(tempValue);
            tempUnitLabel = "°F";
            displayDewPoint = converter.celsiusToFahrenheit(dewPointValue);
            dewPointUnitLabel = "°F";
        }

        appendDataPoint(formattedData, "Temperature", String.valueOf(displayTemp), tempUnitLabel, "  ");
        appendDataPoint(formattedData, "Precipitation", precipValue, "%", "  ");
        appendDataPoint(formattedData, "Dew Point", String.valueOf(displayDewPoint), dewPointUnitLabel, "  ");
        appendDataPoint(formattedData, "Humidity", humidityValue, "%", "  ");
        appendDataPoint(formattedData, "Wind", windValue, "", "  ");
        return formattedData.toString();
    }

    private String buildForecastString(LinkedHashMap<Integer, LinkedHashMap<String, String>> forecastData, String forecastType, String desiredUnit) {
        StringBuilder formattedData = new StringBuilder();
        String timeLabel = forecastType.contains("Hour") ? "Hour" : "Day";
        formattedData.append(forecastType).append(" Forecast:\n");

        for (Map.Entry<Integer, LinkedHashMap<String, String>> entry : forecastData.entrySet()) {
            formattedData.append("  ").append(timeLabel).append(" ").append(entry.getKey()).append(":\n");

            Map<String, String> data = entry.getValue();

            double tempValue = Double.parseDouble(data.get("Temperature"));
            String precipValue = data.get("Precipitation");
            double dewPointValue = Double.parseDouble(data.get("Dew Point"));
            String humidityValue = data.get("Humidity");
            String windValue = data.get("Wind Speed & Direction");

            String tempUnitLabel;
            String dewPointUnitLabel;

            int displayTemp;
            int displayDewPoint;

            if (desiredUnit.equalsIgnoreCase("C")) {
                displayTemp = converter.fahrenheitToCelsius(tempValue);
                tempUnitLabel = "°C";
                displayDewPoint = (int) Math.round(dewPointValue);
                dewPointUnitLabel = "°C";
            } else {
                displayTemp = (int) Math.round(tempValue);
                tempUnitLabel = "°F";
                displayDewPoint = converter.celsiusToFahrenheit(dewPointValue);
                dewPointUnitLabel = "°F";
            }

            appendDataPoint(formattedData, "Temperature", String.valueOf(displayTemp), tempUnitLabel, "    ");
            appendDataPoint(formattedData, "Precipitation", precipValue, "%", "    ");
            appendDataPoint(formattedData, "Dew Point", String.valueOf(displayDewPoint), dewPointUnitLabel, "    ");
            appendDataPoint(formattedData, "Humidity", humidityValue, "%", "    ");
            appendDataPoint(formattedData, "Wind", windValue, "", "    ");
        }
        return formattedData.toString();
    }

    private void appendDataPoint(StringBuilder builder, String label, String value, String unit, String indent) {
        builder.append(indent)
                .append("• ")
                .append(label)
                .append(": ")
                .append(value)
                .append(unit.equals("%") && value.endsWith("%") ? "" : unit)
                .append("\n");
    }
}
