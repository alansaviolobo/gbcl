package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.WarehouseBreakage;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class WarehouseBreakageReader extends BaseParser {

    public WarehouseBreakageReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public WarehouseBreakage parse() throws ParseException {
        WarehouseBreakage warehouseBreakage = new WarehouseBreakage();
        warehouseBreakage.setDate(getDateValueForCell(6, C));
        warehouseBreakage.setWarehouseBreakages(getFloatValueForCell(0, A));
        return warehouseBreakage;
    }
}
