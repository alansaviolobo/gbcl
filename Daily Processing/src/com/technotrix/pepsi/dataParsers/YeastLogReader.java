package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.YeastLog;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class YeastLogReader extends BaseParser {
    public YeastLogReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public YeastLog parse() throws ParseException {
        YeastLog yeastLog = new YeastLog();
        yeastLog.setDate(getDateValueForCell(2, C));
        yeastLog.setYeastLog(getFloatValueForCell(0, A));
        return yeastLog;
    }
}
