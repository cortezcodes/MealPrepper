package com.example.android.mealprepper;

public class Grocery {
    private String name;
    private int quantity;
    private String unitOfMeasurement;
    private double pricePerUnit;

    public Grocery(String name, int quantity, String unitOfMeasurement, int pricePerUnit ){
        this.name = name;
        this.quantity = quantity;
        this.unitOfMeasurement = unitOfMeasurement;
        this.pricePerUnit = pricePerUnit;
    }


    //All Getters and Setters below here
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
