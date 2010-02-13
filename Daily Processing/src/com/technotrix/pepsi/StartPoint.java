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

        //1
        try {
            tempPath = downloadFile(new URI(path + "injury_frequency.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "injury_frequency");
            InjuryFrequencyReader injuryFrequencyReader = new InjuryFrequencyReader(excelSheetReader);
            InjuryFrequency injuryFrequency = injuryFrequencyReader.parse();
            session.save(injuryFrequency);
            appendStatus("Processing Injury Frequency... OK");
        } catch (Exception e) {
            appendStatus("Injury Frequency... FAILED");
        }

        //2
        try {
            tempPath = downloadFile(new URI(path + "lost_time.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "lost_time");
            LostTimeReader lostTimeReader = new LostTimeReader(excelSheetReader);
            LostTime lostTime = lostTimeReader.parse();
            session.save(lostTime);
            appendStatus("Processing Lost Time... OK");
        } catch (Exception e) {
            appendStatus("Lost Time... FAILED");
        }

        //3
        try {
            tempPath = downloadFile(new URI(path + "brix.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "brix");
            BrixReader BrixReader = new BrixReader(excelSheetReader);
            Brix brix = BrixReader.parse();
            session.save(brix);
            appendStatus("Processing Brix... OK");
        } catch (Exception e) {
            appendStatus("Brix... FAILED");
        }

        //4
        try {
            tempPath = downloadFile(new URI(path + "ta.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "ta");
            TAReader tAReader = new TAReader(excelSheetReader);
            TA tA = tAReader.parse();
            session.save(tA);
            appendStatus("Processing TA... OK");
        } catch (Exception e) {
            appendStatus("TA... FAILED");
        }

        //5
        try {
            tempPath = downloadFile(new URI(path + "co2.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "co2");
            CO2Reader cO2Reader = new CO2Reader(excelSheetReader);
            CO2 cO2 = cO2Reader.parse();
            session.save(cO2);
            appendStatus("Processing CO2... OK");
        } catch (Exception e) {
            appendStatus("CO2... FAILED");
        }

        //6
        try {
            tempPath = downloadFile(new URI(path + "yeast_log.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "yeast_log");
            YeastLogReader yeastLogReader = new YeastLogReader(excelSheetReader);
            YeastLog yeastLog = yeastLogReader.parse();
            session.save(yeastLog);
            appendStatus("Processing Yeast Log... OK");
        } catch (Exception e) {
            appendStatus("Yeast Log... FAILED");
        }

        //7
        try {
            tempPath = downloadFile(new URI(path + "cases_not_available_to_load.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "cases_not_available_to_load");
            CasesNotAvailableToLoadReader casesNotAvailableToLoadReader = new CasesNotAvailableToLoadReader(excelSheetReader);
            CasesNotAvailableToLoad casesNotAvailableToLoad = casesNotAvailableToLoadReader.parse();
            session.save(casesNotAvailableToLoad);
            appendStatus("Processing Cases Not Available To Load... OK");
        } catch (Exception e) {
            appendStatus("Cases Aot Available To Load... FAILED");
        }

        //8 & 9
        try {
            tempPath = downloadFile(new URI(path + "forecast_accuracy.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "forecast_accuracy");
            ForecastAccuracyReader forecastAccuracyReader = new ForecastAccuracyReader(excelSheetReader);
            ForecastAccuracy forecastAccuracy = forecastAccuracyReader.parse();
            session.save(forecastAccuracy);
            appendStatus("Processing Forecast Accuracy... OK");
        } catch (Exception e) {
            appendStatus("Forecast Accuracy... FAILED");
        }

        //10
        try {
            tempPath = downloadFile(new URI(path + "material_availability.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "material_availability");
            MaterialAvailabilityReader materialAvailabilityReader = new MaterialAvailabilityReader(excelSheetReader);
            MaterialAvailability materialAvailability = materialAvailabilityReader.parse();
            session.save(materialAvailability);
            appendStatus("Processing Material Availability... OK");
        } catch (Exception e) {
            appendStatus("Material Availability... FAILED");
        }

        //11
        try {
            tempPath = downloadFile(new URI(path + "schedule_compliance.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "schedule_compliance");
            ScheduleComplianceReader scheduleComplianceReader = new ScheduleComplianceReader(excelSheetReader);
            ScheduleCompliance scheduleCompliance = scheduleComplianceReader.parse();
            session.save(scheduleCompliance);
            appendStatus("Processing Schedule Compliance... OK");
        } catch (Exception e) {
            appendStatus("Schedule Compliance... FAILED");
        }

        //12
        try {
            tempPath = downloadFile(new URI(path + "total_plant_waste.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "total_plant_waste");
            TotalPlantWasteReader totalPlantWasteReader = new TotalPlantWasteReader(excelSheetReader);
            TotalPlantWaste totalPlantWaste = totalPlantWasteReader.parse();
            session.save(totalPlantWaste);
            appendStatus("Processing Total Plant Waste... OK");
        } catch (Exception e) {
            appendStatus("Total Plant Waste... FAILED");
        }

        //13
        try {
            tempPath = downloadFile(new URI(path + "production_breakage.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "production_breakage");
            ProductionBreakageReader productionBreakageReader = new ProductionBreakageReader(excelSheetReader);
            ProductionBreakage productionBreakage = productionBreakageReader.parse();
            session.save(productionBreakage);
            appendStatus("Processing Production Breakage... OK");
        } catch (Exception e) {
            appendStatus("Production Breakage... FAILED");
        }

        //14
        try {
            tempPath = downloadFile(new URI(path + "warehouse_breakage.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "warehouse_breakage");
            WarehouseBreakageReader warehouseBreakageReader = new WarehouseBreakageReader(excelSheetReader);
            WarehouseBreakage warehouseBreakage = warehouseBreakageReader.parse();
            session.save(warehouseBreakage);
            appendStatus("Processing Warehouse Breakage... OK");
        } catch (Exception e) {
            appendStatus("Warehouse Breakage... FAILED");
        }

        //15
        try {
            tempPath = downloadFile(new URI(path + "ingredient_yield_loss.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "ingredient_yield_loss");
            IngredientYieldLossReader ingredientYieldLossReader = new IngredientYieldLossReader(excelSheetReader);
            IngredientYieldLoss ingredientYieldLoss = ingredientYieldLossReader.parse();
            session.save(ingredientYieldLoss);
            appendStatus("Processing Ingredient Yield Loss... OK");
        } catch (Exception e) {
            appendStatus("Ingredient Yield Loss... FAILED");
        }

        //16
        try {
            tempPath = downloadFile(new URI(path + "packaging_yield_loss.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "packaging_yield_loss");
            PackagingYieldLossReader packagingYieldLossReader = new PackagingYieldLossReader(excelSheetReader);
            PackagingYieldLoss packagingYieldLoss = packagingYieldLossReader.parse();
            session.save(packagingYieldLoss);
            appendStatus("Processing Packaging Yield Loss... OK");
        } catch (Exception e) {
            appendStatus("Packaging Yield Loss... FAILED");
        }

        //17
        try {
            tempPath = downloadFile(new URI(path + "finished_goods_shrinkage.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "finished_goods_shrinkage");
            FinishedGoodsShrinkageReader finishedGoodsShrinkageReader = new FinishedGoodsShrinkageReader(excelSheetReader);
            FinishedGoodsShrinkage finishedGoodsShrinkage = finishedGoodsShrinkageReader.parse();
            session.save(finishedGoodsShrinkage);
            appendStatus("Processing Finished Goods Shrinkage... OK");
        } catch (Exception e) {
            appendStatus("Finished Goods Shrinkage... FAILED");
        }

        //19 A & B
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

        //20
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

        //21
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

        //22
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

        //23
        try {
            tempPath = downloadFile(new URI(path + "concentrate_yield.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "concentrate_yield");
            ConcentrateYieldReader concentrateYieldReader = new ConcentrateYieldReader(excelSheetReader);
            ConcentrateYield concentrateYield = concentrateYieldReader.parse();
            session.save(concentrateYield);
            appendStatus("Processing Concentrate Yield... OK");
        } catch (Exception e) {
            appendStatus("Concentrate Yield... FAILED");
        }

        //24
        try {
            tempPath = downloadFile(new URI(path + "crown_yield.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "crown_yield");
            CrownYieldReader crownYieldReader = new CrownYieldReader(excelSheetReader);
            CrownYield crownYield = crownYieldReader.parse();
            session.save(crownYield);
            appendStatus("Processing Crown Yield... OK");
        } catch (Exception e) {
            appendStatus("Crown Yield... FAILED");
        }

        //25
        try {
            tempPath = downloadFile(new URI(path + "sugar_yield.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "sugar_yield");
            SugarYieldReader sugarYieldReader = new SugarYieldReader(excelSheetReader);
            SugarYield sugarYield = sugarYieldReader.parse();
            session.save(sugarYield);
            appendStatus("Processing Sugar Yield... OK");
        } catch (Exception e) {
            appendStatus("Sugar Yield... FAILED");
        }

        //26
        try {
            tempPath = downloadFile(new URI(path + "co2_yield.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "co2_yield");
            CO2YieldReader cO2YieldReader = new CO2YieldReader(excelSheetReader);
            CO2Yield cO2Yield = cO2YieldReader.parse();
            session.save(cO2Yield);
            appendStatus("Processing CO2 Yield... OK");
        } catch (Exception e) {
            appendStatus("CO2 Yield... FAILED");
        }

        //27
        try {
            tempPath = downloadFile(new URI(path + "furnace_oil.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "furnace_oil");
            FurnaceOilReader furnaceOilReader = new FurnaceOilReader(excelSheetReader);
            FurnaceOil furnaceOil = furnaceOilReader.parse();
            session.save(furnaceOil);
            appendStatus("Processing Furnace Oil... OK");
        } catch (Exception e) {
            appendStatus("Furnace Oil... FAILED");
        }

        //28
        try {
            tempPath = downloadFile(new URI(path + "power_beverage.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "power_beverage");
            PowerBeverageReader powerBeverageReader = new PowerBeverageReader(excelSheetReader);
            PowerBeverage powerBeverage = powerBeverageReader.parse();
            session.save(powerBeverage);
            appendStatus("Processing Power Beverage... OK");
        } catch (Exception e) {
            appendStatus("Power Beverage... FAILED");
        }

        //29
        try {
            tempPath = downloadFile(new URI(path + "water.xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "water");
            WaterReader waterReader = new WaterReader(excelSheetReader);
            Water water = waterReader.parse();
            session.save(water);
            appendStatus("Processing Water... OK");
        } catch (Exception e) {
            appendStatus("Water... FAILED");
        }

/*      Pending Gross, net, dispatch
        //
        try {
            tempPath = downloadFile(new URI(path + "...xls"), username, password);
            excelSheetReader = new ExcelSheetReader(tempPath, "..");
            ..Reader ..Reader = new ..Reader(excelSheetReader);
            .. .. = ..Reader.parse();
            session.save(..);
            appendStatus("Processing ..... OK");
        } catch (Exception e) {
            appendStatus("..... FAILED");
        }

*/
        session.close();
    }

    public void generateReport(Calendar calendar) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            InjuryFrequency injuryFrequency;
            LostTime lostTime;
            Brix brix;
            TA ta;
            CO2 co2;
            YeastLog yeastLog;
            CasesNotAvailableToLoad casesNotAvailableToLoad;
            ForecastAccuracy forecastAccuracy;
            MaterialAvailability materialAvailability;
            ScheduleCompliance scheduleCompliance;
            TotalPlantWaste totalPlantWaste;
            ProductionBreakage productionBreakage;
            WarehouseBreakage warehouseBreakage;
            IngredientYieldLoss ingredientYieldLoss;
            PackagingYieldLoss packagingYieldLoss;
            FinishedGoodsShrinkage finishedGoodsShrinkage;
            PlantProductivity plantProductivity;
            LineProductivity lineProductivity;
            WarehouseProductivity warehouseProductivity;
            FillerDowntime fillerDowntime;
            ProductionProductivity productionProductivity;
            FurnaceOil furnaceOil;
            TotalPaidHours totalPaidHours;

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
                    injuryFrequency = (InjuryFrequency) session.createQuery("from InjuryFrequency where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    lostTime = (LostTime) session.createQuery("from LostTime where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    brix = (Brix) session.createQuery("from Brix where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    ta = (TA) session.createQuery("from TA where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    co2 = (CO2) session.createQuery("from CO2 where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    yeastLog = (YeastLog) session.createQuery("from YeastLog where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    casesNotAvailableToLoad = (CasesNotAvailableToLoad) session.createQuery("from CasesNotAvailableToLoad where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    forecastAccuracy = (ForecastAccuracy) session.createQuery("from ForecastAccuracy where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    materialAvailability = (MaterialAvailability) session.createQuery("from MaterialAvailability where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    scheduleCompliance = (ScheduleCompliance) session.createQuery("from ScheduleCompliance where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    totalPlantWaste = (TotalPlantWaste) session.createQuery("from TotalPlantWaste where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    productionBreakage = (ProductionBreakage) session.createQuery("from ProductionBreakage where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    warehouseBreakage = (WarehouseBreakage) session.createQuery("from WarehouseBreakage where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    ingredientYieldLoss = (IngredientYieldLoss) session.createQuery("from IngredientYieldLoss where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    packagingYieldLoss = (PackagingYieldLoss) session.createQuery("from PackagingYieldLoss where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    finishedGoodsShrinkage = (FinishedGoodsShrinkage) session.createQuery("from FinishedGoodsShrinkage where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    totalPaidHours = (TotalPaidHours) session.createQuery("from TotalPaidHours where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    productionProductivity = (ProductionProductivity) session.createQuery("from ProductionProductivity where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    plantProductivity = new PlantProductivity(productionProductivity, totalPaidHours);
                    lineProductivity = (LineProductivity) session.createQuery("from LineProductivity where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    warehouseProductivity = (WarehouseProductivity) session.createQuery("from WarehouseProductivity where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    fillerDowntime = (FillerDowntime) session.createQuery("from FillerDowntime where date = :now").setDate("now", calendar.getTime()).list().get(0);
                    furnaceOil = (FurnaceOil) session.createQuery("from FurnaceOil where date = :now").setDate("now", calendar.getTime()).list().get(0);

                    excelSheetWriter.setFloatCellValue(8, count, injuryFrequency.getInjuryFrequency());
                    excelSheetWriter.setFloatCellValue(9, count, lostTime.getLostTime());
                    excelSheetWriter.setFloatCellValue(13, count, brix.getBrix());
                    excelSheetWriter.setFloatCellValue(14, count, ta.getTa());
                    excelSheetWriter.setFloatCellValue(15, count, co2.getCo2());
                    excelSheetWriter.setFloatCellValue(16, count, yeastLog.getYeastLog());
                    excelSheetWriter.setFloatCellValue(20, count, casesNotAvailableToLoad.getCasesNotAvailableToLoad());
                    excelSheetWriter.setFloatCellValue(21, count, forecastAccuracy.getForcastAccuracy());
                    excelSheetWriter.setFloatCellValue(22, count, scheduleCompliance.getScheduleCompliance());
                    excelSheetWriter.setFloatCellValue(23, count, materialAvailability.getMaterialAvailability());
                    excelSheetWriter.setFloatCellValue(24, count, forecastAccuracy.getGoodForecast());
                    excelSheetWriter.setFloatCellValue(28, count, totalPlantWaste.getTotalPlantWaste());
                    excelSheetWriter.setFloatCellValue(29, count, productionBreakage.getProductionBreakage());
                    excelSheetWriter.setFloatCellValue(30, count, warehouseBreakage.getWarehouseBreakages());
                    excelSheetWriter.setFloatCellValue(31, count, ingredientYieldLoss.getIngredientYieldLoss());
                    excelSheetWriter.setFloatCellValue(32, count, packagingYieldLoss.getPackagingYieldLoss());
                    excelSheetWriter.setFloatCellValue(33, count, finishedGoodsShrinkage.getFinishedGoodsShrinkage());
                    excelSheetWriter.setFloatCellValue(36, count, plantProductivity.getProductionProductivity());
                    excelSheetWriter.setFloatCellValue(37, count, lineProductivity.getLineProductivity());
                    excelSheetWriter.setFloatCellValue(38, count, warehouseProductivity.getCasesPerEmployeeHour());
                    excelSheetWriter.setFloatCellValue(39, count, fillerDowntime.getFillerDowntime());
                    excelSheetWriter.setFloatCellValue(40, count, plantProductivity.getProductionProductivity());
                    excelSheetWriter.setFloatCellValue(44, count, furnaceOil.getFurnaceOil());

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