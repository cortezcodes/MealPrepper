package com.example.android.mealprepper.Activities;

import android.os.Bundle;
import com.example.android.mealprepper.R;
import com.example.android.mealprepper.Fragments.GroceryListFragment;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;



public class GroceryFragmentActivity extends FragmentActivity{


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity);



        //If returning from a previous state then do not create a new fragment
        if(savedInstanceState != null){
            return;
        }

        //Create Fragment to be place in activity layout
        GroceryListFragment groceryListFragment = new GroceryListFragment();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        groceryListFragment.setArguments(getIntent().getExtras());

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout_container, groceryListFragment).commit();
    }

}
