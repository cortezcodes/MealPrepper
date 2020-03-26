package com.example.android.mealprepper.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.mealprepper.Model.Grocery;
import com.example.android.mealprepper.R;
import com.example.android.mealprepper.Utilities.GroceryUtil;
import com.example.android.mealprepper.ViewModels.GroceryViewModel;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class GroceryDetailedFragment extends Fragment {
    private Button cancelButton, addButton;
    private EditText itemEditText, quantityEditText, unitEdittext;
    private GroceryViewModel mGroceryViewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grocery_detailed, parent, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        itemEditText = view.findViewById(R.id.edit_text_item_name);
        quantityEditText = view.findViewById(R.id.edit_text_quantity);
        unitEdittext = view.findViewById(R.id.edit_text_units);
        mGroceryViewModel = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(GroceryViewModel.class);


        //if arguments were passed from another fragment set the information in the views;
        Bundle bundle = getArguments();
        if(bundle != null){
            itemEditText.setText(bundle.get(GroceryUtil.ITEM).toString());
            quantityEditText.setText(bundle.get(GroceryUtil.QUANTITY).toString());
            unitEdittext.setText(bundle.get(GroceryUtil.UNITS).toString());
        }

        //TODO Update the Cancel button

        // Setup any handles to view objects here
        //Setup cancel button to go back on the transaction
        cancelButton = view.findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
                if(count > 0){
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });

        //Click Listener on Add Button. Add button will add the grocery
        //to the database and go back to the previous fragment
        addButton = view.findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Grocery grocery;
                if(itemEditText.getText() != null && quantityEditText != null){
                        grocery = new Grocery(itemEditText.getText().toString(),
                                Integer.parseInt(quantityEditText.getText().toString()),
                                unitEdittext.getText().toString());
                }
                else{
                    Toast.makeText(getContext(), "Must input both an item and a quantity", Toast.LENGTH_SHORT).show();
                    return;
                }
                mGroceryViewModel.insertGrocery(grocery);
            }
        });
    }

    private void backToLastFragment(){
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
