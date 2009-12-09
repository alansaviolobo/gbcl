package com.technotrix.pepsi.readers;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class ExcelSheetWriter implements SheetWriter {
    private HSSFWorkbook workBook;
    private HSSFSheet sheet;
    private String fileName;

    public ExcelSheetWriter(String templateName, String sheetName, String fileName) throws IOException {
        workBook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(templateName)));
        sheet = workBook.getSheet(sheetName);
        this.fileName = fileName;
    }

    public void setFloatCellValue(int row, int column, float value) {
        sheet.createRow(row).createCell((short)column).setCellValue(value);
    }

    public void setDateCellValue(int row, int column, Calendar value) {
        sheet.createRow(row).createCell((short)column).setCellValue(value);
    }

    public void setStringCellValue(int row, int column, String value) {
         sheet.createRow(row).createCell((short)column).setCellValue(new HSSFRichTextString(value));
     }

     public void save() throws IOException
    {
        FileOutputStream fileOut = new FileOutputStream(fileName);
        workBook.write(fileOut);
        fileOut.close();
    }
}