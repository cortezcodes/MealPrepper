package com.example.android.mealprepper;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Data model to define grocery objects.
 */
@Entity(tableName = "grocery_table")
public class Grocery {
    public static final String ID_KEY = "id";
    public static final String ITEM_KEY = "item";
    public static final String AMOUNT_KEY = "amount";
    public static final String UNITS_KEY = "units";

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

    @Ignore
    public Grocery(int id, String item, int amount, String unitOfMeasure){
        this.id = id;
        this.item = item;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * Static class that is used to create Bundles out of Grocery Data
     * @return Bundle of data
     */
    public Bundle createBundle(){
        Bundle bundle = new Bundle();
        bundle.putInt(ID_KEY, getId());
        bundle.putString(ITEM_KEY, getItem());
        bundle.putInt(AMOUNT_KEY, getAmount());
        bundle.putString(UNITS_KEY, getUnitOfMeasure());
        return bundle;
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
