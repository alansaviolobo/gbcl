package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class LineProductivity {
    private int id;
    private Date date;
    private float lineProductivity;

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

    public float getLineProductivity() {
        return lineProductivity;
    }

    public void setLineProductivity(float lineProductivity) {
        this.lineProductivity = lineProductivity;
    }
}