package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class PlantProductivity {
    private Date date;
    private LineProductivity lineProductivity;
    private TotalPaidHours totalPaidHours;

    public PlantProductivity(Date date) {
        this.date = date;
        lineProductivity = new LineProductivity();
        lineProductivity.setDate(date);
        totalPaidHours = new TotalPaidHours();
        totalPaidHours.setDate(date);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getProductionProductivity()
    {
        return lineProductivity.getLineProductivity()/totalPaidHours.getTotalPaidHours();
    }
}