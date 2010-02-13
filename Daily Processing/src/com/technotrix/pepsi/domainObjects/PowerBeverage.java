package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class PowerBeverage {
    private int id;
    private Date date;
    private float powerBeverage;

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

    public float getPowerBeverage() {
        return powerBeverage;
    }

    public void setPowerBeverage(float powerBeverage) {
        this.powerBeverage = powerBeverage;
    }
}
