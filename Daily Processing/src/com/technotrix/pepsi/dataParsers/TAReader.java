package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.TA;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class TAReader extends BaseParser {
    public TAReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public TA parse() throws ParseException {
        TA ta = new TA();
        ta.setDate(getDateValueForCell(5, B));
        ta.setTa(getFloatValueForCell(0, A));
        return ta;
    }
}
