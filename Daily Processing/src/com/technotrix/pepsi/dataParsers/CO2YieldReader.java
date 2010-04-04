package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.CO2Yield;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class CO2YieldReader extends BaseParser {
    public CO2YieldReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public CO2Yield parse() throws ParseException {
        CO2Yield co2Yield = new CO2Yield();
        co2Yield.setDate(getDateValueForCell(2, C));
        co2Yield.setCo2Yield(getFloatValueForCell(0, A));
        return co2Yield;
    }
}
