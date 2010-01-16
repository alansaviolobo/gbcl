package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.InjuryFrequency;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class InjuryFrequencyReader extends BaseParser {
    public InjuryFrequencyReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public InjuryFrequency parse() throws ParseException {
        InjuryFrequency injuryFrequency = new InjuryFrequency();
        injuryFrequency.setDate(getDateValueForCell(4, D));
        injuryFrequency.setInjuryFrequency(getFloatValueForCell(0, A));
        return injuryFrequency;
    }
}
