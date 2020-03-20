package com.example.android.mealprepper;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GroceryListActivity extends AppCompatActivity {
    private List<Grocery> groceryList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GroceryAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        recyclerView = findViewById(R.id.grocery_recycler_view);
        mAdapter = new GroceryAdapter(groceryList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        testGroceryData();
    }

    private void testGroceryData(){
            Grocery grocery;
        for(int i = 0; i <= 50; i++) {
            grocery = new Grocery("Orange", 3, " ", 1.99);
            groceryList.add(grocery);

            grocery = new Grocery("Tomato", 2, " ", 1.00);
            groceryList.add(grocery);

            grocery = new Grocery("Soup", 5, "Cans", 2.99);
            groceryList.add(grocery);

            grocery = new Grocery("Meat", 1, "Lbs", 8.99);
            groceryList.add(grocery);
        }

        mAdapter.notifyDataSetChanged();
    }
}
