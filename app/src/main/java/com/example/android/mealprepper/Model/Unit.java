package com.example.android.mealprepper.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "unit_table")
public class Unit {
    @PrimaryKey(autoGenerate = true)
    private int unitId;

    @ColumnInfo(name = "units")
    private String unit;


    public Unit(String unit){
        this.unit = unit;
    }

    public int getUnitId() {
        return unitId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setUnitId(int id){this.unitId = id;}
}
