package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPair {
    Pair locationLatLong = new Pair("Muncie", "40.1933,-85.3863");

    @Test
    public void testGetLeft(){
        String city = locationLatLong.getName();
        Assertions.assertEquals("Muncie", city);
    }

    @Test
    public void testGetRight(){
        String latLong = locationLatLong.getLatLong();
        Assertions.assertEquals("40.1933,-85.3863", latLong);
    }
}
