package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class Brix {
    private int id;
    private Date date;
    private float brix;

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

    public float getBrix() {
        return brix;
    }

    public void setBrix(float brix) {
        this.brix = brix;
    }
}
