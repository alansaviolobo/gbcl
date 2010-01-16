package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.WarehouseBreakages;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class WarehouseBreakagesReader extends BaseParser {

    public WarehouseBreakagesReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public WarehouseBreakages parse() throws ParseException {
        WarehouseBreakages warehouseBreakages = new WarehouseBreakages();
        warehouseBreakages.setDate(getDateValueForCell(6, C));
        warehouseBreakages.setWarehouseBreakages(getFloatValueForCell(0, A));
        return warehouseBreakages;
    }
}
