package edu.bsu.cs;

import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DataFormatter;
import java.io.InputStream;

public class CitiesDatabaseParser {
    private final HashMap<String, List<String>> zipCodeToCoordinates = new HashMap<>();

    //At start of initialization immediately create HashMap values
    public CitiesDatabaseParser() throws IOException {
        InputStream excelStream = getClass().getResourceAsStream("/edu/bsu/cs/uscities.xlsx");
        assert excelStream != null;

        Workbook workbook = WorkbookFactory.create(excelStream);
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();

        //Goes through each row of the Excel file
        for (int rowCount =  1; rowCount <= sheet.getLastRowNum(); rowCount++){
            Row row  = sheet.getRow(rowCount);

            //Gets information from only the relevant columns
            String zipCodeCell = dataFormatter.formatCellValue(row.getCell(15));
            String latitudeCell = dataFormatter.formatCellValue(row.getCell(6));
            String longitudeCell = dataFormatter.formatCellValue(row.getCell(7));
            String populationCell = dataFormatter.formatCellValue(row.getCell(8));

            //Gives each individual zip code coordinates
            String[] zipCodes = zipCodeCell.split("\\s+");
            for (String zipCode : zipCodes) {
                if (!zipCodeToCoordinates.containsKey(zipCode)) {
                    zipCodeToCoordinates.computeIfAbsent(zipCode, _ -> new ArrayList<>())
                            .addAll(Arrays.asList((latitudeCell + "," + longitudeCell),  populationCell));
                } else {
                    int storedPopulation = Integer.parseInt(zipCodeToCoordinates.get(zipCode).get(1));

                    if (Integer.parseInt(populationCell) > storedPopulation) {
                        List<String> information = new ArrayList<>(List.of(latitudeCell + "," + longitudeCell, populationCell));
                        zipCodeToCoordinates.put(zipCode,information);
                    }

                }
            }
        }
    }

    public String getCoordinates(String zipCode) {
        if (!zipCodeToCoordinates.containsKey(zipCode)) {
            return "Error";
        }
        List<String> coordinates = zipCodeToCoordinates.get(zipCode);
        return coordinates.getFirst();
    }
}
