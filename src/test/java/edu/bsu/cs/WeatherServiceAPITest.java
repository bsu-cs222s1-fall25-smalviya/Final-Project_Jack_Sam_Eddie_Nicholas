package edu.bsu.cs;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;

//testing connection to URL and JSON in WeatherServiceAPI.java
public class WeatherServiceAPITest {

    @Test
    public void testWeatherServiceAPI() throws IOException{
        WeatherServiceAPI service = new WeatherServiceAPI();
        //Muncie's coordination for testing
        String latLong = "40.195923,85.387545";
        String urlString = service.createURLString(latLong);

        URLConnection connection = service.getConnectionFromURL(urlString);

        Assertions.assertNotNull(connection);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        } //end of while
        reader.close();

        JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);
        JSONObject json = (JSONObject) parser.parse(response.toString());

        //API should return JSON object containing "properties"
        Assertions.assertTrue(json.containsKey("properties"));
    } //end of testWeatherServiceAPI
} //end of WeatherServiceAPITest class



