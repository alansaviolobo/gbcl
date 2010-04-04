package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.MaterialAvailability;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class MaterialAvailabilityReader extends BaseParser {
    public MaterialAvailabilityReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public MaterialAvailability parse() throws ParseException {
        MaterialAvailability materialAvailability = new MaterialAvailability();
        materialAvailability.setDate(getDateValueForCell(2, C));
        materialAvailability.setMaterialAvailability(getFloatValueForCell(0, A));
        return materialAvailability;
    }
}
