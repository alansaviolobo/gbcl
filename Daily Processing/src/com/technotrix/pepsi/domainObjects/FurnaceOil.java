package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class FurnaceOil {
    private int id;
    private Date date;
    private float furnaceOil;

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

    public float getFurnaceOil() {
        return furnaceOil;
    }

    public void setFurnaceOil(float furnaceOil) {
        this.furnaceOil = furnaceOil;
    }
}
