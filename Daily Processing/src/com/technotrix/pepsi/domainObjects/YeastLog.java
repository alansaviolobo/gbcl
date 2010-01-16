package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class YeastLog {
    private int id;
    private Date date;
    private float yeastLog;

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

    public float getYeastLog() {
        return yeastLog;
    }

    public void setYeastLog(float yeastLog) {
        this.yeastLog = yeastLog;
    }
}
