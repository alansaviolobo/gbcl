package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class ForecastAccuracy {
    private int id;
    private Date date;
    private float forcastAccuracy;
    private float goodForecast;

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

    public float getForcastAccuracy() {
        return forcastAccuracy;
    }

    public void setForcastAccuracy(float forcastAccuracy) {
        this.forcastAccuracy = forcastAccuracy;
    }

    public float getGoodForecast() {
        return goodForecast;
    }

    public void setGoodForecast(float goodForecast) {
        this.goodForecast = goodForecast;
    }
}
