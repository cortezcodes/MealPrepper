package com.example.android.mealprepper.Utilities;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class GroceryUtil {
    //Constants for passing data in  bundles between fragments
    public static final String ITEM = "item";
    public static final String QUANTITY = "QUANTITY";
    public static final String UNITS = "UNIT";

    //options in the spinner in the
    private static ArrayList<String> spinnerSelections = new ArrayList<String>(){
        {
            add(" ");
            add("Cans");
            add("Oz");
            add("Lb");
            add("Gal");
            add("Carton");
            add("Bottle");
            add("Six Pack");
            add("Bag");
            add("other");
        }
    };

    public static void addUnitOfMeasure(String units){
        spinnerSelections.add(units);
    }

    public static List<String> getSpinnerData(){
        return spinnerSelections;
    }
}
