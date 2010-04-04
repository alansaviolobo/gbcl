package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.LineProductivity;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class LineProductivityReader extends BaseParser {

    public LineProductivityReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public LineProductivity parse() throws ParseException {
        LineProductivity lineProductivity = new LineProductivity();
        lineProductivity.setDate(getDateValueForCell(2, C));
        lineProductivity.setLineProductivity(getFloatValueForCell(0, A));
        return lineProductivity;
    }

}