package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.SugarYield;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class SugarYieldReader extends BaseParser {
    public SugarYieldReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public SugarYield parse() throws ParseException {
        SugarYield sugarYield = new SugarYield();
        sugarYield.setDate(getDateValueForCell(5, B));
        sugarYield.setSugarYield(getFloatValueForCell(0, A));
        return sugarYield;
    }
}
