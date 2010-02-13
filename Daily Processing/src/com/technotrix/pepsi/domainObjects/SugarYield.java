package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class SugarYield {
    private int id;
    private Date date;
    private float sugarYield;

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

    public float getSugarYield() {
        return sugarYield;
    }

    public void setSugarYield(float sugarYield) {
        this.sugarYield = sugarYield;
    }
}
