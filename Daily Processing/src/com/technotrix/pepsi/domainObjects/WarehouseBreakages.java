package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class WarehouseBreakages {
    private int id;
    private Date date;
    private float warehouseBreakages;

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

    public float getWarehouseBreakages() {
        return warehouseBreakages;
    }

    public void setWarehouseBreakages(float warehouseBreakages) {
        this.warehouseBreakages = warehouseBreakages;
    }
}
