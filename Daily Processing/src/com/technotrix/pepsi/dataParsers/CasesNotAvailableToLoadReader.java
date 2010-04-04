package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.CasesNotAvailableToLoad;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class CasesNotAvailableToLoadReader extends BaseParser {

    public CasesNotAvailableToLoadReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public CasesNotAvailableToLoad parse() throws ParseException {
        CasesNotAvailableToLoad casesNotAvailableToLoad = new CasesNotAvailableToLoad();
        casesNotAvailableToLoad.setDate(getDateValueForCell(2, C));
        casesNotAvailableToLoad.setCasesNotAvailableToLoad(getFloatValueForCell(0, A));
        return casesNotAvailableToLoad;
    }
}
