package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class KeyPerformanceMeasure {
    private int id;
    private Date date;
    private float fillerDowntime;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setFillerDowntime(float fillerDowntime) {
        this.fillerDowntime = fillerDowntime;
    }

    public float getFillerDowntime() {
        return fillerDowntime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}