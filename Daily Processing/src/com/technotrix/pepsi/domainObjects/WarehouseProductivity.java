package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class WarehouseProductivity {
    private int id;
    private Date date;
    private float casesPerEmployeeHour;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCasesPerEmployeeHour(float casesPerEmployeeHours) {
        this.casesPerEmployeeHour = casesPerEmployeeHours;
    }

    public float getCasesPerEmployeeHour() {
        return casesPerEmployeeHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}