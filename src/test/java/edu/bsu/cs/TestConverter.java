package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestConverter {


    @Test
    public void testFahrenheitToCelsius(){
        int fTemp1 = 0;
        int fTemp2 = 32;
        int fTemp3 = 50;
        int cTemp1 = Converter.fahrenheitToCelsius(fTemp1);
        int cTemp2 = Converter.fahrenheitToCelsius(fTemp2);
        int cTemp3 = Converter.fahrenheitToCelsius(fTemp3);
        Assertions.assertEquals(-18, cTemp1);
        Assertions.assertEquals(0, cTemp2);
        Assertions.assertEquals(10, cTemp3);
    }
}
