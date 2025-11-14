package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;

public class WeatherServiceAPITest {
    WeatherServiceAPI service = new WeatherServiceAPI();

    @Test
    public void testGetInputStreamFromURL() throws IOException {
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


    @Test
    public void testWeatherAlertsAPI() throws IOException, ParseException {
        WeatherServiceAPI service = new WeatherServiceAPI();
        String latLong = "39.7456,-97.0892";
        String urlString = service.createAlertsURLString(latLong);

        InputStream connection = service.getInputStreamFromURL(urlString);

        if (connection == null) {
            Assertions.fail("Connection is null – failed to connect to the alerts API.");
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);
        JSONObject json = (JSONObject) parser.parse(response.toString());

        //the API should return JSON object containing features
        Assertions.assertTrue(json.containsKey("features"));
    }
}