package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.WarehouseBreakages;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Nov 21, 2009
 * Time: 6:27:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class WarehouseBreakagesReader extends BaseParser{
    private static final short B = 1;
    private static final short G = 6;

    public WarehouseBreakagesReader(SheetReader sheetReader) {
        super(sheetReader);
    }
    
    public WarehouseBreakages parse() throws ParseException
    {
        WarehouseBreakages warehouseBreakages = new WarehouseBreakages();
        warehouseBreakages.setDate(getDateValueForCell(G, B));
        warehouseBreakages.setTotalRGB(getFloatValueForCell(30, G));
        return warehouseBreakages;
    }
}
