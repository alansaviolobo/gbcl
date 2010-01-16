package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseParser {
    protected SheetReader sheetReader;
    protected static final short A = 0;
    protected static final short B = 1;
    protected static final short C = 2;
    protected static final short D = 3;

    public BaseParser(SheetReader sheetReader) {
        this.sheetReader = sheetReader;
    }

    protected Date getDateValueForCell(int rowNumber, short columnNumber) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("dd-MM-yy");
        return sheetReader.getDateValue(rowNumber, columnNumber);
    }

    protected Integer getIntegerValueForCell(int rowNumber, short columnNumber) {
        return (int) Float.parseFloat(sheetReader.getCellValue(rowNumber, columnNumber));
    }

    protected Float getFloatValueForCell(int rowNumber, short columnNumber) {
        return Float.parseFloat(sheetReader.getCellValue(rowNumber, columnNumber));
    }
}
