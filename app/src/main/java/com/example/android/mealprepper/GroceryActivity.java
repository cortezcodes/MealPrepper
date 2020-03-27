package com.example.android.mealprepper;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

public class GroceryActivity extends FragmentActivity {

    /**
     * onCreate for this FragmentActivity host the GroceryList and GroceryDetail fragment views. It
     * will set its ContentView to the grocery_list_fragment initially.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity);
        if(findViewById(R.id.frame_layout_container) != null){
            if(savedInstanceState != null){
                return;
            }

            GroceryListFragment listFragment = new GroceryListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_container, listFragment).commit();
        }
    }
}
