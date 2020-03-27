package com.example.android.mealprepper;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Data model to define grocery objects.
 */
@Entity(tableName = "grocery_table")
public class Grocery {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "item")
    @NonNull
    private String item;

    @ColumnInfo(name = "amount")
    private int amount;

    @ColumnInfo(name = "units")
    private String unitOfMeasure;

    public Grocery(@NonNull String item, int amount, String unitOfMeasure){
        this.item = item;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getItem() {
        return item;
    }

    public void setItem(@NonNull String item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
