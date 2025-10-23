package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.jayway.jsonpath.*;


public class APIDataParser {

    protected HashMap<String, ArrayList<String>> hourlyForecast = new HashMap<>();
    protected HashMap<String, ArrayList<String>> dailyForecast = new HashMap<>();

    private ReadContext ctx;
    private final Configuration conf = Configuration.builder()
            .options(Option.DEFAULT_PATH_LEAF_TO_NULL, Option.SUPPRESS_EXCEPTIONS)
            .build();

    protected void HourlyForecastData(){
        for (int i = 1; i <= 7; i++){
            hourlyForecast.put(String.valueOf(i), ParseWeatherAPIData( i - 1));
        }
    }

    protected void forecastData(){
        for (int i = 1; i <= 8; i++){
            dailyForecast.put(String.valueOf(i), ParseWeatherAPIData(i - 1));
        }
    }



    protected ArrayList<String> ParseWeatherAPIData(int periodQuery){
        String base = "$.properties.periods[" + periodQuery + "]";
        String humidity, dewPointValue;
        Number dewPoint;

        String temperature   = ("Temperature: " + ctx.read(base + ".temperature"));
        String precipitation = ("Precipitation: " + ctx.read(base + ".probabilityOfPrecipitation.value") + "%");
        if (ctx.read(base + ".dewpoint.value") != null) {
            dewPoint = ctx.read(base + ".dewpoint.value", Number.class);
            dewPointValue = "Dew Point: " + String.format("%.2f", dewPoint.doubleValue());
            humidity = ("Humidity: " + ctx.read(base + ".relativeHumidity.value") + "%");
        } else {
            dewPointValue = null;
            humidity = null;
        }
        String windSpeed     = ("Wind Speed & Direction: " + ctx.read(base + ".windSpeed"));
        String windDirection = (ctx.read(base + ".windDirection")).toString();
        return Stream.of(
                        temperature,
                        precipitation,
                        dewPointValue,
                        humidity,
                        windSpeed + " " + windDirection
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    //Searches for proper JsonPath based on the query you request
    //forecast; forecastHourly
    protected String parseWeatherAPILink(InputStream weatherData, String weatherQuery) throws IOException {
        String searchQuery = "$.properties." + weatherQuery;
        return JsonPath.read(weatherData, searchQuery);
    }

    public void setWeatherData(InputStream weatherDataStream) throws IOException{
        String jsonFile = new String(weatherDataStream.readAllBytes(), StandardCharsets.UTF_8);
        this.ctx = JsonPath.using(conf).parse(jsonFile);
    }

    protected HashMap<String, ArrayList<String>> getHourlyForecast() {
        return hourlyForecast;
    }

    protected HashMap<String, ArrayList<String>> getDailyForecast() {
        return dailyForecast;
    }
}
