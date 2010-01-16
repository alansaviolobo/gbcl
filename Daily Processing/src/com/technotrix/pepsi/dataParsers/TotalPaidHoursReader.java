package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.TotalPaidHours;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class TotalPaidHoursReader extends BaseParser {

    public TotalPaidHoursReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public TotalPaidHours parse() throws ParseException {
        TotalPaidHours totalPaidHours = new TotalPaidHours();
        totalPaidHours.setDate(getDateValueForCell(4, C));
        totalPaidHours.setTotalPaidHours(getFloatValueForCell(0, A));
        return totalPaidHours;
    }

}