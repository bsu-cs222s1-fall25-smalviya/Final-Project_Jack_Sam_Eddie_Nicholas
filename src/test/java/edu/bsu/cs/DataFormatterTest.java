package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

public class DataFormatterTest {

    @Test
    public void testFormatWeatherData_Current_Fahrenheit() {
        DataFormatter dataFormatter = new DataFormatter();

        ArrayList<String> weatherData = new ArrayList<>(Arrays.asList(
                "71",
                "0%",
                "5.0",
                "34%",
                "7 mph NNE"
        ));
        String expectedOutput = """
        Next Hour:
           -  Temperature: 71 degrees F
           -  Precipitation: 0%
           -  Wind: 7 mph NNE
           -  Humidity: 34%
           -  Dew Point: 41 degrees F
        """;
        String actualOutput = dataFormatter.formatWeatherData(weatherData, "F", "hourly");
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    @Test
    public void testFormatWeatherData_Current_Celsius() {
        DataFormatter dataFormatter = new DataFormatter();

        ArrayList<String> weatherData = new ArrayList<>(Arrays.asList(
                "71",
                "0%",
                "5.0",
                "34%",
                "7 mph NNE"
        ));
        String expectedOutput = """
        Next Day:
           -  Temperature: 22 degrees C
           -  Precipitation: 0%
           -  Wind: 7 mph NNE
           -  Humidity: 34%
           -  Dew Point: 5 degrees C
        """;
        String actualOutput = dataFormatter.formatWeatherData(weatherData, "M", "daily");
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }


    @Test
    public void testFormatAlerts_WithAlerts() {
        DataFormatter dataFormatter = new DataFormatter();

        ArrayList<String> alerts = new ArrayList<>(Arrays.asList(
                "Severe Thunderstorm Warning",
                "National Weather Service has issued a Severe Thunderstorm Warning",
                "Flood Watch",
                "A Flood Watch has been issued for the area"
        ));

        String expectedOutput = """
        Active Weather Alerts:
          -  Severe Thunderstorm Warning: National Weather Service has issued a Severe Thunderstorm Warning
          -  Flood Watch: A Flood Watch has been issued for the area
        """;

        String actualOutput = dataFormatter.formatAlerts(alerts);
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    @Test
    public void testFormatAlerts_NoAlerts() {
        DataFormatter dataFormatter = new DataFormatter();

        ArrayList<String> alerts = new ArrayList<>();
        String expectedOutput = "No active weather alerts.";
        String actualOutput = dataFormatter.formatAlerts(alerts);

        assertEquals(expectedOutput, actualOutput);
    }
}