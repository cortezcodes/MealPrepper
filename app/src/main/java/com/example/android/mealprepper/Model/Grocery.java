package com.example.android.mealprepper.Model;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "grocery_table")
public class Grocery {
    @PrimaryKey(autoGenerate = true)
    private int groceryId;

    @ColumnInfo(name = "item")
    private String item;

    @ColumnInfo(name = "quantity")
    private int quantity;

    @ColumnInfo(name = "unitOfMeasurement")
    private String unitOfMeasurement;


    public Grocery(String item, int quantity, @Nullable String unitOfMeasurement){
        this.item = item;
        this.quantity = quantity;
        if(unitOfMeasurement == null){
            this.unitOfMeasurement = " ";
        }else{
            this.unitOfMeasurement = unitOfMeasurement;
        }

    }


    //All Getters and Setters below here
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
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

    public int getGroceryId() {
        return groceryId;
    }

    public void setGroceryId(int groceryId) {
        this.groceryId = groceryId;
    }
}
