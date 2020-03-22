package com.example.android.mealprepper.grocerylist;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.android.mealprepper.Grocery;
import com.example.android.mealprepper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroceryListFragment extends Fragment {
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

    /**
     * Where all the subclass views care initiated.
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //Connect Recycler View and Grocery Adapter to populate the Fragment
        recyclerView = view.findViewById(R.id.grocery_recycler_view);
        mAdapter = new GroceryAdapter(groceryList);

        //Performance
        recyclerView.setHasFixedSize(true);

        //use a linearlayout manager for vertical scrolling
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(super.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        //set line divders between each list item
        recyclerView.addItemDecoration(new DividerItemDecoration(super.getContext(), LinearLayoutManager.VERTICAL));

        //attach adapter to create viewholders
        recyclerView.setAdapter(mAdapter);


        testGroceryData();

        addGroceryImageButton = view.findViewById(R.id.image_button_add);
        addGroceryImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create fragment and give it an argument specifying the article it should show
                GroceryDetailedFragment newGroceryFragment = new GroceryDetailedFragment();

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_container, newGroceryFragment);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();
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
