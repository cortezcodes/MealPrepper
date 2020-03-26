package com.example.android.mealprepper.ViewModels;


import android.app.Application;

import com.example.android.mealprepper.Data.GroceryRepository;
import com.example.android.mealprepper.Model.Grocery;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


/**
 * ViewModel Class used to control and maintain data for the UI.
 * This also handles the repository CRUD operations. Extends AndroidViewModel so it is Activity aware
 */
public class GroceryViewModel extends AndroidViewModel {
    private GroceryRepository mRepository;
    private LiveData<List<Grocery>> mAllGroceries;

    public GroceryViewModel(Application application){
        super(application);
        mRepository = new GroceryRepository(application);
        mAllGroceries = mRepository.getAllGroceries();
    }


    public LiveData<List<Grocery>> getAllGroceries(){ return mAllGroceries;}

    public void insertGrocery(Grocery grocery){
        mRepository.insert(grocery);
    }
}
