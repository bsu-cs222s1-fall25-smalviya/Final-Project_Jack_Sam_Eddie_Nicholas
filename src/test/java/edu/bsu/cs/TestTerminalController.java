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
    public void testPrintWelcomeMessage(){
        String expectedPrinted = "Welcome to the CS220 Weather App!";
        terminalController.printWelcomeMessage();
        //Assertions.assert;
    }

    //TODO: figure out how to assert this
    @Test
    public void testPrintLocations() throws FileNotFoundException {
        ArrayList<Pair> locations = fileController.loadCities();
        terminalController.printLocations(locations);
        //Assertions.assert;
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
        Pair indianapolis = new Pair("Indianapolis", "");
        locations.add(indianapolis);

        String simulatedInput = "nothing";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        String preference = terminalController.getLocationPreference(locations);
        Assertions.assertEquals("muncie", preference);

        simulatedInput = "indianapolis";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        preference = terminalController.getLocationPreference(locations);
        Assertions.assertEquals("indianapolis", preference);
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
}