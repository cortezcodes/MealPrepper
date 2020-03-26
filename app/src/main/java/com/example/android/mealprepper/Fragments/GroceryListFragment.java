package com.example.android.mealprepper.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.android.mealprepper.Model.Grocery;
import com.example.android.mealprepper.R;
import com.example.android.mealprepper.Adapters.GroceryListAdapter;
import com.example.android.mealprepper.ViewModels.GroceryViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroceryListFragment extends Fragment {
    private List<Grocery> groceryList;
    private RecyclerView recyclerView;
    private GroceryListAdapter mAdapter;
    private ImageButton addGroceryImageButton;
    private GroceryViewModel mGroceryViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelProvider.Factory factory = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication());
        mGroceryViewModel = new ViewModelProvider(this, factory).get(GroceryViewModel.class);
        mGroceryViewModel.getAllGroceries().observe(this, new Observer<List<Grocery>>() {
            @Override
            public void onChanged(List<Grocery> groceries) {
                //update the cached copy of the words in the adapter.
                mAdapter.setGroceryList(groceryList);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grocery_list, container,false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new GroceryListAdapter(requireContext());

        //Connect Recycler View and Grocery Adapter to populate the Fragment
        recyclerView = view.findViewById(R.id.grocery_recycler_view);
        //attach adapter to create viewholders
        recyclerView.setAdapter(mAdapter);
        //use a linearlayout manager for vertical scrolling
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        //set line divders between each list item
        recyclerView.addItemDecoration(new DividerItemDecoration(super.getContext(), LinearLayoutManager.VERTICAL));


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

}
