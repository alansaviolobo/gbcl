package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.TotalPaidHours;
import com.technotrix.pepsi.readers.SheetReader;
import java.text.ParseException;

public class TotalPaidHoursReader extends BaseParser {
    private static final short B = 0;
    private static final short G = 6;

    public TotalPaidHoursReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public TotalPaidHours parse() throws ParseException
    {
        TotalPaidHours totalPaidHours = new TotalPaidHours();
        totalPaidHours.setDate(getDateValueForCell(3, B));
        totalPaidHours.setTotalPaidHours(getFloatValueForCell(22, G));
        return totalPaidHours;
    }

}