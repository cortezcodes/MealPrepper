package com.example.android.mealprepper.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.mealprepper.R;
import com.example.android.mealprepper.Utilities.GroceryUtil;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class GroceryDetailedFragment extends Fragment {
    private Button cancelButton;
    private TextView itemName, quantity;
    private Spinner unitSpinner;
    private ArrayAdapter<String> spinnerDataAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grocery_detailed, parent, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        itemName = view.findViewById(R.id.edit_text_item_name);
        quantity = view.findViewById(R.id.edit_text_quantity);
        unitSpinner = view.findViewById(R.id.spinner_units);

        //add the options within the unit spinner
        spinnerDataAdapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_dropdown_item, GroceryUtil.getSpinnerData());
        unitSpinner.setAdapter(spinnerDataAdapter);

        //if arguments were passed from another fragment set the information in the views;
        Bundle bundle = getArguments();
        if(bundle != null){
            itemName.setText(bundle.get(GroceryUtil.ITEM).toString());
            quantity.setText(bundle.get(GroceryUtil.QUANTITY).toString());
        }



        // Setup any handles to view objects here
        //Setup cancel button to go back on the transaction
        cancelButton = view.findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                if(fm.getBackStackEntryCount() > 0){
                    fm.popBackStack();
                }
            }
        });
    }
}
