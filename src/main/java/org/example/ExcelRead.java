package org.example;

import com.alibaba.fastjson.JSON;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExcelRead {

    public static List<List<String>> getStringSheet(String filePath) {

        List<List<String>> sheetList = new ArrayList<>();
        try (InputStream inp = Files.newInputStream(Paths.get(filePath))) {
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);
            for (Row row : sheet) {
                List<String> rowList = new ArrayList<>();
                for (Cell cell : row) {
                    String s = "";
                    switch (cell.getCellType()) {
                        case _NONE:
                            break;
                        case NUMERIC:
                            s = String.valueOf(cell.getNumericCellValue());
                            break;
                        case STRING:
                            s = cell.getStringCellValue();
                            break;
                        case FORMULA:
                            break;
                        case BLANK:
                            break;
                        case BOOLEAN:
                            break;
                        case ERROR:
                            break;
                    }
                    rowList.add(s);
                }
                sheetList.add(rowList);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sheetList;

    }
    public static void main(String[] args) {
        List<List<String>> stringSheet = getStringSheet("/Users/Ren/Downloads/材料批量上传标准模版.xlsx");
        System.out.println(JSON.toJSONString(stringSheet));


    }
}
