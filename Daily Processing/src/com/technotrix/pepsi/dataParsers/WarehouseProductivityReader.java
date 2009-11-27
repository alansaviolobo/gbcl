package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.WarehouseProductivity;
import com.technotrix.pepsi.readers.SheetReader;
import java.text.ParseException;

public class WarehouseProductivityReader extends BaseParser {
    private static final short A = 0;
    private static final short D = 3;

    public WarehouseProductivityReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public WarehouseProductivity parse() throws ParseException
    {
        WarehouseProductivity warehouseProductivity = new WarehouseProductivity();
        warehouseProductivity.setDate(getDateValueForCell(5, D));
        warehouseProductivity.setCasesPerEmployeeHour(getFloatValueForCell(0, A));
        return warehouseProductivity;
    }
}
