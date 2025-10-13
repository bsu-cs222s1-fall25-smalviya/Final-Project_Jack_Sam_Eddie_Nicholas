package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestConverter {
    ArrayList<Pair> locationPairs = new ArrayList<>();
    Converter converter = new Converter(locationPairs);

    public TestConverter() {
        Pair munciePair = new Pair("Muncie", "40.1933,-85.3863");
        Pair indianapolisPair = new Pair("Indianapolis", "39.7655,-86.1595");

        this.locationPairs.add(munciePair);
        this.locationPairs.add(indianapolisPair);
    }


    @Test
    public void testFahrenheitToCelsius(){
        int fTemp1 = 0;
        int fTemp2 = 32;
        int fTemp3 = 50;

        int cTemp1 = converter.fahrenheitToCelsius(fTemp1);
        int cTemp2 = converter.fahrenheitToCelsius(fTemp2);
        int cTemp3 = converter.fahrenheitToCelsius(fTemp3);

        Assertions.assertEquals(-18, cTemp1);
        Assertions.assertEquals(0, cTemp2);
        Assertions.assertEquals(10, cTemp3);
    }

    @Test
    public void testLocationToLatLong(){
        String indianapolisLatLong = converter.locationToLatLong("Indianapolis");
        String muncieLatLong = converter.locationToLatLong("Muncie");
        String istanbulLatLong = converter.locationToLatLong("Istanbul");

        Assertions.assertEquals("39.7655,-86.1595", indianapolisLatLong);
        Assertions.assertEquals("40.1933,-85.3863", muncieLatLong);
        Assertions.assertNull(istanbulLatLong);
    }
}
