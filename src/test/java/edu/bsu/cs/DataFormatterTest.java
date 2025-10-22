package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

public class DataFormatterTest {

    @Test
    public void testFormatWeatherData() {
        DataFormatter dataFormatter = new DataFormatter();
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
        DataFormatter dataFormatter = new DataFormatter();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> dataFormatter.formatWeatherData(null)
        );
        assertEquals("Weather data cannot be null.", exception.getMessage());
    }
}