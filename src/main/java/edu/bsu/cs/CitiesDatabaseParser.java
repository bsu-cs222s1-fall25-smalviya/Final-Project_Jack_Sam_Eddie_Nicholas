package edu.bsu.cs;

import java.io.IOException;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.*;
import java.io.InputStream;

public class CitiesDatabaseParser {
    private HashMap<String, String> zipCodeToCoordinates = new HashMap<>();

    //At start of initialization immediately create HashMap values
    public CitiesDatabaseParser() throws IOException {
        InputStream excelStream = getClass().getResourceAsStream("/cities.xlsx");
        assert excelStream != null;

        Workbook workbook = WorkbookFactory.create(excelStream);
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();

    }
}
