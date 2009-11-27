package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.ProductionProductivity;
import com.technotrix.pepsi.readers.SheetReader;
import java.text.ParseException;

public class ProductionProductivityReader extends BaseParser {
    private static final short A = 0;
    private static final short C = 2;

    public ProductionProductivityReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public ProductionProductivity parse() throws ParseException
    {
        ProductionProductivity productionProductivity = new ProductionProductivity();
        productionProductivity.setDate(getDateValueForCell(3, C));
        productionProductivity.setCasesPerEmployeeHour(getFloatValueForCell(0, A));
        return productionProductivity;
    }

}