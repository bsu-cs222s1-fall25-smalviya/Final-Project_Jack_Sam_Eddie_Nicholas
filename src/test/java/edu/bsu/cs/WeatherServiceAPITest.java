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
    WeatherServiceAPI service = new WeatherServiceAPI();

    @Test
    public void testGetInputStreamFromURL() throws IOException, ParseException {
        //Muncie’s coordinates for testing
        String latLong = "40.195923,-85.387545";
        String urlString = service.createURLString(latLong);

        InputStream connection = service.getInputStreamFromURL(urlString);

        Assertions.assertNotNull(connection);
    }

    @Test
    public void testCreateURLString(){
        String expectedURL = "https://api.weather.gov/points/40.1933,-85.3863";
        String actualURL = service.createURLString("40.1933,-85.3863");
        Assertions.assertEquals(expectedURL, actualURL);
    }
}




