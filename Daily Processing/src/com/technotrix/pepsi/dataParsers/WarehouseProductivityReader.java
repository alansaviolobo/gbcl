package com.technotrix.pepsi.dataParsers;

import com.technotrix.pepsi.domainObjects.WarehouseProductivity;
import com.technotrix.pepsi.readers.SheetReader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Nov 21, 2009
 * Time: 3:49:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class WarehouseProductivityReader extends BaseParser {
    private static final short G = 6;
    private static final short D = 3;
    private static final short E = 4;

    public WarehouseProductivityReader(SheetReader sheetReader) {
        super(sheetReader);
    }

    public WarehouseProductivity parse() throws ParseException
    {
        WarehouseProductivity warehouseProductivity = new WarehouseProductivity();
        warehouseProductivity.setDate(getDateValueForCell(5, D));
        warehouseProductivity.setCasesLoaded(getIntegerValueForCell(13, G));
        warehouseProductivity.setSupervisorCount(getIntegerValueForCell(9, D));
        warehouseProductivity.setOperatorCount(getIntegerValueForCell(10, D));
        warehouseProductivity.setDriverCount(getIntegerValueForCell(11, D));
        warehouseProductivity.setLoaderCount(getIntegerValueForCell(12, D));
        warehouseProductivity.setSupervisorHours(getIntegerValueForCell(9, E));
        warehouseProductivity.setOperatorHours(getIntegerValueForCell(10, E));
        warehouseProductivity.setDriverHours(getIntegerValueForCell(11, E));
        warehouseProductivity.setLoaderHours(getIntegerValueForCell(12, E));
        warehouseProductivity.setCasesPerEmployeeHours(getFloatValueForCell(15, G));
        return warehouseProductivity;
    }

}
