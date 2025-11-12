package edu.bsu.cs;

import java.io.Console;

public class OutfitRecommender {
    private final Converter converter;

    //calls on the converter to simplify temp useage like C to F
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

protected String precipitationOutfitRecommender(double precipitation) {
    String precipitationrecommendation = "";
    //chance of water falling from the sky, ice, snow, rain
    if (precipitation < 0.00) {
        precipitationrecommendation = "Somehow it got drier, carry water bottle";
    } else if (precipitation <= 0.00) {
        precipitationrecommendation = "Dress for the temperature and winds.";
    } else if (precipitation <= 50.00) {
        precipitationrecommendation = "make sure to carry some rain gear like a rain jacket and a jacket as it have 50/50 chance for rain.";
    } else {
        precipitationrecommendation = "wear carry rain gear like umbrella, boots, pants,  and try to find shelter as best you can to prevent getting wett";
    }
    return precipitationrecommendation;
}

//https://www.snickersworkwear.com/list/product-guide/dress-for-windy-weather
    //found info here
protected String windspeedOutfitRecommender(double windspeed, String unit) {
    String windspeedRecommendation = "";
    //if unit is kilometers, convert to miles
//miles per secound
    if (windspeed < 2.00) {
        windspeedRecommendation = "wind speed is low enough to not really need wind resistant clothes";
    } else if (windspeed <= 6.00) {
        windspeedRecommendation = "Dress for the temperature";
    } else if (windspeed <= 10.00) {
        windspeedRecommendation = "starting to get cold, dress for 50 degrees fahrenheit";
    } else if (windspeed <= 14.00) {
        windspeedRecommendation = "start wearing some wind resistant clothes";
    } else if (windspeed <= 18.00) {
        windspeedRecommendation = "look for breath ability features of your workwear to secure it can ventilate your body heat, layer up and wear cold weather gear";
    } else {
        windspeedRecommendation = "try to find shelter as best you can to prevent wind from ripping through your layers,look for breath ability features of your workwear to secure it can ventilate your body heat, layer up and wear cold weather gear";
    }
    return windspeedRecommendation;
    }
}

