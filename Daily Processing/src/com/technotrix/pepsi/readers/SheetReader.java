package com.technotrix.pepsi.readers;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Nov 21, 2009
 * Time: 3:19:43 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SheetReader {
    public String getCellValue(int rowNumber, short column);
  public Date getDateValue(int rowNumber, short column);
}
