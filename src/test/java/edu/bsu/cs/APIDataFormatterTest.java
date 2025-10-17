package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

public class APIDataFormatterTest {

    @Test
    public void testFormatWeatherData() {
        APIDataFormatter dataFormatter = new APIDataFormatter();
        ArrayList<String> weatherData = new ArrayList<>(Arrays.asList("71", "0%", "5", "34%", "7 mph NNE"));
        String expectedOutput = """
        Current Weather:
          • Temperature: 71°F
          • Precipitation: 0%
          • Dew Point: 5°C
          • Humidity: 34%
          • Wind: 7 mph NNE
        """;
        String actualOutput = dataFormatter.formatWeatherData(weatherData);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testNullWeatherData() {
        APIDataFormatter dataFormatter = new APIDataFormatter();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> dataFormatter.formatWeatherData(null)
        );
        assertEquals("Weather data cannot be null.", exception.getMessage());
    }

    @Test
    public void testTooFewDataPoints() {
        APIDataFormatter dataFormatter = new APIDataFormatter();
        ArrayList<String> weatherData = new ArrayList<>(Arrays.asList("71", "0%", "5", "34%"));
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> dataFormatter.formatWeatherData(weatherData));
        String expectedMessage = "Weather data must contain exactly 5 data points.";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testTooManyDataPoints() {
        APIDataFormatter dataFormatter = new APIDataFormatter();
        ArrayList<String> weatherData = new ArrayList<>(Arrays.asList("71", "0%", "5", "34%", "7 mph NNE", "extra"));
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> dataFormatter.formatWeatherData(weatherData));
        String expectedMessage = "Weather data must contain exactly 5 data points.";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}