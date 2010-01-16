package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.IngredientYieldLoss;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

public class IngredientYieldLossReader extends BaseParser {
    public IngredientYieldLossReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public IngredientYieldLoss parse() throws ParseException {
        IngredientYieldLoss ingredientYieldLoss = new IngredientYieldLoss();
        ingredientYieldLoss.setDate(getDateValueForCell(1, D));
        ingredientYieldLoss.setIngredientYieldLoss(getFloatValueForCell(0, A));
        return ingredientYieldLoss;
    }
}
