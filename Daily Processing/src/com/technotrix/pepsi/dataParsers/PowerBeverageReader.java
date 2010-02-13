package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.PowerBeverage;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class PowerBeverageReader extends BaseParser {
    public PowerBeverageReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public PowerBeverage parse() throws ParseException {
        PowerBeverage powerBeverage = new PowerBeverage();
        powerBeverage.setDate(getDateValueForCell(1, A));
        powerBeverage.setPowerBeverage(getFloatValueForCell(1, A));
        return powerBeverage;
    }
}
