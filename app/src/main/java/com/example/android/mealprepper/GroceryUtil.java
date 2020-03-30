package com.example.android.mealprepper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroceryUtil {
    private static final List<String> singleUnits = new ArrayList<>(Arrays.asList("", "lb", "oz", "gal", "qt",
            "pt", "c.","fl. oz.", "Tbsp.", "tsp.", "mL.", "l.",
            "g.", "kg.", "doz.", "box", "roll", "bottle", "can",
            "bunch", "package", "pack", "loaf", "other"));

    private static final List<String>  pluralUnits = new ArrayList<>(Arrays.asList("", "lb", "oz", "gal", "qt",
            "pt", "c.","fl. oz.", "Tbsp.", "tsp.", "mL.", "l.",
            "g.", "kg.", "doz.", "boxes", "rolls", "bottles", "cans",
            "bunches", "packages", "packs", "loaves", "other"));

    public static String pluralize(int position){
        return pluralUnits.get(position);
    }

    public static List<String> getSingleUnits () {
        return singleUnits;
    }

    public static List<String> getPluralUnits() {
        return pluralUnits;
    }

}
