package com.technotrix.pepsi.readers;

import java.io.IOException;
import java.util.Calendar;

public interface SheetWriter {
    public void setFloatCellValue(int row, int column, float value);

    public void setDateCellValue(int row, int column, Calendar value);

    public void setStringCellValue(int row, int column, String value);

    public void save() throws IOException;
}