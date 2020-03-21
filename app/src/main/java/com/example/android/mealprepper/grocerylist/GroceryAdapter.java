package com.example.android.mealprepper.grocerylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.mealprepper.Grocery;
import com.example.android.mealprepper.R;

import java.util.List;

import androidx.fragment.app.FragmentTransaction;
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
            this.editButton.setOnClickListener(this);
        }
        
        public void onClick(View view){
            Toast.makeText(view.getContext(), "Edit Button Selected", Toast.LENGTH_SHORT).show();
        }
    }

    GroceryAdapter(List<Grocery> groceries) {
        this.groceryList = groceries;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grocery_list_row, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Selected Grocery Item", Toast.LENGTH_SHORT).show();
            }
        });
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
