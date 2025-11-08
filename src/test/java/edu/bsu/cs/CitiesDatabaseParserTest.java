package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CitiesDatabaseParserTest {

    //Tests to see if the program could correctly return correct coordinates
    @Test
    public void getCoordinatesTest() throws IOException {
        String expectedLatLong = "39.8318,-84.8905";
        CitiesDatabaseParser cities = new CitiesDatabaseParser();
        String actualLatLong = cities.getCoordinates("47374");
        Assertions.assertEquals(expectedLatLong, actualLatLong);
    }
}
