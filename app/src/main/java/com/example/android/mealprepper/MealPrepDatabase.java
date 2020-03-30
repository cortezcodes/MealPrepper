package com.example.android.mealprepper;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Database Implementation through RoomDatabase
 */

@Database(entities = {Grocery.class}, version = 1, exportSchema = false)
public abstract class MealPrepDatabase extends RoomDatabase {

    public abstract GroceryDao groceryDao();

    private static MealPrepDatabase INSTANCE;

    static MealPrepDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (MealPrepDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MealPrepDatabase.class, "meal_prep_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(initDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback initDatabaseCallback = new  RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            /*
            If you want to keep the data through app restarts,
            comment out the following line.
             */
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    public static class PopulateDbAsync extends AsyncTask<Void,Void,Void>{

        private final GroceryDao mDao;
        private Grocery[] groceries;

        PopulateDbAsync(MealPrepDatabase db){
            this.mDao = db.groceryDao();
            this.groceries = createGroceries();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteList();
            for(Grocery grocery: groceries){
                mDao.insertItem(grocery);
            }
            return null;
        }

        private Grocery[] createGroceries(){
            Grocery grocery1 = new Grocery("Fruit", 5, "packages");
            Grocery grocery2 = new Grocery("Bread", 4, "loaves");
            Grocery grocery3 = new Grocery("Meat", 2, "lb");
            Grocery grocery4 = new Grocery("Pasta", 3, "Boxes");
            Grocery grocery5 = new Grocery("Pizza", 1, " ");
            Grocery[] groceries = {grocery1, grocery2, grocery3, grocery4, grocery5};

            return groceries;
        }
    }
}
