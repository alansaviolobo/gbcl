package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.ProductionBreakage;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class ProductionBreakageReader extends BaseParser {
    public ProductionBreakageReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public ProductionBreakage parse() throws ParseException {
        ProductionBreakage productionBreakage = new ProductionBreakage();
        productionBreakage.setDate(getDateValueForCell(5, B));
        productionBreakage.setProductionBreakage(getFloatValueForCell(0, A));
        return productionBreakage;
    }
}
