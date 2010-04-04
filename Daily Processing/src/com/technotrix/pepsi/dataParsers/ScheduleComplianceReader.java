package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.ScheduleCompliance;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class ScheduleComplianceReader extends BaseParser {
    public ScheduleComplianceReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public ScheduleCompliance parse() throws ParseException {
        ScheduleCompliance scheduleCompliance = new ScheduleCompliance();
        scheduleCompliance.setDate(getDateValueForCell(2, C));
        scheduleCompliance.setScheduleCompliance(getFloatValueForCell(0, A));
        return scheduleCompliance;
    }
}
