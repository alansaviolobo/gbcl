package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class InjuryFrequency {
    private int id;
    private Date date;
    private float injuryFrequency;

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

    public float getInjuryFrequency() {
        return injuryFrequency;
    }

    public void setInjuryFrequency(float injuryFrequency) {
        this.injuryFrequency = injuryFrequency;
    }
}
