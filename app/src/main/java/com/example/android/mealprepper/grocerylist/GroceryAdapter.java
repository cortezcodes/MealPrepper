package com.example.android.mealprepper.grocerylist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.mealprepper.Grocery;
import com.example.android.mealprepper.R;

import java.util.List;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.MyViewHolder> {

    private List<Grocery> groceryList;

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView item, quantity, units;
        ImageButton editButton;

        MyViewHolder(View view) {
            super(view);
            this.item = view.findViewById(R.id.text_view_item_name);
            this.quantity = view.findViewById(R.id.text_view_item_quantity);
            this.units = view.findViewById(R.id.text_view_item_unit);
            this.editButton = view.findViewById(R.id.image_button_edit);
            this.editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Edit Button Selected", Toast.LENGTH_SHORT).show();
                }
            });

            view.setOnClickListener(this);
        }

        /**
         * TODO Attach the correct units spinner selection
         * Used to open the GroceryDetailedFragment with the appropriate data from a clicked view
         * @param v View that wis attached to the ViewHolder and being attached to the onClickListener
         */
        @Override
        public void onClick(View v) {
            //create the new GroceryDetailedfragment
            GroceryDetailedFragment groceryDetailedFragment = new GroceryDetailedFragment();
            FragmentActivity fragmentActivity = (FragmentActivity) v.getContext();

            //Create the information you need to pass to the detailed fragment
            //Then pass it to the fragment as arguments
            Bundle bundle = new Bundle();
            bundle.putString(GroceryUtil.ITEM, item.getText().toString());
            bundle.putString(GroceryUtil.QUANTITY, quantity.getText().toString());
            bundle.putString(GroceryUtil.UNITS, units.getText().toString());
            groceryDetailedFragment.setArguments(bundle);
            fragmentActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout_container, groceryDetailedFragment)
                    .addToBackStack(null).commit();

        }
    }

    GroceryAdapter(List<Grocery> groceries) {
        this.groceryList = groceries;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grocery_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Grocery grocery = groceryList.get(position);
        holder.item.setText(grocery.getName());
        holder.quantity.setText(Integer.toString(grocery.getQuantity()));
        holder.units.setText(grocery.getUnitOfMeasurement());
    }

    @Override
    public int getItemCount() {
        return groceryList.size();
    }


}
