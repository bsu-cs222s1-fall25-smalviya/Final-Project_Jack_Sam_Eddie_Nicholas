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
        //TODO: connection.connect(); potentially redundant
        connection.connect();
        return connection.getInputStream();
    }
}