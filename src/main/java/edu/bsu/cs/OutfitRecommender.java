package edu.bsu.cs;

import java.io.Console;

public class OutfitRecommender {
    private final Converter converter;

    //calls on the converter to simplify temp useage like C to F
    //it's math is based on F
    public OutfitRecommender(Converter converter) {
        this.converter = converter;
    }

    protected String temperatureOutfitRecommender(double temperature, String unit) {
        if (unit.equalsIgnoreCase("celsius")){
            temperature = converter.celsiusToFahrenheit(temperature);
        }

        String temperatureRecommendation = "";

        //https://www.reddit.com/r/femalefashionadvice/comments/5gk9js/crowd_sourced_guide_on_dressing_with_respect_to/
        //where I got the info
        if (temperature < -50) {
            temperatureRecommendation = " - Too cold for out doors, risk of frostbite";
        } else if (temperature <= -4) {
            temperatureRecommendation = " - Wool, silk, or technical long underwear (pants and shirt), normal pants, several sweaters, at least two layers of wool socks, down or high-quality synthetic lofted snowpants and parka or ankle-length parka, rated snowboots, sunglasses (snow blindness)";
        } else if (temperature <= 14) {
            temperatureRecommendation = " - Long underwear, jeans, wool socks, boots or winter shoes, two long-sleeved shirts, a good insulated windproof coat, a hat, mitts or gloves";
        } else if (temperature <= 32) {
            temperatureRecommendation = " - Pants, a coat, a hat, footwear that will tolerate mud and snow, mitts or gloves";
        } else if (temperature <= 50) {
            temperatureRecommendation = " - A light coat or a sweater. Mostly long pants, and leggings under skirts. No sandals yet.";
        } else if (temperature <= 68) {
            temperatureRecommendation = " - Warm weather clothes. Shorts, skirts without leggings, and sandals appear";
        } else if (temperature <= 86) {
            temperatureRecommendation = " - Hot weather clothes, shorts, short sleeveless, sun hats";
        } else {
            temperatureRecommendation = " - Too hot for our doors, risk of heat stroke";
        }
        return temperatureRecommendation;
    }
}

