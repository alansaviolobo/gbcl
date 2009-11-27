package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.WarehouseProductivity;
import com.technotrix.pepsi.readers.SheetReader;
import java.text.ParseException;

public class WarehouseProductivityReader extends BaseParser {
    private static final short D = 3;
    private static final short F = 5;
    private static final short G = 6;

    public WarehouseProductivityReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public WarehouseProductivity parse() throws ParseException
    {
        float numerator = getFloatValueForCell(13, G);
        float denominator = getFloatValueForCell( 9, F) + getFloatValueForCell(10, F)
                        + getFloatValueForCell(11, F) + getFloatValueForCell(12, F);
        WarehouseProductivity warehouseProductivity = new WarehouseProductivity();
        warehouseProductivity.setDate(getDateValueForCell(5, D));
        warehouseProductivity.setCasesPerEmployeeHours(numerator/denominator);
        return warehouseProductivity;
    }
}
