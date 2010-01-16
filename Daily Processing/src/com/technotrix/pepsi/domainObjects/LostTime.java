package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class LostTime {
    private int id;
    private Date date;
    private float lostTime;

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

    public float getLostTime() {
        return lostTime;
    }

    public void setLostTime(float lostTime) {
        this.lostTime = lostTime;
    }
}
