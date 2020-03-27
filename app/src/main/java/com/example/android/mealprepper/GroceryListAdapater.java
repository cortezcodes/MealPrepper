package com.example.android.mealprepper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class GroceryListAdapater extends RecyclerView.Adapter<GroceryListAdapater.GroceryViewHolder> {

    private final LayoutInflater mInflater;
    private List<Grocery> mGroceryList; // cached copy of the items

    public GroceryListAdapater(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public GroceryViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.grocery_list_item, parent, false);
        return new GroceryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GroceryViewHolder holder, int position){
        if(mGroceryList != null){
            Grocery current = mGroceryList.get(position);
            holder.itemNameView.setText(current.getItem());
            holder.amountView.setText(Integer.toString(current.getAmount()));
            holder.unitsView.setText(current.getUnitOfMeasure());
        } else {
            //Covers the case of data not being ready yet.
            holder.itemNameView.setText("Add Grocery Items");
            holder.amountView.setText(" ");
            holder.unitsView.setText(" ");
        }
    }

    public void setGroceryList(List<Grocery> groceryList){
        mGroceryList = groceryList;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount(){
       if(mGroceryList != null){
            return mGroceryList.size();
        } else{
           return 0;
        }
    }

    class GroceryViewHolder extends RecyclerView.ViewHolder{
        private final TextView itemNameView;
        private final TextView amountView;
        private final TextView unitsView;

        private GroceryViewHolder(View itemView){
            super(itemView);
            this.itemNameView = itemView.findViewById(R.id.text_view_item_name);
            this.amountView = itemView.findViewById(R.id.text_view_item_quantity);
            this.unitsView = itemView.findViewById(R.id.text_view_item_unit);
        }
    }

}
