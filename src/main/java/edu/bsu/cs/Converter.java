package edu.bsu.cs;

public class Converter {
    public static int fahrenheitToCelsius(int fTemp){
        float cTemp = (float) (fTemp-32)*5/9;
        return Math.round(cTemp);
    }

}
