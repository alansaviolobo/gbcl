package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.ForecastAccuracy;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class ForecastAccuracyReader extends BaseParser {
    public ForecastAccuracyReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public ForecastAccuracy parse() throws ParseException {
        ForecastAccuracy forecastAccuracy = new ForecastAccuracy();
        forecastAccuracy.setDate(getDateValueForCell(2, C));
        forecastAccuracy.setForecastAccuracy(getFloatValueForCell(0, A));
        forecastAccuracy.setGoodForecast(getFloatValueForCell(1, A));
        return forecastAccuracy;
    }
}
