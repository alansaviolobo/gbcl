package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class WarehouseBreakage {
    private int id;
    private Date date;
    private float warehouseBreakage;

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

    public float getWarehouseBreakage() {
        return warehouseBreakage;
    }

    public void setWarehouseBreakage(float warehouseBreakage) {
        this.warehouseBreakage = warehouseBreakage;
    }
}
