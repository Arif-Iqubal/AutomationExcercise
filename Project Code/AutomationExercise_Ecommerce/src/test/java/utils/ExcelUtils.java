package utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	  
    private static final String EXCEL_PATH = "/testdata/AutomationExercise_TestData.xlsx";

    // -------------------- Common workbook loader --------------------
    private static Workbook getWorkbook() {
        try {
            InputStream is = ExcelUtils.class.getResourceAsStream(EXCEL_PATH);
            if (is == null) {
                throw new RuntimeException("Excel file not found at: " + EXCEL_PATH);
            }
            return new XSSFWorkbook(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load Excel: " + e.getMessage());
        }
    }
//make sure string value returned
    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }
//make sure integer value returned
    private static int getIntCellValue(Cell cell) {
        if (cell == null) return 0;

        if (cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        }

        String val = getCellValue(cell);
        if (val.isEmpty()) return 0;

        return Integer.parseInt(val);
    }

    private static Map<String, Integer> getHeaderMap(Sheet sheet) {
        Map<String, Integer> map = new HashMap<>();
        Row header = sheet.getRow(0);

        for (Cell cell : header) {
            String colName = getCellValue(cell);
            map.put(colName, cell.getColumnIndex());
        }
        return map;
    }

    // -------------------- ProductsData --------------------
    public static List<String> getProductsToAdd(String tcId) {
        List<String> products = new ArrayList<>();

        try (Workbook wb = getWorkbook()) {
        	
//        	System.out.println("Sheets available:");
//        	for (int i = 0; i < wb.getNumberOfSheets(); i++) {
//        	    System.out.println(wb.getSheetName(i));
//        	}

            Sheet sheet = wb.getSheet("ProductsData");
            Map<String, Integer> h = getHeaderMap(sheet);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String id = getCellValue(row.getCell(h.get("TC_ID")));
                String action = getCellValue(row.getCell(h.get("Action")));
                String productName = getCellValue(row.getCell(h.get("ProductName")));

                if (id.equalsIgnoreCase(tcId) && action.equalsIgnoreCase("ADD")) {
                    products.add(productName);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading ProductsData for " + tcId + ": " + e.getMessage());
        }

        return products;
    }

    public static List<String> getProductsToRemove(String tcId) {
        List<String> products = new ArrayList<>();

        try (Workbook wb = getWorkbook()) {
            Sheet sheet = wb.getSheet("ProductsData");
            Map<String, Integer> h = getHeaderMap(sheet);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String id = getCellValue(row.getCell(h.get("TC_ID")));
                String action = getCellValue(row.getCell(h.get("Action")));
                String productName = getCellValue(row.getCell(h.get("ProductName")));

                if (id.equalsIgnoreCase(tcId) && action.equalsIgnoreCase("REMOVE")) {
                    products.add(productName);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading ProductsData for " + tcId + ": " + e.getMessage());
        }

        return products;
    }

    public static int getQuantityForProduct(String tcId, String productName) {
        try (Workbook wb = getWorkbook()) {
            Sheet sheet = wb.getSheet("ProductsData");
            Map<String, Integer> h = getHeaderMap(sheet);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String id = getCellValue(row.getCell(h.get("TC_ID")));
                String name = getCellValue(row.getCell(h.get("ProductName")));
                String action = getCellValue(row.getCell(h.get("Action")));

                if (id.equalsIgnoreCase(tcId)
                        && name.equalsIgnoreCase(productName)
                        && action.equalsIgnoreCase("ADD")) {

                    return getIntCellValue(row.getCell(h.get("Quantity")));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading quantity for " + tcId + ": " + e.getMessage());
        }

        return 1;
    }

    // -------------------- PaymentData --------------------
    public static PaymentData getPaymentData(String tcId) {

        try (Workbook wb = getWorkbook()) {
            Sheet sheet = wb.getSheet("PaymentData");
            Map<String, Integer> h = getHeaderMap(sheet);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String id = getCellValue(row.getCell(h.get("TC_ID")));

                if (id.equalsIgnoreCase(tcId)) {

                    String name = getCellValue(row.getCell(h.get("NameOnCard")));
                    String number = getCellValue(row.getCell(h.get("CardNumber")));
                    String cvc = getCellValue(row.getCell(h.get("CVC")));
                    String month = getCellValue(row.getCell(h.get("ExpMonth")));
                    String year = getCellValue(row.getCell(h.get("ExpYear")));

                    return new PaymentData(name, number, cvc, month, year);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading PaymentData for " + tcId + ": " + e.getMessage());
        }

        throw new RuntimeException("No payment data found for TC_ID: " + tcId);
    }

    // -------------------- SearchData --------------------
    public static SearchData getSearchData(String tcId) {

        try (Workbook wb = getWorkbook()) {
            Sheet sheet = wb.getSheet("SearchData");
            Map<String, Integer> h = getHeaderMap(sheet);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String id = getCellValue(row.getCell(h.get("TC_ID")));

                if (id.equalsIgnoreCase(tcId)) {

                    String keyword = getCellValue(row.getCell(h.get("SearchKeyword")));
                    int expected = getIntCellValue(row.getCell(h.get("ExpectedMinResults")));

                    return new SearchData(keyword, expected);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading SearchData for " + tcId + ": " + e.getMessage());
        }

        throw new RuntimeException("No search data found for TC_ID: " + tcId);
    }
}
