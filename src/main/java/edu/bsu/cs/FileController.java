package edu.bsu.cs;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileController {
    protected void savePreferences(String preferences) throws IOException {
        File file = new File("src/main/resources/edu/bsu/cs/Preferences.txt");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(preferences);
        bw.close();
    }

    protected String loadPreferences() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/edu/bsu/cs/Preferences.txt"));
        return scanner.nextLine();
    }

    protected ArrayList<Pair> loadCities() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/edu/bsu/cs/Cities.txt"));
        String cityString;
        ArrayList<Pair> cities = new ArrayList<>();

        while(scanner.hasNextLine()){
            cityString = scanner.nextLine();
            String[] cityInfo = cityString.split(";");
            Pair city = new Pair(cityInfo[0], cityInfo[1]);
            cities.add(city);
        }
        return cities;
    }
}
