package com.technotrix.pepsi;

import java.io.FileNotFoundException;

import com.technotrix.pepsi.dataParsers.WarehouseBreakagesReader;
import com.technotrix.pepsi.domainObjects.WarehouseBreakages;
import com.technotrix.pepsi.domainObjects.WarehouseProductivity;
import com.technotrix.pepsi.readers.ExcelSheetReader;
import com.technotrix.pepsi.dataParsers.WarehouseProductivityReader;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.technotrix.pepsi.hibernate.persistence.HibernateUtil;

/**
 * @author Sandarenu
 */
public class StartPoint {

    public static void main(String[] args) {
        try {
            Session session =HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            ExcelSheetReader excelSheetReader = new ExcelSheetReader("..\\excel templates\\warehouse_productivity1.xls", "Warehouse_productivity");
            WarehouseProductivityReader warehouseProductivityReader = new WarehouseProductivityReader(excelSheetReader);
            WarehouseProductivity warehouseProductivity = warehouseProductivityReader.parse();
            session.save(warehouseProductivity);

            excelSheetReader = new ExcelSheetReader("..\\excel templates\\warehouse_breakages.xls", "Sheet1");
            WarehouseBreakagesReader warehouseBreakagesReader = new WarehouseBreakagesReader(excelSheetReader);
            WarehouseBreakages warehouseBreakages = warehouseBreakagesReader.parse();
            session.save(warehouseBreakages);

            tx.commit();
            session.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}


