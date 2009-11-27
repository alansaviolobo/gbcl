package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.LineProductivity;
import com.technotrix.pepsi.readers.SheetReader;
import java.text.ParseException;

public class LineProductivityReader extends BaseParser {
    private static final short A = 0;
    private static final short C = 2;

    public LineProductivityReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public LineProductivity parse() throws ParseException
    {
        LineProductivity lineProductivity = new LineProductivity();
        lineProductivity.setDate(getDateValueForCell(4, C));
        lineProductivity.setLineProductivity(getFloatValueForCell(0, A));
        return lineProductivity;
    }

}