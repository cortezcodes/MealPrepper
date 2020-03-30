package com.example.android.mealprepper;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Fragment used to add and edit Grocery List Items
 */
public class GroceryAddFragment extends Fragment{
    private EditText itemEditText, quantityEditText;
    private Spinner unitSpinner;
    private Button addEditButton, cancelButton;
    private GroceryViewModel mGroceryViewModel;
    private ArrayAdapter<String> spinnerAdapter;
    private List<String> spinnerOptions;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.grocery_add_fragment, container, false);

        this.itemEditText = view.findViewById(R.id.edit_text_item_name);
        this.quantityEditText = view.findViewById(R.id.edit_text_quantity);
        this.unitSpinner = view.findViewById(R.id.unit_spinner);
        this.addEditButton = view.findViewById(R.id.button_add_edit);
        this.cancelButton = view.findViewById(R.id.button_cancel);


        //Setup unit spinner
        spinnerOptions = new ArrayList<>();
        spinnerOptions.addAll(GroceryUtil.getSingleUnits());
        spinnerAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, spinnerOptions);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        this.unitSpinner.setAdapter(spinnerAdapter);

        //Get a new or existing ViewModel form the ViewModelProvider
        ViewModelProvider.Factory factory = new ViewModelProvider.AndroidViewModelFactory(this.getActivity().getApplication());
        this.mGroceryViewModel = new ViewModelProvider(this, factory).get(GroceryViewModel.class);

        //onChangeListener for quantity EditText to change spinner from singular to plural if
        this.quantityEditText.addTextChangedListener(new TextWatcher() {
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
        // add/edit button onClickListener that updates the database
        addEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = itemEditText.getText().toString();
                String quantity = quantityEditText.getText().toString();
                String units = unitSpinner.getSelectedItem().toString();

                if(item.matches("")){
                    Toast.makeText(getContext(), "Please add a Item name", Toast.LENGTH_SHORT).show();
                }else if(quantity.matches("") || quantity.matches("0")) {
                    Toast.makeText(getContext(), "Please fill in a quantity", Toast.LENGTH_SHORT).show();
                } else{
                    mGroceryViewModel.insert(new Grocery(item, Integer.parseInt(quantity), units));
                    exitDetailedView();
                }

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDetailedView();
            }
        });
        return view;
    }

    private void exitDetailedView(){
        getActivity().getSupportFragmentManager().popBackStack();
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
}
