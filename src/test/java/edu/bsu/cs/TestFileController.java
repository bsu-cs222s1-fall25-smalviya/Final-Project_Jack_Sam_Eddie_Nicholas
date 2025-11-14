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
}
