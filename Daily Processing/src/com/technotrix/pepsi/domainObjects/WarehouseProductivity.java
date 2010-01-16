package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class WarehouseProductivity {
    private int id;
    private Date date;
    private float casesPerEmployeeHour;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getCasesPerEmployeeHour() {
        return casesPerEmployeeHour;
    }

    public void setCasesPerEmployeeHour(float casesPerEmployeeHour) {
        this.casesPerEmployeeHour = casesPerEmployeeHour;
    }
}