package com.example.android.mealprepper.Data;

import android.app.Application;
import android.os.AsyncTask;

import com.example.android.mealprepper.Model.Grocery;

import java.util.List;

import androidx.lifecycle.LiveData;

public class GroceryRepository {
    private GroceryDao mGroceryDao;
    private LiveData<List<Grocery>> mAllGroceries;

    /**
     * Constructor to get a handle to the database and initializes
     * member variables
     * @param application Context to the application
     */
    public GroceryRepository(Application application){
        MealPrepDatabase db = MealPrepDatabase.getDatabase(application);
        mGroceryDao = db.groceryDao();
        mAllGroceries = mGroceryDao.getAllGroceries();
    }

    public LiveData<List<Grocery>> getAllGroceries(){
        return mAllGroceries;
    }

    public void insert(Grocery grocery){
        new insertTask(mGroceryDao).execute(grocery);
    }

    private static class insertTask extends AsyncTask<Grocery, Void, Void>{
        private GroceryDao mTaskDao;

        insertTask(GroceryDao dao){
            mTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Grocery... params){
            mTaskDao.insertGrocery(params[0]);
            return null;
        }
    }
}
