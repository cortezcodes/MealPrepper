package com.example.android.mealprepper.Model;

public class Grocery {
    private String name;
    private int quantity;
    private String unitOfMeasurement;
    private double pricePerUnit;

    //TODO Implement price grabbing from Major Grocery Stores
    public Grocery(String name, int quantity, String unitOfMeasurement, double pricePerUnit ){
        this.name = name;
        this.quantity = quantity;
        this.unitOfMeasurement = unitOfMeasurement;
        this.pricePerUnit = pricePerUnit;
    }

    public Grocery(String name, int quantity, String unitOfMeasurement){
        this.name = name;
        this.quantity = quantity;
        this.unitOfMeasurement = unitOfMeasurement;
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
