package edu.bsu.cs;

import java.io.IOException;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DataFormatter;
import java.io.InputStream;

public class CitiesDatabaseParser {
    private final HashMap<String, String> zipCodeToCoordinates = new HashMap<>();

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

            //Gives each individual zip code coordinates
            String[] zipCodes = zipCodeCell.split("\\s+");
            for (String zipCode : zipCodes) {

                zipCodeToCoordinates.put(zipCode, latitudeCell + "," + longitudeCell);
            }
        }
    }

    public String getCoordinates(String zipCode) {
        return zipCodeToCoordinates.get(zipCode);
    }
}
