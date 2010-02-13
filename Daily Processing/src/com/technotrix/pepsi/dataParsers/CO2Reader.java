package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.CO2;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class CO2Reader extends BaseParser {
    public CO2Reader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public CO2 parse() throws ParseException {
        CO2 cO2 = new CO2();
        cO2.setDate(getDateValueForCell(5, B));
        cO2.setCo2(getFloatValueForCell(0, A));
        return cO2;
    }
}
