package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.LostTime;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class LostTimeReader extends BaseParser {
    public LostTimeReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public LostTime parse() throws ParseException {
        LostTime lostTime = new LostTime();
        lostTime.setDate(getDateValueForCell(4, D));
        lostTime.setLostTime(getFloatValueForCell(0, A));
        return lostTime;
    }
}
