package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class TestTerminalController {
    TerminalController terminalController = new TerminalController();

    @Test
    public void testGetPreferences(){
        String simulatedInput = "nothing";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        String preferences = terminalController.getPreferences();
        Assertions.assertEquals("blank", preferences);

        simulatedInput = "Y";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        preferences = terminalController.getPreferences();
        Assertions.assertEquals("")
    }

    @Test
    public void testGetTempPreference(){
        String simulatedInput = "nothing";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        String preference = terminalController.getTempPreference();
        Assertions.assertEquals("fahrenheit", preference);

        simulatedInput = "f";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        preference = terminalController.getTempPreference();
        Assertions.assertEquals("fahrenheit", preference);

        simulatedInput = "c";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        preference = terminalController.getTempPreference();
        Assertions.assertEquals("celsius", preference);
    }
}