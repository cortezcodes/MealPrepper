package com.example.android.mealprepper.Data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.mealprepper.Model.Grocery;
import com.example.android.mealprepper.TestData.GroceryTestData;
import com.example.android.mealprepper.Utilities.GroceryUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Grocery.class}, version = 1, exportSchema = false)
public abstract class MealPrepDatabase extends RoomDatabase {

    public static final String DB_NAME = "meal_prepper_database";
    private static MealPrepDatabase instance;
    public abstract GroceryDao groceryDao();



    /**
     * Create a Singleton instance of MealPrepDatabase using
     * Room.databaseBuilder.
     * @param context Context of the application
     * @return MealPrepDatabase Object
     */
    public static MealPrepDatabase getDatabase(Context context){
        if (instance == null) {
            synchronized (MealPrepDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            MealPrepDatabase.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(sTestGroceryCallback)
                            .build();
                }
            }
        }

        return instance;
    }


    // callback class used to reset the database when it is opend
    private static RoomDatabase.Callback sTestGroceryCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbTask(instance).execute();
                }
            };

    private static class PopulateDbTask extends AsyncTask<Void, Void, Void>{
        private final GroceryDao mDao;
       private Grocery[] mGroceries = GroceryTestData.getTestGroceries();

        public PopulateDbTask(MealPrepDatabase db){
            mDao = db.groceryDao();
        }

        /**
         * Starts the app with a clean database every time.
         * Not needed if you only populate the database
         * when it is first created
         * @param voids
         * @return
         */
        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteGroceryList();

            for (int i = 0; i < mGroceries.length; i++){
                mDao.insertGrocery(mGroceries[i]);
            }
            return null;
        }
    }
}
