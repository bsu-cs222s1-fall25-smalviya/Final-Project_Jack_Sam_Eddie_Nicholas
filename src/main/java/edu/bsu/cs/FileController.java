package edu.bsu.cs;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileController {
    protected void savePreferences(String[] preferences) throws IOException {
        File file = new File("src/main/resources/edu/bsu/cs/Preferences.txt");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        StringBuilder sb = new StringBuilder();

        int i;
        for (i=0;i<3;i++){
            sb.append(preferences[i]).append(";");
        }

        bw.write(String.valueOf(sb));
        bw.close();
    }

    protected String[] loadPreferences() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/edu/bsu/cs/Preferences.txt"));
        String preferences = scanner.nextLine();
        return preferences.split(";");
    }

    protected void resetPreferences() throws IOException {
        this.savePreferences(new String[] {"false","40.1933,-85.3863","imperial"});
    }
}
