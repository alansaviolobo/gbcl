package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.Water;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class WaterReader extends BaseParser{
    public WaterReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public Water parse() throws ParseException {
        Water water = new Water();
        water.setDate(getDateValueForCell(5, D));
        water.setWater(getFloatValueForCell(0, A));
        return water;
    }
}
