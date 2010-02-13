package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.ConcentrateYield;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class ConcentrateYieldReader extends BaseParser {
    public ConcentrateYieldReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public ConcentrateYield parse() throws ParseException {
        ConcentrateYield concentrateYield = new ConcentrateYield();
        concentrateYield.setDate(getDateValueForCell(5, C));
        concentrateYield.setConcentrateYield(getFloatValueForCell(0, A));
        return concentrateYield;
    }
}
