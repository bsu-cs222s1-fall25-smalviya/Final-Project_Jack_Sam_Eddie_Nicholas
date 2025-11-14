package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutfitRecommenderTest {
//these test every out come for temp in OutfitRecommender and if they are saying what they should be saying
    @Test
    void testTemperatureBelowMinus50() {
        var converter = new Converter();
        var recommender = new OutfitRecommender(converter);

        String result = recommender.temperatureOutfitRecommender(-60, "F");
        assertEquals("Too cold for out doors, risk of frostbite", result);
    }

    @Test
    void testTemperatureEqualTo25() {
        var converter = new Converter();
        var recommender = new OutfitRecommender(converter);

        String result = recommender.temperatureOutfitRecommender(25, "F");
        assertEquals("pants, a coat, a hat, footwear that will tolerate mud and snow, mitts or gloves", result);
    }

    @Test
    void testTemperatureEqualTo55() {
        var converter = new Converter();
        var recommender = new OutfitRecommender(converter);

        String result = recommender.temperatureOutfitRecommender(55, "F");
        assertEquals("warm weather clothes. Shorts, skirts without leggings, and sandals appear", result);
    }

    @Test
    void testTemperatureEqualTo75() {
        var converter = new Converter();
        var recommender = new OutfitRecommender(converter);

        String result = recommender.temperatureOutfitRecommender(75, "F");
        assertEquals("hot weather clothes, shorts, short sleeveless, sun hats", result);
    }

    @Test
    void testTemperatureEqualTo100() {
        var converter = new Converter();
        var recommender = new OutfitRecommender(converter);

        String result = recommender.temperatureOutfitRecommender(100, "F");
        assertEquals("Too hot for our doors, risk of heat stroke", result);
    }

    //current iteration theses are not used but will for iteration 3
    //percipation
    @Test
    void testPrecipitationEqualTo0() {
        var converter = new Converter();
        var recommender = new OutfitRecommender(converter);

        String result = recommender.precipitationOutfitRecommender(0.00);
        //assertTrue(result.contains("Somehow it got drier, carry water bottle"));
        assertFalse(result.contains("Somehow it got drier, carry water bottle"));

    }

    //windspeed
    @Test
    void testWindspeedEqualTo2() {
        var converter = new Converter();
        var recommender = new OutfitRecommender(converter);

        String result = recommender.windspeedOutfitRecommender(2, "MpH");
        assertFalse(result.contains("wind speed is low enough to not really need wind resistant clothes"));
    }


}
