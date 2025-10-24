package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class DataFormatterTest {

    // Test 1: Current Weather, Fahrenheit (Unit conversion for Dew Point)
    @Test
    public void testFormatWeatherData_Current_Fahrenheit() {
        DataFormatter dataFormatter = new DataFormatter();

        ArrayList<String> weatherData = new ArrayList<>(Arrays.asList(
                "71",      // Temperature (F)
                "0%",      // Precipitation
                "5.0",     // Dew Point (C)
                "34%",     // Humidity
                "7 mph NNE" // Wind
        ));
        String expectedOutput = """
        Current Weather:
          • Temperature: 71°F
          • Precipitation: 0%
          • Dew Point: 41°F
          • Humidity: 34%
          • Wind: 7 mph NNE
        """;
        // Pass "F" for Fahrenheit
        String actualOutput = dataFormatter.formatWeatherData(weatherData, "F");
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    // Test 2: Current Weather, Celsius (Unit conversion for Temperature)
    @Test
    public void testFormatWeatherData_Current_Celsius() {
        DataFormatter dataFormatter = new DataFormatter();

        ArrayList<String> weatherData = new ArrayList<>(Arrays.asList(
                "71",      // Temperature (F)
                "0%",      // Precipitation
                "5.0",     // Dew Point (C)
                "34%",     // Humidity
                "7 mph NNE" // Wind
        ));
        String expectedOutput = """
        Current Weather:
          • Temperature: 22°C
          • Precipitation: 0%
          • Dew Point: 5°C
          • Humidity: 34%
          • Wind: 7 mph NNE
        """;
        // Pass "C" for Celsius
        String actualOutput = dataFormatter.formatWeatherData(weatherData, "C");
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    // Helper to create the full 7-entry forecast data
    private LinkedHashMap<Integer, LinkedHashMap<String, String>> createFullForecastData() {
        LinkedHashMap<Integer, LinkedHashMap<String, String>> forecastData = new LinkedHashMap<>();

        // Entry 1
        LinkedHashMap<String, String> data1 = new LinkedHashMap<>();
        data1.put("Temperature", "71");
        data1.put("Precipitation", "0%");
        data1.put("Dew Point", "5");
        data1.put("Humidity", "34%");
        data1.put("Wind Speed & Direction", "7 mph NNE");
        forecastData.put(1, data1);

        // Entry 2
        LinkedHashMap<String, String> data2 = new LinkedHashMap<>();
        data2.put("Temperature", "67");
        data2.put("Precipitation", "0%");
        data2.put("Dew Point", "5");
        data2.put("Humidity", "39%");
        data2.put("Wind Speed & Direction", "5 mph NNE");
        forecastData.put(2, data2);

        // Entry 3
        LinkedHashMap<String, String> data3 = new LinkedHashMap<>();
        data3.put("Temperature", "61");
        data3.put("Precipitation", "0%");
        data3.put("Dew Point", "5");
        data3.put("Humidity", "48%");
        data3.put("Wind Speed & Direction", "3 mph NE");
        forecastData.put(3, data3);

        // Entry 4
        LinkedHashMap<String, String> data4 = new LinkedHashMap<>();
        data4.put("Temperature", "59");
        data4.put("Precipitation", "0%");
        data4.put("Dew Point", "5");
        data4.put("Humidity", "51%");
        data4.put("Wind Speed & Direction", "3 mph NE");
        forecastData.put(4, data4);

        // Entry 5
        LinkedHashMap<String, String> data5 = new LinkedHashMap<>();
        data5.put("Temperature", "56");
        data5.put("Precipitation", "0%");
        data5.put("Dew Point", "5");
        data5.put("Humidity", "57%");
        data5.put("Wind Speed & Direction", "3 mph NE");
        forecastData.put(5, data5);

        // Entry 6
        LinkedHashMap<String, String> data6 = new LinkedHashMap<>();
        data6.put("Temperature", "54");
        data6.put("Precipitation", "0%");
        data6.put("Dew Point", "4.444444444444445"); // The specific case
        data6.put("Humidity", "59%");
        data6.put("Wind Speed & Direction", "3 mph ENE");
        forecastData.put(6, data6);

        // Entry 7
        LinkedHashMap<String, String> data7 = new LinkedHashMap<>();
        data7.put("Temperature", "52");
        data7.put("Precipitation", "0%");
        data7.put("Dew Point", "5");
        data7.put("Humidity", "66%");
        data7.put("Wind Speed & Direction", "3 mph ENE");
        forecastData.put(7, data7);

        return forecastData;
    }

    // Test 3: 7-Hour Forecast, Celsius
    @Test
    public void testFormatForecastData_Hourly_Celsius() {
        DataFormatter dataFormatter = new DataFormatter();
        LinkedHashMap<Integer, LinkedHashMap<String, String>> testData = createFullForecastData();

        String expectedOutput = """
        7-Hour Forecast:
          Hour 1:
            • Temperature: 22°C
            • Precipitation: 0%
            • Dew Point: 5°C
            • Humidity: 34%
            • Wind: 7 mph NNE
          Hour 2:
            • Temperature: 19°C
            • Precipitation: 0%
            • Dew Point: 5°C
            • Humidity: 39%
            • Wind: 5 mph NNE
          Hour 3:
            • Temperature: 16°C
            • Precipitation: 0%
            • Dew Point: 5°C
            • Humidity: 48%
            • Wind: 3 mph NE
          Hour 4:
            • Temperature: 15°C
            • Precipitation: 0%
            • Dew Point: 5°C
            • Humidity: 51%
            • Wind: 3 mph NE
          Hour 5:
            • Temperature: 13°C
            • Precipitation: 0%
            • Dew Point: 5°C
            • Humidity: 57%
            • Wind: 3 mph NE
          Hour 6:
            • Temperature: 12°C
            • Precipitation: 0%
            • Dew Point: 4°C
            • Humidity: 59%
            • Wind: 3 mph ENE
          Hour 7:
            • Temperature: 11°C
            • Precipitation: 0%
            • Dew Point: 5°C
            • Humidity: 66%
            • Wind: 3 mph ENE
        """;
        String actualOutput = dataFormatter.formatForecastData(testData, "7-Hour", "C");
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    // Test 4: 7-Day Forecast, Fahrenheit
    @Test
    public void testFormatForecastData_Daily_Fahrenheit() {
        DataFormatter dataFormatter = new DataFormatter();
        LinkedHashMap<Integer, LinkedHashMap<String, String>> testData = createFullForecastData();

        String expectedOutput = """
        7-Day Forecast:
          Day 1:
            • Temperature: 71°F
            • Precipitation: 0%
            • Dew Point: 41°F
            • Humidity: 34%
            • Wind: 7 mph NNE
          Day 2:
            • Temperature: 67°F
            • Precipitation: 0%
            • Dew Point: 41°F
            • Humidity: 39%
            • Wind: 5 mph NNE
          Day 3:
            • Temperature: 61°F
            • Precipitation: 0%
            • Dew Point: 41°F
            • Humidity: 48%
            • Wind: 3 mph NE
          Day 4:
            • Temperature: 59°F
            • Precipitation: 0%
            • Dew Point: 41°F
            • Humidity: 51%
            • Wind: 3 mph NE
          Day 5:
            • Temperature: 56°F
            • Precipitation: 0%
            • Dew Point: 41°F
            • Humidity: 57%
            • Wind: 3 mph NE
          Day 6:
            • Temperature: 54°F
            • Precipitation: 0%
            • Dew Point: 40°F
            • Humidity: 59%
            • Wind: 3 mph ENE
          Day 7:
            • Temperature: 52°F
            • Precipitation: 0%
            • Dew Point: 41°F
            • Humidity: 66%
            • Wind: 3 mph ENE
        """;
        String actualOutput = dataFormatter.formatForecastData(testData, "7-Day", "F");
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}