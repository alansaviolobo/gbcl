package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class MaterialAvailability {
    private int id;
    private Date date;
    private float materialAvailability;

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

    public float getMaterialAvailability() {
        return materialAvailability;
    }

    public void setMaterialAvailability(float materialAvailability) {
        this.materialAvailability = materialAvailability;
    }
}
