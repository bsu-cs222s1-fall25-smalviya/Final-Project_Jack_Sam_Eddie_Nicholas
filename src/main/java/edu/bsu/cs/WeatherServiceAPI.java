package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class WeatherServiceAPI {
    protected String createURLString(String latLong) {
        return "https://api.weather.gov/points/" + latLong;
    }

    protected InputStream getInputStreamFromURL(String urlString) throws IOException {
        URI uri = URI.create(urlString);
        URLConnection connection = uri.toURL().openConnection();
        return connection.getInputStream();
    }

    protected String createAlertsURLString(String latLong) {
        return "https://api.weather.gov/alerts/active?point=" + latLong;
    }

}