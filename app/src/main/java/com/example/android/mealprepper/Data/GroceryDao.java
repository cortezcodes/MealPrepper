package com.example.android.mealprepper.Data;

import com.example.android.mealprepper.Model.Grocery;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface GroceryDao {

    @Query("SELECT * FROM grocery_table")
    LiveData<List<Grocery>> getAllGroceries();

    @Query("DELETE FROM grocery_table")
    void deleteGroceryList();


    @Insert
    void insertGrocery(Grocery grocery);

    @Delete
    void deleteGrocery(Grocery grocery);


    @Update
    void updateGrocery(Grocery grocery);
}
