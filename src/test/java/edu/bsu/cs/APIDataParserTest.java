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
    public void WeatherInformationHourlyForecastTest() throws IOException {
        ArrayList<String> trueArray = new ArrayList<>(Arrays.asList("Temperature: 71",
                "Precipitation: 0%",
                "Dew Point: 5.00",
                "Humidity: 34%",
                "Wind Speed & Direction: 7 mph NNE"));

        InputStream weatherData = getClass().getResourceAsStream("/edu/bsu/cs/hourlyWeather.json");
        Assertions.assertNotNull(weatherData, "Data stream was not found");

        APIDataParser apiDataParser = new APIDataParser();
        apiDataParser.setWeatherData(weatherData);
        apiDataParser.HourlyForecastData();
        HashMap<String, ArrayList<String>> hourlyForecast = apiDataParser.getHourlyForecast();
        ArrayList<String> actualArray = hourlyForecast.get("1");
        Assertions.assertEquals(trueArray, actualArray);
    }

    @Test
    public void parseWeatherAPILinkTest() throws IOException {
        InputStream weatherData = getClass().getResourceAsStream("/edu/bsu/cs/weatherContext.json");
        APIDataParser apiDataParser = new APIDataParser();
        String actualUrl = apiDataParser.parseWeatherAPILink(weatherData, "forecastHourly");
        String expectedUrl = "https://api.weather.gov/gridpoints/IND/83,90/forecast/hourly";
        Assertions.assertEquals(
                expectedUrl,
                actualUrl.replace("\\/", "/")
        );

    }

    @Test
    public void WeatherInformationForecastTest() throws IOException {
        InputStream weatherData = getClass().getResourceAsStream("/edu/bsu/cs/dailyWeatherForecast.json");
        Assertions.assertNotNull(weatherData);
        APIDataParser apiDataParser = new APIDataParser();
        ArrayList<String> trueArray = new ArrayList<>(Arrays.asList(
                "Temperature: 44",
                "Precipitation: 0%",
                "Wind Speed & Direction: 5 mph NE"
        ));
        apiDataParser.setWeatherData(weatherData);
        apiDataParser.forecastData();
        HashMap<String, ArrayList<String>> dailyForecast = apiDataParser.getDailyForecast();
        ArrayList<String> actualArray = dailyForecast.get("1");
        Assertions.assertEquals(trueArray, actualArray);
    }
}
