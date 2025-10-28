package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TestTerminalController {
    TerminalController terminalController = new TerminalController();
    FileController fileController = new FileController();

    //TODO: figure out how to assert this
    @Test
    public void testPrintMessages() throws FileNotFoundException {
        ArrayList<Pair> locations = fileController.loadCities();

        terminalController.printWelcomeMessage();
        terminalController.printLocations(locations);
        terminalController.printInvalidResponse();
    }

    @Test
    public void testGetUserChoice(){
        String simulatedInput = "0";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        String choice = terminalController.getUserChoice();

        Assertions.assertEquals(simulatedInput, choice);
    }

    @Test
    public void testGetPreferencePreferences(){
        String simulatedInput = "nothing";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        String preference = terminalController.getPreferencePreferences();
        Assertions.assertEquals("false", preference);

        simulatedInput = "Y";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        preference = terminalController.getPreferencePreferences();
        Assertions.assertEquals("true", preference);

        simulatedInput = "N";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        preference = terminalController.getPreferencePreferences();
        Assertions.assertEquals("false", preference);
    }

    @Test
    public void testGetLocationPreference(){
        ArrayList<Pair> locations = new ArrayList<>();
        Pair indianapolis = new Pair("Indianapolis", "39.791,-86.148");
        locations.add(indianapolis);

        String simulatedInput = "nothing";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        String preference = terminalController.getLocationPreference(locations);
        Assertions.assertEquals("40.1933,-85.3863", preference);

        simulatedInput = "indianapolis";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        preference = terminalController.getLocationPreference(locations);
        Assertions.assertEquals("39.791,-86.148", preference);
    }

    @Test
    public void testGetUnitPreference(){
        String simulatedInput = "nothing";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        String preference = terminalController.getUnitPreference();
        Assertions.assertEquals("imperial", preference);

        simulatedInput = "i";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        preference = terminalController.getUnitPreference();
        Assertions.assertEquals("imperial", preference);

        simulatedInput = "m";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        preference = terminalController.getUnitPreference();
        Assertions.assertEquals("metric", preference);
    }

    @Test
    public void testGetForecastType(){
        String simulatedInput = "nothing";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        String preference = terminalController.getForecastType();
        Assertions.assertEquals("daily", preference);

        simulatedInput = "d";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        preference = terminalController.getForecastType();
        Assertions.assertEquals("daily", preference);

        simulatedInput = "h";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        preference = terminalController.getForecastType();
        Assertions.assertEquals("hourly", preference);
    }

    @Test
    public void testIsUserDone(){
        String simulatedInput = "nothing";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        boolean preference = terminalController.isUserDone();
        Assertions.assertFalse(preference);

        simulatedInput = "y";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        preference = terminalController.isUserDone();
        Assertions.assertTrue(preference);

        simulatedInput = "n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        preference = terminalController.isUserDone();
        Assertions.assertFalse(preference);
    }
}