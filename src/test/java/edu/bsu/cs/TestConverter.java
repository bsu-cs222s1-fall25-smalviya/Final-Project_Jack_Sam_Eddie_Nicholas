package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestConverter {
    Converter converter = new Converter();

    @Test
    public void testFahrenheitToCelsiusInt(){
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
    public void testFahrenheitToCelsiusDouble(){
        double fTemp1 = 71.0;
        double fTemp2 = 67.0;
        double fTemp3 = 54.0;

        int cTemp1 = converter.fahrenheitToCelsius(fTemp1);
        int cTemp2 = converter.fahrenheitToCelsius(fTemp2);
        int cTemp3 = converter.fahrenheitToCelsius(fTemp3);

        Assertions.assertEquals(22, cTemp1); // 21.66...
        Assertions.assertEquals(19, cTemp2); // 19.44...
        Assertions.assertEquals(12, cTemp3); // 12.22...
    }


    @Test
    public void testCelsiusToFahrenheitInt() {
        int cTemp1 = 0;
        int cTemp2 = 10;
        int cTemp3 = 5;
        int cTemp4 = -18;
        int cTemp5 = 4; // The previous failing case (rounded)

        int fTemp1 = converter.celsiusToFahrenheit(cTemp1);
        int fTemp2 = converter.celsiusToFahrenheit(cTemp2);
        int fTemp3 = converter.celsiusToFahrenheit(cTemp3);
        int fTemp4 = converter.celsiusToFahrenheit(cTemp4);
        int fTemp5 = converter.celsiusToFahrenheit(cTemp5);


        Assertions.assertEquals(32, fTemp1);
        Assertions.assertEquals(50, fTemp2);
        Assertions.assertEquals(41, fTemp3);
        Assertions.assertEquals(0, fTemp4);
        Assertions.assertEquals(39, fTemp5); // 39.2
    }

    @Test
    public void testCelsiusToFahrenheitDouble() {
        double cTemp1 = 5.0;
        double cTemp2 = 4.444444444444445;

        int fTemp1 = converter.celsiusToFahrenheit(cTemp1);
        int fTemp2 = converter.celsiusToFahrenheit(cTemp2);

        Assertions.assertEquals(41, fTemp1);
        Assertions.assertEquals(40, fTemp2); // (40/9 * 9/5) + 32 = 8 + 32 = 40
    }

    @Test
    public void testMilesToKilometers(){
        int mph1 = 0;
        int mph2 = 10;
        int mph3 = 17;

        int kph1 = converter.milesToKilometers(mph1);
        int kph2 = converter.milesToKilometers(mph2);
        int kph3 = converter.milesToKilometers(mph3);

        Assertions.assertEquals(0, kph1);
        Assertions.assertEquals(16, kph2);
        Assertions.assertEquals(27, kph3);
    }
}