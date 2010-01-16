package com.technotrix.pepsi.domainObjects;

import java.util.Date;

public class IngredientYieldLoss {
    private int id;
    private Date date;
    private float ingredientYieldLoss;

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

    public float getIngredientYieldLoss() {
        return ingredientYieldLoss;
    }

    public void setIngredientYieldLoss(float ingredientYieldLoss) {
        this.ingredientYieldLoss = ingredientYieldLoss;
    }
}
