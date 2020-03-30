package com.example.android.mealprepper;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class GroceryEditFragment extends Fragment {
    private static Grocery grocery;
    private EditText itemView, amountView;
    private Spinner spinnerView;
    private Button saveButton, cancelButton, deleteButton;
    private List<String> spinnerOptions;
    private ArrayAdapter<String> spinnerAdapter;
    private GroceryViewModel mGroceryViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if( args != null){
            int id = args.getInt(Grocery.ID_KEY);
            String item = args.getString(Grocery.ITEM_KEY);
            int amount = args.getInt(Grocery.AMOUNT_KEY);
            String units = args.getString(Grocery.UNITS_KEY);
            grocery = new Grocery(id, item, amount, units);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grocery_edit_fragment, container, false);
        itemView = view.findViewById(R.id.groceryEditFragment_editText_itemName);
        amountView = view.findViewById(R.id.groceryEditFragment_editText_quantity);
        spinnerView = view.findViewById(R.id.groceryEditFragment_spinner_units);
        saveButton = view.findViewById(R.id.groceryEditFragment_button_save_changes);
        cancelButton = view.findViewById(R.id.groceryEditFragment_button_cancel);
        deleteButton = view.findViewById(R.id.groceryEditFragment_button_delete);
        mGroceryViewModel = new GroceryViewModel(getActivity().getApplication());

        //Get a new or existing ViewModel form the ViewModelProvider
        ViewModelProvider.Factory factory = new ViewModelProvider.AndroidViewModelFactory(this.getActivity().getApplication());
        mGroceryViewModel = new ViewModelProvider(this, factory).get(GroceryViewModel.class);

        //set the views with the selected grocery information
        itemView.setText(grocery.getItem());
        amountView.setText(Integer.toString(grocery.getAmount()));

        //setup spinner and select the correct units
       spinnerOptions = new ArrayList<String>();
        if(grocery.getAmount() > 1) {
            spinnerOptions.addAll(GroceryUtil.getPluralUnits());
        } else{
            spinnerOptions.addAll(GroceryUtil.getSingleUnits());
        }
        spinnerAdapter = new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,
                spinnerOptions);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerView.setAdapter(spinnerAdapter);
        spinnerView.setSelection(getSpinnerSelectionPositionFromName(grocery.getUnitOfMeasure()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setup adaptive spinner based off number input in amountView
        amountView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0) {
                    if (Integer.parseInt(s.toString()) > 1) {
                        spinnerOptionControl(true);

                    } else {
                        spinnerOptionControl(false);
                    }
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDetailedView();
            }
        });

        //TODO delete save functionality on EditFragment
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGroceryViewModel.delete(grocery);
                exitDetailedView();

            }
        });

        //TODO Add save functionality on EditFragment
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "save Changes", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Finds the Position of the spinner by a given String name
     * @param name String of the item searching for in spinner
     * @return the given position of the String in the spinner
     */
    private int getSpinnerSelectionPositionFromName(String name){
        int count = spinnerView.getAdapter().getCount();
        for(int i = 0; i < count; i++){
            String item = spinnerView.getItemAtPosition(i).toString();
            if(name.equalsIgnoreCase(item)){
                return i;
            }
        }
        return -155;
    }

    /**
     * Dynamically updates the Unit Spinner so that when QuantityEditText view has a value greater
     * than 1 then it makes all the options plural.
     * @param isPlural if this is true then the spinner will set plural values
     */
    private void spinnerOptionControl(boolean isPlural){
        if(isPlural){
            spinnerOptions.clear();
            spinnerOptions.addAll(GroceryUtil.getPluralUnits());
            spinnerAdapter.notifyDataSetChanged();
        }else {
            spinnerOptions.clear();
            spinnerOptions.addAll(GroceryUtil.getSingleUnits());
            spinnerAdapter.notifyDataSetChanged();
        }
    }

    /**
     * popBackStack() wrapper
     */
    private void exitDetailedView(){
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
