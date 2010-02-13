package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class ConcentrateYield {
    private int id;
    private Date date;
    private float concentrateYield;

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

    public float getConcentrateYield() {
        return concentrateYield;
    }

    public void setConcentrateYield(float concentrateYield) {
        this.concentrateYield = concentrateYield;
    }
}
