package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.FurnaceOil;
import com.technotrix.pepsi.readers.ExcelSheetReader;

import java.text.ParseException;

public class FurnaceOilReader extends BaseParser {
    public FurnaceOilReader(ExcelSheetReader sheetReader) {
        super(sheetReader);
    }

    public FurnaceOil parse() throws ParseException {
        FurnaceOil furnaceOil = new FurnaceOil();
        furnaceOil.setDate(getDateValueForCell(2, C));
        furnaceOil.setFurnaceOil(getFloatValueForCell(0, A));
        return furnaceOil;
    }
}
