package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class TestTerminalController {
    TerminalController terminalController = new TerminalController();

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