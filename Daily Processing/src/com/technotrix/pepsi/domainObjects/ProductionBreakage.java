package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class ProductionBreakage {
    private int id;
    private Date date;
    private float productionBreakage;

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

    public float getProductionBreakage() {
        return productionBreakage;
    }

    public void setProductionBreakage(float productionBreakage) {
        this.productionBreakage = productionBreakage;
    }
}
