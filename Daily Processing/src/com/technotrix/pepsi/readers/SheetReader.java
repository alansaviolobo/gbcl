package com.technotrix.pepsi.readers;

import java.util.Date;

public interface SheetReader {
    public String getCellValue(int rowNumber, short column);
  public Date getDateValue(int rowNumber, short column);
}
