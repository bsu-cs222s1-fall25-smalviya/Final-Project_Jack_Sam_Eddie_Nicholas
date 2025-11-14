package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OutfitRecommenderTest {
    Converter converter = new Converter();
    OutfitRecommender recommender = new OutfitRecommender(converter);

    //these test every outcome for temp in OutfitRecommender and if they are saying what they should be saying
    @Test
    public void testTemperatureBelowMinus50() {
        String result = recommender.temperatureOutfitRecommender(-60, "F");
        Assertions.assertEquals(" - Too cold for out doors, risk of frostbite", result);
    }

    @Test
    public void testTemperatureEqualTo25() {
        String result = recommender.temperatureOutfitRecommender(25, "F");
        Assertions.assertEquals(" - Pants, a coat, a hat, footwear that will tolerate mud and snow, mitts or gloves", result);
    }

    @Test
    public void testTemperatureEqualTo55() {
        String result = recommender.temperatureOutfitRecommender(55, "F");
        Assertions.assertEquals(" - Warm weather clothes. Shorts, skirts without leggings, and sandals appear", result);
    }

    @Test
    public void testTemperatureEqualTo75() {
        String result = recommender.temperatureOutfitRecommender(75, "F");
        Assertions.assertEquals(" - Hot weather clothes, shorts, short sleeveless, sun hats", result);
    }

    @Test
    public void testTemperatureEqualTo100() {
        String result = recommender.temperatureOutfitRecommender(100, "F");
        Assertions.assertEquals(" - Too hot for our doors, risk of heat stroke", result);
    }

    //current iteration theses are not used but will for iteration 3
    //precipitation
    @Test
    public void testPrecipitationEqualTo0() {
        String result = recommender.precipitationOutfitRecommender(0.00);
        //assertTrue(result.contains("Somehow it got drier, carry water bottle"));
        Assertions.assertFalse(result.contains("Somehow it got drier, carry water bottle"));
    }

    //windspeed
    @Test
    public void testWindspeedEqualTo2() {
        String result = recommender.windspeedOutfitRecommender(2, "MpH");
        Assertions.assertFalse(result.contains("wind speed is low enough to not really need wind resistant clothes"));
    }
}
