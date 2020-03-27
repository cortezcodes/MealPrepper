package com.example.android.mealprepper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

/**
 * Fragment used to add and edit Grocery List Items
 */
public class GroceryDetailFragment extends Fragment {
    private EditText itemEditText, quantityEditText, unitEditText;
    private Button addEditButton, cancelButton;
    private GroceryViewModel mGroceryViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.grocery_detail_fragment, container, false);

        this.itemEditText = view.findViewById(R.id.edit_text_item_name);
        this.quantityEditText = view.findViewById(R.id.edit_text_quantity);
        this.unitEditText = view.findViewById(R.id.edit_text_units);
        this.addEditButton = view.findViewById(R.id.button_add_edit);
        this.cancelButton = view.findViewById(R.id.button_cancel);

        //Get a new or existing ViewModel form the ViewModelProvider
        ViewModelProvider.Factory factory = new ViewModelProvider.AndroidViewModelFactory(this.getActivity().getApplication());
        this.mGroceryViewModel = new ViewModelProvider(this, factory).get(GroceryViewModel.class);

        //TODO Add Grocery Functionality
        addEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Add item", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }
}
