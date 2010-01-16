package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class PlantProductivity {
    private Date date;
    private ProductionProductivity productionProductivity;
    private TotalPaidHours totalPaidHours;

    public PlantProductivity(ProductionProductivity productionProductivity, TotalPaidHours totalPaidHours) {
        this.date = productionProductivity.getDate();
        this.productionProductivity = productionProductivity;
        this.totalPaidHours = totalPaidHours;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getProductionProductivity() {
        return productionProductivity.getGrossProduction() / totalPaidHours.getTotalPaidHours();
    }
}