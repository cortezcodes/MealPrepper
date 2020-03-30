package com.example.android.mealprepper;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Interface used by the Room framework Where SQL queries are created
 */
@Dao
public interface GroceryDao {

    @Insert
    void insertItem(Grocery grocery);

    @Query("DELETE FROM grocery_table")
    void deleteList();

    @Delete
    void deleteItem(Grocery grocery);

    @Query("SELECT * FROM grocery_table")
    LiveData<List<Grocery>> getList();


}
