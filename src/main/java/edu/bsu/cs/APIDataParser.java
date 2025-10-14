package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ReadContext;
import net.minidev.json.JSONArray;

public class APIDataParser {

    protected ArrayList<String> WeatherApiParser(InputStream weatherData) throws IOException {
        String json = new String(weatherData.readAllBytes(), StandardCharsets.UTF_8);
        String base = "$.properties.periods[0]";

        Configuration conf = Configuration.builder().options(Option.DEFAULT_PATH_LEAF_TO_NULL, Option.SUPPRESS_EXCEPTIONS).build();
        ReadContext ctx = JsonPath.using(conf).parse(json);

        String temperature   = (ctx.read(base + ".temperature")).toString();
        String precipitation = (ctx.read(base + ".probabilityOfPrecipitation.value") + "%");
        String dewpoint      = (ctx.read(base + ".dewpoint.value")).toString();
        String humidity      = (ctx.read(base + ".relativeHumidity.value") + "%");
        String windSpeed     = (ctx.read(base + ".windSpeed")).toString();
        String windDirection = (ctx.read(base + ".windDirection")).toString();

        return new ArrayList<>(List.of(
                temperature,
                precipitation,
                dewpoint,
                humidity,
                windSpeed + " " + windDirection
        ));
    }


    //Searches for proper JsonPath based on the query you request
    //forecast; forecastHourly
    protected String weatherAPILinkParser(InputStream weatherData, String weatherQuery) throws IOException {
        String searchQuery = "$..properties." + weatherQuery;
        JSONArray queryResult;
        queryResult = JsonPath.read(weatherData, searchQuery);
        return queryResult.getFirst().toString();
    }
}
