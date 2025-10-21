package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class APIDataParserTest {

    @Test
    public void WeatherInformationParserTest() throws IOException {
        ArrayList<String> trueArray = new ArrayList<>(Arrays.asList("Temperature: 71",
                "Precipitation: 0%",
                "Dew Point: 5",
                "Humidity: 34%",
                "Wind Speed & Direction: 7 mph NNE"));

        InputStream weatherData = getClass().getResourceAsStream("/hourlyWeather.json");
        Assertions.assertNotNull(weatherData, "Data stream was not found");

        APIDataParser apiDataParser = new APIDataParser(weatherData);
        apiDataParser.HourlyForecastData();

        ArrayList<String> actualArray = null;
        HashMap<String, ArrayList<String>> hourlyForecast = apiDataParser.getHourlyForecast();
        actualArray = hourlyForecast.get("0");
        Assertions.assertEquals(trueArray, actualArray);
    }

    @Test
    public void WeatherAPILinkParserTest() throws IOException {
        InputStream weatherData = Thread.currentThread().getContextClassLoader().getResourceAsStream("weatherContext.json");
        APIDataParser apiDataParser = new APIDataParser(null);
        String testCase = "forecastHourly";
        String actualUrl = apiDataParser.weatherAPILinkParser(weatherData, testCase);
        String expectedUrl = "https://api.weather.gov/gridpoints/IND/83,90/forecast/hourly";
        Assertions.assertEquals(
                expectedUrl,
                actualUrl.replace("\\/", "/")
        );

    }
}
