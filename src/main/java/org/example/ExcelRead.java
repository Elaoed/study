package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.IOUtils;

import java.io.FileInputStream;
import java.io.InputStream;

public class ExcelRead {

    public static void main(String[] args) {
        IOUtils.setByteArrayMaxOverride(400000000);
        try (InputStream inp = new FileInputStream("/Users/Ren/Downloads/1301西药中成药220428.xlsx")) {
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(2);
            Cell cell = row.getCell(3);
            System.out.println(cell.getStringCellValue());
            // Write the output to a file
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
