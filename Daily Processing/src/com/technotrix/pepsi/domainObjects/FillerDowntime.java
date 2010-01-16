package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class FillerDowntime {
    private int id;
    private Date date;
    private float fillerDowntime;

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

    public float getFillerDowntime() {
        return fillerDowntime;
    }

    public void setFillerDowntime(float fillerDowntime) {
        this.fillerDowntime = fillerDowntime;
    }
}