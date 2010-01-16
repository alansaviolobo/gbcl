package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class CasesNotAvailableToLoad {
    private int id;
    private Date date;
    private float casesNotAvailableToLoad;

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

    public float getCasesNotAvailableToLoad() {
        return casesNotAvailableToLoad;
    }

    public void setCasesNotAvailableToLoad(float casesNotAvailableToLoad) {
        this.casesNotAvailableToLoad = casesNotAvailableToLoad;
    }
}
