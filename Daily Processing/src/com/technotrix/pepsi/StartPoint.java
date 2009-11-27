package com.technotrix.pepsi;

import java.io.FileNotFoundException;
import java.util.Date;

import com.technotrix.pepsi.dataParsers.*;
import com.technotrix.pepsi.domainObjects.*;
import com.technotrix.pepsi.hibernate.persistence.HibernateUtil;
import com.technotrix.pepsi.readers.ExcelSheetReader;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class StartPoint {

    public static void main(String[] args) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            ExcelSheetReader excelSheetReader;
            Date date = new Date();
            //String path = "z:\\" + "\\" + year + "\\" + month + "\\" + day\\;
            String path = "..\\excel templates\\";

            excelSheetReader = new ExcelSheetReader(path + "warehouse_productivity.xls", "warehouse_productivity");
            WarehouseProductivityReader warehouseProductivityReader = new WarehouseProductivityReader(excelSheetReader);
            WarehouseProductivity warehouseProductivity = warehouseProductivityReader.parse();
            session.save(warehouseProductivity);
/*
            excelSheetReader = new ExcelSheetReader(path + "production_productivity.xls", "production_productivity");
            ProductionProductivityReader productionProductivityReader = new ProductionProductivityReader(excelSheetReader);
            ProductionProductivity productionProductivity = productionProductivityReader.parse();
            session.save(productionProductivity);

            excelSheetReader = new ExcelSheetReader(path + "line_productivity.xls", "line_productivity");
            LineProductivityReader lineProductivityReader = new LineProductivityReader(excelSheetReader);
            LineProductivity lineProductivity = lineProductivityReader.parse();
            session.save(lineProductivity);

            excelSheetReader = new ExcelSheetReader(path + "total_paid_hours.xls", "total_paid_hrs");
            TotalPaidHoursReader totalPaidHoursReader = new TotalPaidHoursReader(excelSheetReader);
            TotalPaidHours totalPaidHours = totalPaidHoursReader.parse();
            session.save(totalPaidHours);

            excelSheetReader = new ExcelSheetReader(path + "filler_downtime.xls", "filler_downtime");
            FillerDowntimeReader fillerDowntimeReader = new FillerDowntimeReader(excelSheetReader);
            FillerDowntime fillerDowntime = fillerDowntimeReader.parse();
            session.save(fillerDowntime);
 */
            tx.commit();
            session.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


