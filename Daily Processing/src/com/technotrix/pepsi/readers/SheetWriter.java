package com.technotrix.pepsi.readers;

import java.io.IOException;
import java.util.Date;

public interface SheetWriter {
    public void setFloatCellValue(int row, int column, float value);
    public void setDateValue(int row, short column);
    public void save(String filename) throws IOException;
}