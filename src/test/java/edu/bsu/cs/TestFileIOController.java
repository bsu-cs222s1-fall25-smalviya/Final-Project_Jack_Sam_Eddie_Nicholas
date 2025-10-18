package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TestFileIOController {
    FileIOController fileIO = new FileIOController();

    @Test
    public void testSavePreferences() throws IOException {
        String expectedPreferences = "false, indianapolis, metric";
        fileIO.savePreferences(expectedPreferences);
        String actualPreferences = fileIO.loadPreferences();
        Assertions.assertEquals(expectedPreferences, actualPreferences);

        String defaultPreferences = "true, muncie, imperial";
        fileIO.savePreferences(defaultPreferences);
    }

    @Test
    public void testLoadPreferences() throws FileNotFoundException {
        String expectedPreferences = "true, muncie, imperial";
        String actualPreferences = fileIO.loadPreferences();
        Assertions.assertEquals(expectedPreferences, actualPreferences);
    }

    @Test
    public void testLoadCities() throws FileNotFoundException {
        ArrayList<Pair> expectedCitiesList = new ArrayList<>();
        expectedCitiesList.add(new Pair("muncie", "40.1933,-85.3863"));
        expectedCitiesList.add(new Pair("indianapolis", "39.791,-86.148"));

        ArrayList<Pair> actualCitiesList = fileIO.loadCities();

        int i;
        for (i=0; i<expectedCitiesList.size(); i++) {
            Assertions.assertEquals(expectedCitiesList.get(i).getName(), actualCitiesList.get(i).getName());
            Assertions.assertEquals(expectedCitiesList.get(i).getLatLong(), actualCitiesList.get(i).getLatLong());
        }
    }
}
