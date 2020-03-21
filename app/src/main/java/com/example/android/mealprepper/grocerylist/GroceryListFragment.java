package com.example.android.mealprepper.grocerylist;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.mealprepper.Grocery;
import com.example.android.mealprepper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroceryListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private List<Grocery> groceryList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GroceryAdapter mAdapter;
    private ImageButton addGroceryImageButton;

    public GroceryListFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grocery_list, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.grocery_recycler_view);
        mAdapter = new GroceryAdapter(groceryList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(super.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(super.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        testGroceryData();

        addGroceryImageButton = view.findViewById(R.id.image_button_add);
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
