package com.example.android.mealprepper;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Repository for making database queries.
 */
public class GroceryRepository {

    private GroceryDao mGroceryDao;
    private LiveData<List<Grocery>> mGroceryList;

    GroceryRepository(Application application){
        MealPrepDatabase db = MealPrepDatabase.getDatabase(application);
        mGroceryDao = db.groceryDao();
        mGroceryList = mGroceryDao.getList();
    }

    public LiveData<List<Grocery>> getGroceryList(){
        return mGroceryList;
    }

    public void insert (Grocery grocery){
        new insertAsyncTask(mGroceryDao).execute(grocery);
    }

    private static class insertAsyncTask extends AsyncTask<Grocery, Void, Void>{

        private GroceryDao mAsyncTaskDao;

        insertAsyncTask(GroceryDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Grocery... params){
            mAsyncTaskDao.insertItem(params[0]);
            return null;
        }
    }
}
