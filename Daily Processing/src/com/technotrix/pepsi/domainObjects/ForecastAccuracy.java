package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class ForecastAccuracy {
    private int id;
    private Date date;
    private float forecastAccuracy;
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

    public float getForecastAccuracy() {
        return forecastAccuracy;
    }

    public void setForecastAccuracy(float forecastAccuracy) {
        this.forecastAccuracy = forecastAccuracy;
    }

    public float getGoodForecast() {
        return goodForecast;
    }

    public void setGoodForecast(float goodForecast) {
        this.goodForecast = goodForecast;
    }
}
