package com.technotrix.pepsi;

import com.technotrix.pepsi.dataParsers.*;
import com.technotrix.pepsi.domainObjects.*;
import com.technotrix.pepsi.hibernate.persistence.HibernateUtil;
import com.technotrix.pepsi.readers.ExcelSheetReader;
import com.technotrix.pepsi.readers.ExcelSheetWriter;
import com.toedter.calendar.JCalendar;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StartPoint implements ActionListener {
    MainPanel mainPanel;

    public static void main(String[] args) {
        StartPoint startPoint = new StartPoint();
        startPoint.ProgressDisplay();
    }

    public String downloadFile(URI uri, String username, String password) throws IOException {
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

        try {
            tempPath = downloadFile(new URI(path + "warehouse_breakages.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "warehouse_breakages");
            WarehouseBreakagesReader warehouseBreakagesReader = new WarehouseBreakagesReader(excelSheetReader);
            WarehouseBreakages warehouseBreakages = warehouseBreakagesReader.parse();
            session.save(warehouseBreakages);
            appendStatus("Processing Warehouse Breakages... OK");
        } catch (Exception e) {
            appendStatus("Warehouse Breakages... FAILED");
        }

        try {
            tempPath = downloadFile(new URI(path + "finished_goods_shrinkage.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "finished_goods_shrinkage");
            FinishedGoodsShrinkageReader finishedGoodsShrinkageReader = new FinishedGoodsShrinkageReader(excelSheetReader);
            FinishedGoodsShrinkage finishedGoodsShrinkage = finishedGoodsShrinkageReader.parse();
            session.save(finishedGoodsShrinkage);
            appendStatus("Finished Goods Shrinkage... OK");
        } catch (Exception e) {
            appendStatus("Finished Goods Shrinkage... FAILED");
        }

        try {
            tempPath = downloadFile(new URI(path + "cases_not_available_to_load.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "cases_not_available_to_load");
            CasesNotAvailableToLoadReader casesNotAvailableToLoadReader = new CasesNotAvailableToLoadReader(excelSheetReader);
            CasesNotAvailableToLoad casesNotAvailableToLoad = casesNotAvailableToLoadReader.parse();
            session.save(casesNotAvailableToLoad);
            appendStatus("Cases Not Available To Load... OK");
        } catch (Exception e) {
            appendStatus("Cases Aot Available To Load... FAILED");
        }

        try {
            tempPath = downloadFile(new URI(path + "injury_frequency.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "injury_frequency");
            InjuryFrequencyReader injuryFrequencyReader = new InjuryFrequencyReader(excelSheetReader);
            InjuryFrequency injuryFrequency = injuryFrequencyReader.parse();
            session.save(injuryFrequency);
            appendStatus("Injury Frequency... OK");
        } catch (Exception e) {
            appendStatus("Injury Frequency... FAILED");
        }

        try {
            tempPath = downloadFile(new URI(path + "lost_time.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "lost_time");
            LostTimeReader lostTimeReader = new LostTimeReader(excelSheetReader);
            LostTime lostTime = lostTimeReader.parse();
            session.save(lostTime);
            appendStatus("Lost Time... OK");
        } catch (Exception e) {
            appendStatus("Lost Time... FAILED");
        }

        try {
            tempPath = downloadFile(new URI(path + "forecast_accuracy.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "forecast_accuracy");
            ForecastAccuracyReader forecastAccuracyReader = new ForecastAccuracyReader(excelSheetReader);
            ForecastAccuracy forecastAccuracy = forecastAccuracyReader.parse();
            session.save(forecastAccuracy);
            appendStatus("Forecast Accuracy... OK");
        } catch (Exception e) {
            appendStatus("Forecast Accuracy... FAILED");
        }

        try {
            tempPath = downloadFile(new URI(path + "ingredient_yield_loss.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "ingredient_yield_loss");
            IngredientYieldLossReader ingredientYieldLossReader = new IngredientYieldLossReader(excelSheetReader);
            IngredientYieldLoss ingredientYieldLoss = ingredientYieldLossReader.parse();
            session.save(ingredientYieldLoss);
            appendStatus("Ingredient Yield Loss... OK");
        } catch (Exception e) {
            appendStatus("Ingredient Yield Loss... FAILED");
        }

        try {
            tempPath = downloadFile(new URI(path + "yeast_log.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "yeast_log");
            YeastLogReader yeastLogReader = new YeastLogReader(excelSheetReader);
            YeastLog yeastLog = yeastLogReader.parse();
            session.save(yeastLog);
            appendStatus("Yeast Log... OK");
        } catch (Exception e) {
            appendStatus("Yeast Log... FAILED");
        }

        try {
            tempPath = downloadFile(new URI(path + "furnace_oil.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "furnace_oil");
            FurnaceOilReader furnaceOilReader = new FurnaceOilReader(excelSheetReader);
            FurnaceOil furnaceOil = furnaceOilReader.parse();
            session.save(furnaceOil);
            appendStatus("Furnace Oil... OK");
        } catch (Exception e) {
            appendStatus("Furnace Oil... FAILED");
        }

        try {
            tempPath = downloadFile(new URI(path + "water.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "water");
            WaterReader waterReader = new WaterReader(excelSheetReader);
            Water water = waterReader.parse();
            session.save(water);
            appendStatus("Water... OK");
        } catch (Exception e) {
            appendStatus("Water... FAILED");
        }

        try {
            tempPath = downloadFile(new URI(path + "power.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "power");
            PowerReader powerReader = new PowerReader(excelSheetReader);
            Power power = powerReader.parse();
            session.save(power);
            appendStatus("Power... OK");
        } catch (Exception e) {
            appendStatus("Power... FAILED");
        }

        session.close();
    }

    public void generateReport(Calendar calendar) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            WarehouseProductivity warehouseProductivity;
            WarehouseBreakages warehouseBreakages;
            FinishedGoodsShrinkage finishedGoodsShrinkage;
            CasesNotAvailableToLoad casesNotAvailableToLoad;
            InjuryFrequency injuryFrequency;
            ProductionProductivity productionProductivity;
            LineProductivity lineProductivity;
            TotalPaidHours totalPaidHours;
            FurnaceOil furnaceOil;
            LostTime lostTime;
            FillerDowntime fillerDowntime;
            ForecastAccuracy forecastAccuracy;
            IngredientYieldLoss ingredientYieldLoss;
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
                    warehouseBreakages = (WarehouseBreakages) session.createQuery("from WarehouseBreakages where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    finishedGoodsShrinkage = (FinishedGoodsShrinkage) session.createQuery("from FinishedGoodsShrinkage where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    casesNotAvailableToLoad = (CasesNotAvailableToLoad) session.createQuery("from CasesNotAvailableToLoad where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    injuryFrequency = (InjuryFrequency) session.createQuery("from InjuryFrequency where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    lostTime = (LostTime) session.createQuery("from LostTime where date = :now").setDate("now", calendar.getTime()).list().get(0);

                    //this is to be calculated weekly.
                    forecastAccuracy = (ForecastAccuracy) session.createQuery("from ForecastAccuracy where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    ingredientYieldLoss = (IngredientYieldLoss) session.createQuery("from IngredientYieldLoss where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    furnaceOil = (FurnaceOil) session.createQuery("from FurnaceOil where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    //===
                    excelSheetWriter.setFloatCellValue(36, count, plantProductivity.getProductionProductivity());
                    excelSheetWriter.setFloatCellValue(37, count, lineProductivity.getLineProductivity());
                    excelSheetWriter.setFloatCellValue(38, count, warehouseProductivity.getCasesPerEmployeeHour());
                    excelSheetWriter.setFloatCellValue(39, count, fillerDowntime.getFillerDowntime());
                    excelSheetWriter.setFloatCellValue(40, count, productionProductivity.getCasesPerEmployeeHour());
                    excelSheetWriter.setFloatCellValue(41, count, warehouseBreakages.getWarehouseBreakages());
                    excelSheetWriter.setFloatCellValue(42, count, finishedGoodsShrinkage.getFinishedGoodsShrinkage());
                    excelSheetWriter.setFloatCellValue(43, count, casesNotAvailableToLoad.getCasesNotAvailableToLoad());
                    excelSheetWriter.setFloatCellValue(44, count, injuryFrequency.getInjuryFrequency());
                    excelSheetWriter.setFloatCellValue(45, count, lostTime.getLostTime());
                    excelSheetWriter.setFloatCellValue(46, count, forecastAccuracy.getForcastAccuracy());

                    //weekly
                    excelSheetWriter.setFloatCellValue(47, count, forecastAccuracy.getGoodForecast());
                    excelSheetWriter.setFloatCellValue(48, count, ingredientYieldLoss.getIngredientYieldLoss());
                    excelSheetWriter.setFloatCellValue(49, count, furnaceOil.getFurnaceOil());
                    //==

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

        area = new JTextArea(9, 30);
        area.setEditable(Boolean.FALSE);

        JScrollPane areaScrollPane = new JScrollPane(area);
        areaScrollPane
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
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