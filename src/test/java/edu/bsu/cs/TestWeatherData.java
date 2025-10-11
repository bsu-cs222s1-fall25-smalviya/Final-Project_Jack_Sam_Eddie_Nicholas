package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestWeatherData {
    int temp = 80;
    int humidityPercent = 50;
    int precipitationPercent = 0;
    int windSpeed = 5;
    String windDirection = "NNE";
    String cloudCover = "Sunny";
    WeatherData tempWeatherData = new WeatherData(this.temp, this.humidityPercent,
            this.precipitationPercent, this.windSpeed, this.windDirection, this.cloudCover);



    /*@Test
    public void testConstructor(){
        Assertions.assert
    }*/
}
