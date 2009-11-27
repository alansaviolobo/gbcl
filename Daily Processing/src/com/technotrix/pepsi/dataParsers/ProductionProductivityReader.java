package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.ProductionProductivity;
import com.technotrix.pepsi.readers.SheetReader;
import java.text.ParseException;

public class ProductionProductivityReader extends BaseParser {
    private static final short B = 1;
    private static final short C = 2;
    private static final short D = 3;

    public ProductionProductivityReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public ProductionProductivity parse() throws ParseException
    {
        float numerator = getFloatValueForCell(7, C);
        float denominator = getFloatValueForCell(7, D);
        ProductionProductivity productionProductivity = new ProductionProductivity();
        productionProductivity.setDate(getDateValueForCell(7, B));
        productionProductivity.setCasesPerEmployeeHour(numerator/denominator);
        return productionProductivity;
    }

}