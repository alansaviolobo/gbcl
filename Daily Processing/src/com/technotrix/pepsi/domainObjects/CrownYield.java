package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class CrownYield {
    private int id;
    private Date date;
    private float crownYield;

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

    public float getCrownYield() {
        return crownYield;
    }

    public void setCrownYield(float crownYield) {
        this.crownYield = crownYield;
    }
}
