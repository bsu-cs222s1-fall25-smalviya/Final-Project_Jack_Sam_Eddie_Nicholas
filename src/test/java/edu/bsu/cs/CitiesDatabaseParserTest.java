package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CitiesDatabaseParserTest {

    @Test
    public void getCoordinatesTest(){
        String expectedLatLong = "39.8318,-84.8905";
        USCitiesDatabaseParser cities = new USCitiesDatabaseParser();
        String actualLatLong = cities.getCoordinates("47374");
        Assertions.assertEquals(expectedLatLong, actualLatLong);
    }
}
