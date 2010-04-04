package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.Brix;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class BrixReader extends BaseParser {
    public BrixReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public Brix parse() throws ParseException {
        Brix brix = new Brix();
        brix.setDate(getDateValueForCell(2, C));
        brix.setBrix(getFloatValueForCell(0, A));
        return brix;
    }
}
