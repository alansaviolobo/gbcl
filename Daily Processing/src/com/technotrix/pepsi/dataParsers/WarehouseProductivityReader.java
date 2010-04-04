package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.WarehouseProductivity;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class WarehouseProductivityReader extends BaseParser {

    public WarehouseProductivityReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public WarehouseProductivity parse() throws ParseException {
        WarehouseProductivity warehouseProductivity = new WarehouseProductivity();
        warehouseProductivity.setDate(getDateValueForCell(2, C));
        warehouseProductivity.setCasesPerEmployeeHour(getFloatValueForCell(0, A));
        return warehouseProductivity;
    }
}
