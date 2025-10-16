import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;

package edu.bsu.cs;

public class WeatherServiceAPI {

    //Ensure you have proper internet connection in order to receive a connection to the API
    protected URLConnection connectToGovernmentWeatherAPI(String title) throws IOException, URISyntaxException {
        try {
            String URL = "https://api.weather.gov/points/40.1933,-85.3863" +
                    URLEncoder.encode(title, Charset.defaultCharset()) + "&rvprop=timestamp" + URLEncoder.encode("|", Charset.defaultCharset()) +
                    "user&rvlimit=15&redirects";
            URI uri = new URI(URL);
            URLConnection connection = uri.toURL().openConnection();
            connection.setRequestProperty("User-Agent", "Final-Project_Jack_Sam_Eddie_Nicholas/0.1 (Nicholas.braeman@bsu.edu)");
            connection.connect();
            return connection;
        } catch (UnknownHostException e){

            System.err.println("Connection refused: " + e.getMessage());
            return null;
        }
    }
}