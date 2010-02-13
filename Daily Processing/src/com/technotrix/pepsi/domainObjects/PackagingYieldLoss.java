package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class PackagingYieldLoss {
    private int id;
    private Date date;
    private float packagingYieldLoss;

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

    public float getPackagingYieldLoss() {
        return packagingYieldLoss;
    }

    public void setPackagingYieldLoss(float packagingYieldLoss) {
        this.packagingYieldLoss = packagingYieldLoss;
    }
}
