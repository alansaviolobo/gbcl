package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class TA {
    private int id;
    private Date date;
    private float ta;

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

    public float getTa() {
        return ta;
    }

    public void setTa(float ta) {
        this.ta = ta;
    }
}
