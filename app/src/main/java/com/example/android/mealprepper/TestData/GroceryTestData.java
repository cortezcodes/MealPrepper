package com.example.android.mealprepper.TestData;

import com.example.android.mealprepper.Model.Grocery;

public class GroceryTestData {

    /**
     * Test cases for Grocery table in MealPrepDatabase
     * @return Grocery[]
     */
    public static Grocery[] getTestGroceries(){
        Grocery grocery1 = new Grocery("Fruit", 5, "pieces");
        Grocery grocery2 = new Grocery("Meat", 4, "Lbs");
        Grocery grocery3 = new Grocery("Veggies", 2, " ");
        Grocery grocery4 = new Grocery("Candy", 3, "bags");
        Grocery grocery5 = new Grocery("Bread", 1, "Loaf");

       return new Grocery[]{grocery1,grocery2,grocery3, grocery4,grocery5};
    }
}
