package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class FinishedGoodsShrinkage {
    private int id;
    private Date date;
    private int finishedGoodsShrinkage;

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

    public int getFinishedGoodsShrinkage() {
        return finishedGoodsShrinkage;
    }

    public void setFinishedGoodsShrinkage(int finishedGoodsShrinkage) {
        this.finishedGoodsShrinkage = finishedGoodsShrinkage;
    }
}
