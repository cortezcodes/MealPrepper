package com.example.android.mealprepper.grocerylist;

import android.app.Activity;
import android.os.Bundle;
import com.example.android.mealprepper.R;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;


public class GroceryFragmentActivity extends FragmentActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if(findViewById(R.id.frame_layout_container) != null){
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


}
