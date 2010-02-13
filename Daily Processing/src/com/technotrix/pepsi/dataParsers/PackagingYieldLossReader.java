package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.PackagingYieldLoss;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class PackagingYieldLossReader extends BaseParser {
    public PackagingYieldLossReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public PackagingYieldLoss parse() throws ParseException {
        PackagingYieldLoss packagingYieldLoss = new PackagingYieldLoss();
        packagingYieldLoss.setDate(getDateValueForCell(5, B));
        packagingYieldLoss.setPackagingYieldLoss(getFloatValueForCell(0, A));
        return packagingYieldLoss;
    }
}
