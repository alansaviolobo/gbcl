package com.technotrix.pepsi.domainObjects;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Nov 21, 2009
 * Time: 6:27:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class WarehouseBreakages {
     private int id;
    private Date date;
    private float totalRGB;

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

    public float getTotalRGB() {
        return totalRGB;
    }

    public void setTotalRGB(float totalRGB) {
        this.totalRGB = totalRGB;
    }
}
