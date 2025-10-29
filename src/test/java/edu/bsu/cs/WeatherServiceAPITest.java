package edu.bsu.cs;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;

public class WeatherServiceAPITest {

    @Test
    public void testWeatherServiceAPI() throws IOException, ParseException {
        WeatherServiceAPI service = new WeatherServiceAPI();
        //Muncie’s coordinates for testing
        String latLong = "40.195923,-85.387545";
        String urlString = service.createURLString(latLong);

        InputStream connection = service.getInputStreamFromURL(urlString);

        //if connection is null, it means it failed to grab data from API which means API isn't working, so no data
        if (connection == null) {
            Assertions.fail("Connection is null — failed to connect to the weather API.");
            return; // Stop the test early
        }

        //it goes on only if the connection works
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);
        JSONObject json = (JSONObject) parser.parse(response.toString());

        //the API should return JSON object containing properties
        Assertions.assertTrue(json.containsKey("properties"));
    }
}




