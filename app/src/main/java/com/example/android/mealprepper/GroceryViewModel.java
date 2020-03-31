package com.example.android.mealprepper;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class GroceryViewModel extends AndroidViewModel {

    private GroceryRepository mRepository;
    private LiveData<List<Grocery>> mGroceryList;

    public GroceryViewModel (Application application){
        super(application);
        mRepository = new GroceryRepository(application);
        mGroceryList = mRepository.getGroceryList();
    }

    public LiveData<List<Grocery>> getGroceryList(){
        return mGroceryList;
    }

    public void insert(Grocery grocery){
        mRepository.insert(grocery);
    }

    public void delete(Grocery grocery){
        mRepository.delete(grocery);
    }

    public void update(Grocery grocery){ mRepository.update(grocery);}
}
