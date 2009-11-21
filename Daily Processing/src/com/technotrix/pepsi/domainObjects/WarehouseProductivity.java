package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class WarehouseProductivity {
    private int id;
    private Date date;
    private int supervisorHours;
    private int supervisorCount;
    private int operatorHours;
    private int operatorCount;
    private int driverHours;
    private int driverCount;
    private int loaderHours;
    private int loaderCount;
    private int casesLoaded;
    private float casesPerEmployeeHours;

    public Date getDate() {
        return date;
    }

    public int getSupervisorHours() {
        return supervisorHours;
    }

    public int getSupervisorCount() {
        return supervisorCount;
    }

    public int getOperatorHours() {
        return operatorHours;
    }

    public int getOperatorCount() {
        return operatorCount;
    }

    public int getDriverHours() {
        return driverHours;
    }

    public int getDriverCount() {
        return driverCount;
    }

    public int getLoaderHours() {
        return loaderHours;
    }

    public int getLoaderCount() {
        return loaderCount;
    }

    public int getCasesLoaded() {
        return casesLoaded;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSupervisorHours(int supervisorHours) {
        this.supervisorHours = supervisorHours;
    }

    public void setSupervisorCount(int supervisorCount) {
        this.supervisorCount = supervisorCount;
    }

    public void setOperatorHours(int operatorHours) {
        this.operatorHours = operatorHours;
    }

    public void setOperatorCount(int operatorCount) {
        this.operatorCount = operatorCount;
    }

    public void setDriverHours(int driverHours) {
        this.driverHours = driverHours;
    }

    public void setDriverCount(int driverCount) {
        this.driverCount = driverCount;
    }

    public void setLoaderHours(int loaderHours) {
        this.loaderHours = loaderHours;
    }

    public void setLoaderCount(int loaderCount) {
        this.loaderCount = loaderCount;
    }

    public void setCasesLoaded(int casesLoaded) {
        this.casesLoaded = casesLoaded;
    }

    public void setCasesPerEmployeeHours(float casesPerEmployeeHours) {
        this.casesPerEmployeeHours = casesPerEmployeeHours;
    }

    public float getCasesPerEmployeeHours() {
        return casesPerEmployeeHours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}