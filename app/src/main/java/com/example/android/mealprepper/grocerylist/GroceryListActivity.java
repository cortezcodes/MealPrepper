package com.example.android.mealprepper.grocerylist;

import android.os.Bundle;
import com.example.android.mealprepper.R;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


public class GroceryListActivity extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity);
        openGroceryList();
    }

    public void openGroceryList(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.grocery_activity_placeholder,new GroceryListFragment());
        ft.commit();
    }


}
