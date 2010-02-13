package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.TotalPlantWaste;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class TotalPlantWasteReader extends BaseParser {
    public TotalPlantWasteReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public TotalPlantWaste parse() throws ParseException {
        TotalPlantWaste totalPlantWaste = new TotalPlantWaste();
        totalPlantWaste.setDate(getDateValueForCell(5, B));
        totalPlantWaste.setTotalPlantWaste(getFloatValueForCell(0, A));
        return totalPlantWaste;
    }
}
