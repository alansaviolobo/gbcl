package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class WarehouseProductivity {
    private int id;
    private Date date;
    private float casesPerEmployeeHours;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCasesPerEmployeeHours(float casesPerEmployeeHours) {
        this.casesPerEmployeeHours = casesPerEmployeeHours;
    }

    public float getCasesPerEmployeeHours() {
        return casesPerEmployeeHours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}