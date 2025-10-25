import java.io.IOException;
import java.net.*;

public class WeatherServiceAPI {
    protected String createURLString(String latLong) {
        return "https://api.weather.gov/points/" + latLong;
    }

    protected URLConnection getConnectionFromURL(String urlString) throws IOException {
        URI uri = URI.create(urlString);
        URLConnection connection = uri.toURL().openConnection();
        return connection;
    } //end of URLConnection
} //end of WeatherServiceAPI class