package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class Water {
    private int id;
    private Date date;
    private float water;

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

    public float getWater() {
        return water;
    }

    public void setWater(float water) {
        this.water = water;
    }
}
