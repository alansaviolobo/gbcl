package com.technotrix.pepsi.readers;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class ExcelSheetWriter implements SheetWriter {
    private HSSFWorkbook workBook;
    private HSSFSheet sheet;

    public ExcelSheetWriter(String fileName, String sheetName) throws IOException {
        POIFSFileSystem fileSystem =
                new POIFSFileSystem(new FileInputStream(fileName));
        workBook = new HSSFWorkbook(fileSystem);
        sheet = workBook.getSheet(sheetName);
    }

    public void setFloatCellValue(int row, int column, float value) {
        sheet.createRow(row).createCell((short)column).setCellValue(value);
    }

    public void setDateCellValue(int row, int column, Date value) {
        sheet.createRow(row).createCell((short)column).setCellValue(value);
    }

    public void setDateValue(int rowNumber, short column)
    {
        HSSFRow currentRow = sheet.getRow(rowNumber);
        HSSFCell cell = currentRow.getCell(column);
    }

    public void save(String filename) throws IOException
    {
        FileOutputStream fileOut = new FileOutputStream(filename);
        workBook.write(fileOut);
        fileOut.close();
    }
}