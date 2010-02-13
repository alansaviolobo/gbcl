package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class CO2Yield {
    private int id;
    private Date date;
    private float co2Yield;

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

    public float getCo2Yield() {
        return co2Yield;
    }

    public void setCo2Yield(float co2Yield) {
        this.co2Yield = co2Yield;
    }
}
