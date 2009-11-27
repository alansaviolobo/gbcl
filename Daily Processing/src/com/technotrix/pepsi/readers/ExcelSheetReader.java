package com.technotrix.pepsi.readers;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class ExcelSheetReader implements SheetReader {
    private HSSFSheet sheet;

    public ExcelSheetReader(String fileName, String sheetName) throws FileNotFoundException, IOException {
        POIFSFileSystem fileSystem =
                new POIFSFileSystem(new FileInputStream(fileName));
        HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
        sheet = workbook.getSheet(sheetName);
    }

    public String getCellValue(int rowNumber, short column) {
        HSSFRow currentRow = sheet.getRow(rowNumber);
        HSSFCell cell = currentRow.getCell(column);
        return cell.toString();
    }

    public Date getDateValue(int rowNumber, short column)
    {
        HSSFRow currentRow = sheet.getRow(rowNumber);
        HSSFCell cell = currentRow.getCell(column);
        return cell.getDateCellValue();
    }
}
