package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class APIDataParserTest {

    @Test
    public void WeatherInformationParserTest() throws IOException {
        ArrayList<String> trueArray = new ArrayList<>(Arrays.asList("71", "0%", "5", "34%", "7 mph NNE"));

        APIDataParser apiDataFormater = new APIDataParser();
        InputStream weatherData = Thread.currentThread().getContextClassLoader().getResourceAsStream("hourlyWeather.json");
        ArrayList<String> actualArray = null;
        if (weatherData != null) {
            actualArray = apiDataFormater.WeatherApiParser(weatherData);
        }
        Assertions.assertEquals(trueArray, actualArray);
    }

    @Test
    public void WeatherAPILinkParserTest() throws IOException {
        APIDataParser apiDataParser = new APIDataParser();
        String testCase = "forecastHourly";
        InputStream weatherData = Thread.currentThread().getContextClassLoader().getResourceAsStream("weatherContext.json");
        String actualUrl = apiDataParser.weatherAPILinkParser(weatherData, testCase);
        String expectedUrl = "https://api.weather.gov/gridpoints/IND/83,90/forecast/hourly";
        Assertions.assertEquals(
                expectedUrl,
                actualUrl.replace("\\/", "/")
        );

    }
}
