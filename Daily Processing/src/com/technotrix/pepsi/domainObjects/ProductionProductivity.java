package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class ProductionProductivity {
    private int id;
    private Date date;
    private float casesPerEmployeeHour;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCasesPerEmployeeHour(float casesPerEmployeeHour) {
        this.casesPerEmployeeHour = casesPerEmployeeHour;
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