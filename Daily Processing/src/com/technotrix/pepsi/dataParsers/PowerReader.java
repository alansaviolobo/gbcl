package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.Power;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class PowerReader extends BaseParser {
    public PowerReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public Power parse() throws ParseException {
        Power power = new Power();
        power.setDate(getDateValueForCell(5, D));
        power.setPower(getFloatValueForCell(0, A));
        return power;
    }
}
