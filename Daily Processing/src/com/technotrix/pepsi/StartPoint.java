package com.technotrix.pepsi;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.technotrix.pepsi.dataParsers.*;
import com.technotrix.pepsi.domainObjects.*;
import com.technotrix.pepsi.hibernate.persistence.HibernateUtil;
import com.technotrix.pepsi.readers.ExcelSheetReader;
import com.technotrix.pepsi.readers.ExcelSheetWriter;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class StartPoint {

    public static void main(String[] args) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            ExcelSheetReader excelSheetReader;

            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("\\yy\\MMM\\dd\\");
            //String path = "z:" + sdf.format(cal.getTime());
            String path = "..\\excel templates\\";

            excelSheetReader = new ExcelSheetReader(path + "warehouse_productivity.xls", "warehouse_productivity");
            WarehouseProductivityReader warehouseProductivityReader = new WarehouseProductivityReader(excelSheetReader);
            WarehouseProductivity warehouseProductivity = warehouseProductivityReader.parse();
            session.save(warehouseProductivity);

            excelSheetReader = new ExcelSheetReader(path + "production_productivity.xls", "production_productivity");
            ProductionProductivityReader productionProductivityReader = new ProductionProductivityReader(excelSheetReader);
            ProductionProductivity productionProductivity = productionProductivityReader.parse();
            session.save(productionProductivity);

            excelSheetReader = new ExcelSheetReader(path + "line_productivity.xls", "line_productivity");
            LineProductivityReader lineProductivityReader = new LineProductivityReader(excelSheetReader);
            LineProductivity lineProductivity = lineProductivityReader.parse();
            session.save(lineProductivity);

            excelSheetReader = new ExcelSheetReader(path + "total_paid_hrs.xls", "total_paid_hrs");
            TotalPaidHoursReader totalPaidHoursReader = new TotalPaidHoursReader(excelSheetReader);
            TotalPaidHours totalPaidHours = totalPaidHoursReader.parse();
            session.save(totalPaidHours);

            excelSheetReader = new ExcelSheetReader(path + "filler_downtime.xls", "filler_downtime");
            FillerDowntimeReader fillerDowntimeReader = new FillerDowntimeReader(excelSheetReader);
            FillerDowntime fillerDowntime = fillerDowntimeReader.parse();
            session.save(fillerDowntime);

//            KeyPerformanceMeasure keyPerformanceMeasure = new KeyPerformanceMeasure();
//            KeyPerformanceMeasureWriter keyPerformanceMeasureWriter = new KeyPerformanceMeasureWriter(keyPerformanceMeasure);
//            keyPerformanceMeasureWriter.save();
            PlantProductivity plantProductivity = new PlantProductivity(productionProductivity,  totalPaidHours);

            int offset = 4;
            if(sdf.format(cal.getTime()).equals("Mon")) offset+=1;
            else if (sdf.format(cal.getTime()).equals("Tue")) offset+=2;
            else if (sdf.format(cal.getTime()).equals("Wed")) offset+=3;
            else if (sdf.format(cal.getTime()).equals("Thu")) offset+=4;
            else if (sdf.format(cal.getTime()).equals("Fri")) offset+=5;
            else if (sdf.format(cal.getTime()).equals("Sat")) offset+=6;
            else if(sdf.format(cal.getTime()).equals("Sun")) offset+=7;

            ExcelSheetWriter excelSheetWriter = new ExcelSheetWriter(path + "goabottlings_kpm.xls", "Sheet1");
            excelSheetWriter.setDateCellValue(3, 2, plantProductivity.getDate());
            excelSheetWriter.setFloatCellValue(36, offset, plantProductivity.getProductionProductivity());
            excelSheetWriter.setFloatCellValue(37, offset, lineProductivity.getLineProductivity());
            excelSheetWriter.setFloatCellValue(38, offset, warehouseProductivity.getCasesPerEmployeeHour());
            excelSheetWriter.setFloatCellValue(39, offset, fillerDowntime.getFillerDowntime());
            excelSheetWriter.setFloatCellValue(40, offset, productionProductivity.getCasesPerEmployeeHour());
            excelSheetWriter.save("mnw1.xls");

            tx.commit();
            session.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}