package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.FillerDowntime;
import com.technotrix.pepsi.readers.SheetReader;
import java.text.ParseException;

public class FillerDowntimeReader extends BaseParser {
    private static final short A = 0;
    private static final short C = 2;
    private static final short D = 3;

    public FillerDowntimeReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public FillerDowntime parse() throws ParseException
    {
        float numerator = getFloatValueForCell(12, C) * 100;
        float denominator = getFloatValueForCell(12, D);
        FillerDowntime fillerDowntime = new FillerDowntime();
        fillerDowntime.setDate(getDateValueForCell(12, A));
        fillerDowntime.setFillerDowntime(numerator/denominator);
        return fillerDowntime;
    }

}