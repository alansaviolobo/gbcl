package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.FinishedGoodsShrinkage;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class FinishedGoodsShrinkageReader extends BaseParser {

    public FinishedGoodsShrinkageReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public FinishedGoodsShrinkage parse() throws ParseException {
        FinishedGoodsShrinkage finishedGoodsShrinkage = new FinishedGoodsShrinkage();
        finishedGoodsShrinkage.setDate(getDateValueForCell(5, C));
        finishedGoodsShrinkage.setFinishedGoodsShrinkage(getIntegerValueForCell(0, A));
        return finishedGoodsShrinkage;
    }
}
