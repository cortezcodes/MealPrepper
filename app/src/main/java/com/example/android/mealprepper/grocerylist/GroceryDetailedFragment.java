package com.example.android.mealprepper.grocerylist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.mealprepper.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class GroceryDetailedFragment extends Fragment {
    private Button cancelButton;
    private TextView itemName, quantity;

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_grocery, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        itemName = view.findViewById(R.id.edit_text_item_name);
        quantity = view.findViewById(R.id.edit_text_quantity);

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
