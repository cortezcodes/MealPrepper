package com.example.android.mealprepper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GroceryListFragment extends Fragment {

    private GroceryViewModel mGroceryViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grocery_list_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.grocery_recycler_view);
        final GroceryListAdapater adapter = new GroceryListAdapater(this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        //Get a new or existing ViewModel form the ViewModelProvider
        ViewModelProvider.Factory factory = new ViewModelProvider.AndroidViewModelFactory(this.getActivity().getApplication());
        mGroceryViewModel = new ViewModelProvider(this, factory).get(GroceryViewModel.class);

        // Add an observer on the LiveData returned by getList.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mGroceryViewModel.getGroceryList().observe(this, new Observer<List<Grocery>>() {
            @Override
            public void onChanged(List<Grocery> groceries) {
                //update the cashed copy of the words in the adapter
                adapter.setGroceryList(groceries);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
