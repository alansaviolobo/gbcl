package com.technotrix.pepsi;

import java.io.*;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.technotrix.pepsi.dataParsers.*;
import com.technotrix.pepsi.domainObjects.*;
import com.technotrix.pepsi.hibernate.persistence.HibernateUtil;
import com.technotrix.pepsi.readers.*;
import com.toedter.calendar.JCalendar;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StartPoint implements ActionListener {
    MainPanel mainPanel;

    public static void main(String[] args) {
        StartPoint startPoint = new StartPoint();
        startPoint.ProgressDisplay();
    }

    public String downloadFile(URI uri, String username, String password) {
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

    public void collectData(Calendar calendar) {
        String path = "http://192.168.1.110:8080/alfresco/webdav/User%20homes/mnwfiles" +
                new SimpleDateFormat("/yy/MMM/dd/").format(calendar.getTime());
        String username = "vandana", password = "vandana", tempPath;
        ExcelSheetReader excelSheetReader;
        Session session = HibernateUtil.getSessionFactory().openSession();
        appendStatus("Collecting data for " + new SimpleDateFormat("dd-MMM-yy").format(calendar.getTime()));
        try {
            tempPath = downloadFile(new URI(path + "warehouse_productivity.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "warehouse_productivity");
            WarehouseProductivityReader warehouseProductivityReader = new WarehouseProductivityReader(excelSheetReader);
            WarehouseProductivity warehouseProductivity = warehouseProductivityReader.parse();
            session.save(warehouseProductivity);
            appendStatus("Processing Warehouse Productivity... OK");
        } catch (Exception e) {
            appendStatus("Warehouse Productivity... FAILED");
        }

        try {
            tempPath = downloadFile(new URI(path + "production_productivity.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "production_productivity");
            ProductionProductivityReader productionProductivityReader = new ProductionProductivityReader(excelSheetReader);
            ProductionProductivity productionProductivity = productionProductivityReader.parse();
            session.save(productionProductivity);
            appendStatus("Processing Production Productivity... OK");
        } catch (Exception e) {
            appendStatus("Production Productivity... FAILED");
        }

        try {
            tempPath = downloadFile(new URI(path + "line_productivity.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "line_productivity");
            LineProductivityReader lineProductivityReader = new LineProductivityReader(excelSheetReader);
            LineProductivity lineProductivity = lineProductivityReader.parse();
            session.save(lineProductivity);
            appendStatus("Processing Line Productivity... OK");
        } catch (Exception e) {
            appendStatus("Line Productivity... FAILED");
        }

        try {
            tempPath = downloadFile(new URI(path + "total_paid_hrs.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "total_paid_hrs");
            TotalPaidHoursReader totalPaidHoursReader = new TotalPaidHoursReader(excelSheetReader);
            TotalPaidHours totalPaidHours = totalPaidHoursReader.parse();
            session.save(totalPaidHours);
            appendStatus("Processing Total Paid Hours... OK");
        } catch (Exception e) {
            appendStatus("Total Paid Hours... FAILED");
        }

        try {
            tempPath = downloadFile(new URI(path + "filler_downtime.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "filler_downtime");
            FillerDowntimeReader fillerDowntimeReader = new FillerDowntimeReader(excelSheetReader);
            FillerDowntime fillerDowntime = fillerDowntimeReader.parse();
            session.save(fillerDowntime);
            appendStatus("Processing Filler Downtime... OK");
        } catch (Exception e) {
            appendStatus("Filler Downtime... FAILED");
        }
        session.close();
    }

    public void generateReport(Calendar calendar) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            WarehouseProductivity warehouseProductivity;
            ProductionProductivity productionProductivity;
            LineProductivity lineProductivity;
            TotalPaidHours totalPaidHours;
            FillerDowntime fillerDowntime;
            PlantProductivity plantProductivity;
            int offset = 3;
            SimpleDateFormat sdf = new SimpleDateFormat("E");
            if (sdf.format(calendar.getTime()).equals("Mon")) offset += 1;
            else if (sdf.format(calendar.getTime()).equals("Tue")) offset += 2;
            else if (sdf.format(calendar.getTime()).equals("Wed")) offset += 3;
            else if (sdf.format(calendar.getTime()).equals("Thu")) offset += 4;
            else if (sdf.format(calendar.getTime()).equals("Fri")) offset += 5;
            else if (sdf.format(calendar.getTime()).equals("Sat")) offset += 6;
            else if (sdf.format(calendar.getTime()).equals("Sun")) offset += 7;
            sdf = new SimpleDateFormat("dd-MMM-yy");

            appendStatus("Creating kpm file for " + sdf.format(calendar.getTime()));
            ExcelSheetWriter excelSheetWriter = new ExcelSheetWriter("goabottlings_kpm.xls", "KPM Sheet", "m & w report for " + sdf.format(calendar.getTime()) + ".xls");
            excelSheetWriter.setStringCellValue(3, 2, sdf.format(calendar.getTime()));
            for (int count = offset; count > 3; count--, calendar.add(Calendar.DATE, -1)) {
                try {
                    lineProductivity = (LineProductivity) session.createQuery("from LineProductivity where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    warehouseProductivity = (WarehouseProductivity) session.createQuery("from WarehouseProductivity where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    fillerDowntime = (FillerDowntime) session.createQuery("from FillerDowntime where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    productionProductivity = (ProductionProductivity) session.createQuery("from ProductionProductivity where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    totalPaidHours = (TotalPaidHours) session.createQuery("from TotalPaidHours where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    plantProductivity = new PlantProductivity(productionProductivity, totalPaidHours);

                    excelSheetWriter.setFloatCellValue(36, count, plantProductivity.getProductionProductivity());
                    excelSheetWriter.setFloatCellValue(37, count, lineProductivity.getLineProductivity());
                    excelSheetWriter.setFloatCellValue(38, count, warehouseProductivity.getCasesPerEmployeeHour());
                    excelSheetWriter.setFloatCellValue(39, count, fillerDowntime.getFillerDowntime());
                    excelSheetWriter.setFloatCellValue(40, count, productionProductivity.getCasesPerEmployeeHour());
                    appendStatus("Retrieving data for " + sdf.format(calendar.getTime()) + "...OK");
                } catch (Exception e) {
                    appendStatus("Retrieving data for " + sdf.format(calendar.getTime()) + "...FAILED");
                }
            }
            excelSheetWriter.save();
            tx.commit();
            session.close();
            appendStatus("KPM file successfully created.");
        } catch (Exception e) {
            appendStatus(e.getMessage());
        }
    }

    private void ProgressDisplay() {
        JTabbedPane jtp = new JTabbedPane();
        mainPanel = new MainPanel(this);
        AboutPanel aboutPanel = new AboutPanel();

        jtp.addTab("KPI", mainPanel);
        jtp.addTab("About", aboutPanel);

        JFrame.setDefaultLookAndFeelDecorated(false);
        JFrame frame = new JFrame("Goa Bottlings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(350, 400));
        frame.setResizable(false);
        frame.getContentPane().add(jtp);
        frame.pack();
        frame.setVisible(true);
    }

    public void appendStatus(String status) {
        mainPanel.appendStatusMessage(status);
    }

    public void actionPerformed(ActionEvent e) {
        mainPanel.clearStatusMessage();
        if ("Collect Data".equals(e.getActionCommand())) {
            collectData(mainPanel.getCalendar());
        } else if ("Generate Report".equals(e.getActionCommand())) {
            generateReport(mainPanel.getCalendar());
        }
    }
}

class MainPanel extends JPanel {
    private JTextArea area = null;
    private JCalendar calendar = null;

    public MainPanel(StartPoint sp) {
        calendar = new JCalendar(Calendar.getInstance());

        JButton datacollectionBtn = new JButton("Collect Data");
        datacollectionBtn.setActionCommand("Collect Data");
        datacollectionBtn.addActionListener(sp);

        JButton sheetgenerationBtn = new JButton("Generate Report");
        sheetgenerationBtn.setActionCommand("Generate Report");
        sheetgenerationBtn.addActionListener(sp);

        area = new JTextArea(10, 30);
        area.setEditable(Boolean.FALSE);

        JScrollPane areaScrollPane = new JScrollPane(area);
        areaScrollPane
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        add(calendar, gridBagConstraints);
        add(datacollectionBtn, gridBagConstraints);
        add(sheetgenerationBtn, gridBagConstraints);
        add(areaScrollPane, gridBagConstraints);
    }

    public void appendStatusMessage(String status) {
        area.append(status + "\n");
    }

    public void clearStatusMessage() {
        area.setText("");
    }

    public Calendar getCalendar() {
        return calendar.getCalendar();
    }
}

class AboutPanel extends JPanel {
    public AboutPanel() {
        add(new JLabel("This Application has been created by:"));
        add(new JLabel(new ImageIcon("logo.png"), JLabel.LEFT));
        add(new JLabel("visit http://www.technotrix.co.in"));
        add(new JLabel("contact : support@technotrix.co.in"));
    }
}