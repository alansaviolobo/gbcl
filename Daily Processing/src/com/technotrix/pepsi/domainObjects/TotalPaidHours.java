package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class TotalPaidHours {
    private int id;
    private Date date;
    private float totalPaidHours;

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

    public float getTotalPaidHours() {
        return totalPaidHours;
    }

    public void setTotalPaidHours(float totalPaidHours) {
        this.totalPaidHours = totalPaidHours;
    }
}