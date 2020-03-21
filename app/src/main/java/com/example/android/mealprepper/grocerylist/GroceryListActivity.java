package com.example.android.mealprepper.grocerylist;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.android.mealprepper.Grocery;
import com.example.android.mealprepper.R;
import com.example.android.mealprepper.grocerylist.GroceryAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GroceryListActivity extends AppCompatActivity {
    private List<Grocery> groceryList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GroceryAdapter mAdapter;
    private ImageButton addGroceryImageButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        recyclerView = findViewById(R.id.grocery_recycler_view);
        mAdapter = new GroceryAdapter(groceryList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        testGroceryData();

        addGroceryImageButton = findViewById(R.id.image_button_add);
        addGroceryImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void testGroceryData(){
            Grocery grocery;
        for(int i = 0; i <= 50; i++) {
            grocery = new Grocery("Orange", 3, " ");
            groceryList.add(grocery);

            grocery = new Grocery("Tomato", 2, " ");
            groceryList.add(grocery);

            grocery = new Grocery("Soup", 5, "Cans");
            groceryList.add(grocery);

            grocery = new Grocery("Meat", 1, "Lbs");
            groceryList.add(grocery);
        }

        mAdapter.notifyDataSetChanged();
    }
}
