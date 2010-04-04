package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.CrownYield;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class CrownYieldReader extends BaseParser {
    public CrownYieldReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public CrownYield parse() throws ParseException {
        CrownYield crownYield = new CrownYield();
        crownYield.setDate(getDateValueForCell(2, C));
        crownYield.setCrownYield(getFloatValueForCell(0, A));
        return crownYield;
    }
}
