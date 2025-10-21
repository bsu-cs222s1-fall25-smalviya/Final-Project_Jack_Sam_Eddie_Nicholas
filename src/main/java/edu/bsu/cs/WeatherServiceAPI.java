package edu.bsu.cs;

import java.io.IOException;
import java.net.*;

public class WeatherServiceAPI {
    protected String createURLString(String latLong) {
        return "https://api.weather.gov/points/" + latLong;
    }

    protected URLConnection getConnectionFromURL(String urlString) throws IOException {
        URI uri = URI.create(urlString);
        URLConnection connection = uri.toURL().openConnection();
        //TODO: connection.connect(); potentially redundant
        connection.connect();
        return connection;
    }
}