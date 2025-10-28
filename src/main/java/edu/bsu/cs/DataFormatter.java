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

    public String formatWeatherData(ArrayList<String> weatherData, String desiredUnit, String forecastType) {
        return buildCurrentWeatherString(weatherData, desiredUnit, forecastType);
    }

    private String buildCurrentWeatherString(List<String> weatherData, String desiredUnit, String forecastType) {
        StringBuilder formattedData = new StringBuilder();
        if (forecastType.equalsIgnoreCase("hourly")) {
            formattedData.append("Next Hour:\n");
        } else if (forecastType.equalsIgnoreCase("daily")) {
            formattedData.append("Next Day:\n");
        }

        double tempValue = 0;
        String precipValue;
        String humidityValue = "";
        double dewPointValue = 0;
        String windValue;

        if (weatherData.size() > 3) {
            tempValue = Double.parseDouble(weatherData.get(0));
            precipValue = weatherData.get(1);
            dewPointValue = Double.parseDouble(weatherData.get(2));
            humidityValue = weatherData.get(3);
            windValue = weatherData.get(4);
        } else {
            tempValue = Double.parseDouble(weatherData.get(0));
            precipValue = weatherData.get(1);
            windValue = weatherData.get(2);
        }

        String tempUnitLabel;
        String dewPointUnitLabel;

        int displayTemp;
        int displayDewPoint;

        if (desiredUnit.equalsIgnoreCase("M")) {
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

        if (weatherData.size() > 3) {
            appendDataPoint(formattedData, "Temperature", String.valueOf(displayTemp), tempUnitLabel, "  ");
            appendDataPoint(formattedData, "Precipitation", precipValue, "%", "  ");
            appendDataPoint(formattedData, "Wind", windValue, "", "  ");
            appendDataPoint(formattedData, "Humidity", humidityValue, "%", "  ");
            appendDataPoint(formattedData, "Dew Point", String.valueOf(displayDewPoint), dewPointUnitLabel, "  ");
        } else {
            appendDataPoint(formattedData, "Temperature", String.valueOf(displayTemp), tempUnitLabel, "  ");
            appendDataPoint(formattedData, "Precipitation", precipValue, "%", "  ");
            appendDataPoint(formattedData, "Dew Point", String.valueOf(displayDewPoint), dewPointUnitLabel, "  ");
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