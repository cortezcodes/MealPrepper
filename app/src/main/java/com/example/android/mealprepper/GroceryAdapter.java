package com.example.android.mealprepper;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.MyViewHolder> {

    List<Grocery> groceryList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item, quantity, units, price;

        public MyViewHolder(View view) {
            super(view);
            this.item = view.findViewById(R.id.text_view_item_name);
            this.quantity = view.findViewById(R.id.text_view_item_quantity);
            this.units = view.findViewById(R.id.text_view_item_unit);
            this.price = view.findViewById(R.id.text_view_price);
        }
    }

    public GroceryAdapter(List<Grocery> groceries) {
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
        String price = "Price: $" + grocery.getPricePerUnit();
        holder.price.setText(price);
    }

    @Override
    public int getItemCount() {
        return groceryList.size();
    }
}
