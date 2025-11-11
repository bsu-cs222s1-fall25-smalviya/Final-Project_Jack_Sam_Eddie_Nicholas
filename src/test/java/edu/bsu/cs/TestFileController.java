package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TestFileController {
    FileController fileIO = new FileController();

    @Test
    public void testSavePreferences() throws IOException {
        String[] expectedPreferences = {"false", "indianapolis", "metric"};
        fileIO.savePreferences(expectedPreferences);
        String[] actualPreferences = fileIO.loadPreferences();

        int i;
        for (i=0;i<actualPreferences.length;i++) {
            Assertions.assertEquals(expectedPreferences[i], actualPreferences[i]);
        }
        fileIO.resetPreferences();
    }

    @Test
    public void testResetPreferences() throws IOException {
        String[] expectedPreferences = {"false", "40.1933,-85.3863","imperial"};
        fileIO.resetPreferences();
        String[] actualPreferences = fileIO.loadPreferences();
        for (int i=0;i<actualPreferences.length;i++) {
            Assertions.assertEquals(expectedPreferences[i], actualPreferences[i]);
        }
    }

    @Test
    public void testLoadPreferences() throws IOException {
        String[] expectedPreferences = {"false", "40.1933,-85.3863", "imperial"};
        String[] actualPreferences = fileIO.loadPreferences();
        int i;
        for (i=0; i< actualPreferences.length; i++) {
            Assertions.assertEquals(expectedPreferences[i], actualPreferences[i]);
        }
    }

    @Test
    public void testLoadCities() throws FileNotFoundException {
        ArrayList<Pair> expectedCitiesList = new ArrayList<>();
        expectedCitiesList.add(new Pair("Muncie, IN", "40.1933,-85.3863"));
        expectedCitiesList.add(new Pair("Indianapolis, IN", "39.791,-86.148"));
        expectedCitiesList.add(new Pair("Hoboken, NJ", "40.7128,-74.006"));
        expectedCitiesList.add(new Pair("Florida City, FL", "25.45,-80.49"));

        ArrayList<Pair> actualCitiesList = fileIO.loadCities();

        int i;
        for (i=0; i<expectedCitiesList.size(); i++) {
            Assertions.assertEquals(expectedCitiesList.get(i).getName(), actualCitiesList.get(i).getName());
            Assertions.assertEquals(expectedCitiesList.get(i).getLatLong(), actualCitiesList.get(i).getLatLong());
        }
    }
}
