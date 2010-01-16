package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.FillerDowntime;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class FillerDowntimeReader extends BaseParser {

    public FillerDowntimeReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public FillerDowntime parse() throws ParseException {
        FillerDowntime fillerDowntime = new FillerDowntime();
        fillerDowntime.setDate(getDateValueForCell(2, C));
        fillerDowntime.setFillerDowntime(getFloatValueForCell(0, A));
        return fillerDowntime;
    }

}