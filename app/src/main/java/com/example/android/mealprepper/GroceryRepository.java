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

    public void delete(Grocery grocery){new deleteAsyncTask(mGroceryDao).execute(grocery);}

    public void update(Grocery grocery){new updateAsyncTask(mGroceryDao).execute(grocery);}

    /**
     * ASyncTask used to insert a single Grocery object into the database
     */
    private static class insertAsyncTask extends AsyncTask<Grocery, Void, Void>{

        private GroceryDao mAsyncTaskDao;

        private insertAsyncTask(GroceryDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Grocery... params){
            mAsyncTaskDao.insertItem(params[0]);
            return null;
        }
    }

    /**
     * ASyncTask used to delete a single Grocery object from the databsse
     */
    private static class deleteAsyncTask extends AsyncTask<Grocery, Void, Void>{
        private GroceryDao mAsyncTaskDao;

        private deleteAsyncTask(GroceryDao dao){ mAsyncTaskDao = dao;}

        @Override
        protected Void doInBackground(Grocery... groceries) {
            mAsyncTaskDao.deleteItem(groceries[0]);
            return null;
        }
    }

    /**
     * AsyncTask used to update a single Grocery Object from the database
     */
    private static class updateAsyncTask extends AsyncTask<Grocery, Void, Void>{
        private GroceryDao mAsyncTaskDao;

        private updateAsyncTask(GroceryDao dao){mAsyncTaskDao = dao;}

        @Override
        protected Void doInBackground(Grocery... groceries) {
            mAsyncTaskDao.updateItem(groceries[0]);
            return null;
        }
    }
}
