package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.LineProductivity;
import com.technotrix.pepsi.readers.SheetReader;
import java.text.ParseException;

public class LineProductivityReader extends BaseParser {
    private static final short A = 0;
    private static final short E = 4;
    private static final short F = 5;

    public LineProductivityReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public LineProductivity parse() throws ParseException
    {
        float numerator = getFloatValueForCell(9, F) * 100;
        float denominator = getFloatValueForCell(9, E);
        LineProductivity lineProductivity = new LineProductivity();
        lineProductivity.setDate(getDateValueForCell(12, A));
        lineProductivity.setLineProductivity(numerator/denominator);
        return lineProductivity;
    }

}