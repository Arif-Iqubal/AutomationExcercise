package com.automationexercise.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

public class ExcelUtils {

    private Workbook workbook;
    private Sheet sheet;

    private static final Logger log =
            LogManager.getLogger(ExcelUtils.class);

    // Constructor
    public ExcelUtils(String filePath, String sheetName) {
        try {
            log.info("Opening Excel file: " + filePath);

            FileInputStream fis = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);

            log.info("Using sheet: " + sheetName);

        } catch (IOException e) {
            log.error("Error loading Excel file", e);
        }
    }

    // Get total number of rows
    public int getRowCount() {
        int rows = sheet.getLastRowNum();
        log.info("Total rows found: " + rows);
        return rows;
    }

    // Get total number of columns
    public int getColumnCount() {
        int cols = sheet.getRow(0).getLastCellNum();
        log.info("Total columns found: " + cols);
        return cols;
    }

    // Read cell data
    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        String value = cell.toString();
        log.info("Reading cell [" + rowNum + "," + colNum + "] = " + value);
        return value;
    }

    // Convert entire sheet to Object[][]
    public Object[][] getSheetData() {
        int rowCount = getRowCount();
        int colCount = getColumnCount();

        log.info("Converting sheet data to Object[][]");

        Object[][] data = new Object[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = getCellData(i, j);
            }
        }

        log.info("Excel data loaded successfully");
        return data;
    }
}
