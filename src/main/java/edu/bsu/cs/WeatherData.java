package edu.bsu.cs;

public class WeatherData {
    int temp;
    int humidityPercent;
    int precipitationPercent;
    int windSpeed;
    String windDirection;
    String cloudCover;
    public WeatherData(int temp, int humidityPercent, int precipitationPercent,
                       int windSpeed, String windDirection, String cloudCover){
        this.temp = temp;
        this.humidityPercent = humidityPercent;
        this.precipitationPercent = precipitationPercent;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.cloudCover = cloudCover;
    }

}
