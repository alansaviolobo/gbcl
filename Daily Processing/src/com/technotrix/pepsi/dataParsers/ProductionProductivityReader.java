package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.ProductionProductivity;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class ProductionProductivityReader extends BaseParser {

    public ProductionProductivityReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public ProductionProductivity parse() throws ParseException {
        ProductionProductivity productionProductivity = new ProductionProductivity();
        productionProductivity.setDate(getDateValueForCell(2, C));
        productionProductivity.setGrossProduction(getFloatValueForCell(0, A));
        productionProductivity.setCasesPerEmployeeHour(getFloatValueForCell(1, A));
        return productionProductivity;
    }

}