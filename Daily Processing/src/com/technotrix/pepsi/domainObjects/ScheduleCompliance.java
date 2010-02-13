package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class ScheduleCompliance {
    private int id;
    private Date date;
    private float scheduleCompliance;

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

    public float getScheduleCompliance() {
        return scheduleCompliance;
    }

    public void setScheduleCompliance(float scheduleCompliance) {
        this.scheduleCompliance = scheduleCompliance;
    }
}
