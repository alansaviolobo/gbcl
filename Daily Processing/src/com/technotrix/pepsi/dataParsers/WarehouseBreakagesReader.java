package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.WarehouseBreakages;
import com.technotrix.pepsi.readers.SheetReader;
import java.text.ParseException;

public class WarehouseBreakagesReader extends BaseParser{
    private static final short A = 0;
    private static final short B = 1;

    public WarehouseBreakagesReader(SheetReader sheetReader) {
        super(sheetReader);
    }
    
    public WarehouseBreakages parse() throws ParseException
    {
        WarehouseBreakages warehouseBreakages = new WarehouseBreakages();
        warehouseBreakages.setDate(getDateValueForCell(10, B));
        warehouseBreakages.setTotalRGB(getFloatValueForCell(0, A));
        return warehouseBreakages;
    }
}
