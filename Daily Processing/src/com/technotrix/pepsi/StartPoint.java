package com.technotrix.pepsi;

import java.io.*;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.technotrix.pepsi.dataParsers.*;
import com.technotrix.pepsi.domainObjects.*;
import com.technotrix.pepsi.hibernate.persistence.HibernateUtil;
import com.technotrix.pepsi.readers.ExcelSheetReader;
import com.technotrix.pepsi.readers.ExcelSheetWriter;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StartPoint {
    public static void main(String[] args) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            ExcelSheetReader excelSheetReader;
            SimpleDateFormat sdf;
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            sdf = new SimpleDateFormat("/yy/MMM/dd/");
            String path = "http://192.168.1.4:8080/alfresco/webdav/User%20homes/mnwfiles" + sdf.format(cal.getTime());
            String username = "vandana", password= "vandana", tempPath;

            tempPath = downloadFile(new URI(path + "warehouse_productivity.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "warehouse_productivity");
            WarehouseProductivityReader warehouseProductivityReader = new WarehouseProductivityReader(excelSheetReader);
            WarehouseProductivity warehouseProductivity = warehouseProductivityReader.parse();
            session.save(warehouseProductivity);

            tempPath = downloadFile(new URI(path + "production_productivity.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "production_productivity");
            ProductionProductivityReader productionProductivityReader = new ProductionProductivityReader(excelSheetReader);
            ProductionProductivity productionProductivity = productionProductivityReader.parse();
            session.save(productionProductivity);

            tempPath = downloadFile(new URI(path + "line_productivity.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "line_productivity");
            LineProductivityReader lineProductivityReader = new LineProductivityReader(excelSheetReader);
            LineProductivity lineProductivity = lineProductivityReader.parse();
            session.save(lineProductivity);

            tempPath = downloadFile(new URI(path + "total_paid_hrs.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "total_paid_hrs");
            TotalPaidHoursReader totalPaidHoursReader = new TotalPaidHoursReader(excelSheetReader);
            TotalPaidHours totalPaidHours = totalPaidHoursReader.parse();
            session.save(totalPaidHours);

            tempPath = downloadFile(new URI(path + "filler_downtime.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "filler_downtime");
            FillerDowntimeReader fillerDowntimeReader = new FillerDowntimeReader(excelSheetReader);
            FillerDowntime fillerDowntime = fillerDowntimeReader.parse();
            session.save(fillerDowntime);

            PlantProductivity plantProductivity;
            int offset = 3;
            sdf = new SimpleDateFormat("E");
            if(sdf.format(cal.getTime()).equals("Mon")) offset+=1;
            else if (sdf.format(cal.getTime()).equals("Tue")) offset+=2;
            else if (sdf.format(cal.getTime()).equals("Wed")) offset+=3;
            else if (sdf.format(cal.getTime()).equals("Thu")) offset+=4;
            else if (sdf.format(cal.getTime()).equals("Fri")) offset+=5;
            else if (sdf.format(cal.getTime()).equals("Sat")) offset+=6;
            else if(sdf.format(cal.getTime()).equals("Sun")) offset+=7;
            sdf = new SimpleDateFormat("dd-MMM-yy");

            ExcelSheetWriter excelSheetWriter = new ExcelSheetWriter("goabottlings_kpm.xls", "KPM Sheet", "m & w report for " + sdf.format(cal.getTime())+ ".xls");
            excelSheetWriter.setStringCellValue(3, 2, sdf.format(cal.getTime()));
            for (int count = offset; count > 3; count--, cal.add(Calendar.DATE, -1))
            {
                try {
                lineProductivity = (LineProductivity) session.createQuery("from LineProductivity where date = :now").setDate("now", cal.getTime()).list().get(0);
                warehouseProductivity = (WarehouseProductivity) session.createQuery("from WarehouseProductivity where date = :now").setDate("now", cal.getTime()).list().get(0);
                fillerDowntime = (FillerDowntime) session.createQuery("from FillerDowntime where date = :now").setDate("now", cal.getTime()).list().get(0);
                productionProductivity = (ProductionProductivity) session.createQuery("from ProductionProductivity where date = :now").setDate("now", cal.getTime()).list().get(0);
                totalPaidHours = (TotalPaidHours) session.createQuery("from TotalPaidHours where date = :now").setDate("now", cal.getTime()).list().get(0);
                plantProductivity = new PlantProductivity(productionProductivity, totalPaidHours);

                excelSheetWriter.setFloatCellValue(36, count, plantProductivity.getProductionProductivity());
                excelSheetWriter.setFloatCellValue(37, count, lineProductivity.getLineProductivity());
                excelSheetWriter.setFloatCellValue(38, count, warehouseProductivity.getCasesPerEmployeeHour());
                excelSheetWriter.setFloatCellValue(39, count, fillerDowntime.getFillerDowntime());
                excelSheetWriter.setFloatCellValue(40, count, productionProductivity.getCasesPerEmployeeHour());
                } catch (Exception e) {}
            }
            cal.set(Calendar.DATE, 0);
            excelSheetWriter.save();

            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String downloadFile(URI uri, String username, String password) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            httpClient.getCredentialsProvider().setCredentials(
                    new AuthScope(uri.getHost(), uri.getPort()),
                    new UsernamePasswordCredentials(username, password));
            HttpGet httpget = new HttpGet(uri);
            HttpEntity entity = httpClient.execute(httpget).getEntity();
            if (entity != null) {
                File tempFile = File.createTempFile("test", ".xls");
                FileOutputStream fileOutputStream = new FileOutputStream(tempFile.getAbsoluteFile());
                entity.writeTo(fileOutputStream);
                entity.consumeContent();
                return tempFile.getAbsolutePath();
            }
            httpClient.getConnectionManager().shutdown();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
         return "";
    }
}