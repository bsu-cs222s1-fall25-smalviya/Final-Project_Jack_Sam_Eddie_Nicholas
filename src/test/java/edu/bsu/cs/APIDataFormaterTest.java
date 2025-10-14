package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class APIDataFormaterTest {

    @Test
    public void WeatherInformationParserTest() throws IOException {
        ArrayList<String> trueArray = new ArrayList<>(Arrays.asList("71", "0%", "5", "34%", "7 mph NNE"));

        APIDataFormater apiDataFormater = new APIDataFormater();
        InputStream weatherData = Thread.currentThread().getContextClassLoader().getResourceAsStream("hourlyWeather.json");
        ArrayList<String> actualArray = null;
        if (weatherData != null) {
            actualArray = apiDataFormater.WeatherApiParser(weatherData);
        }
        Assertions.assertEquals(trueArray, actualArray);
    }
}
