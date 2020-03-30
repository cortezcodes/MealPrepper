package com.example.android.mealprepper;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.fragment.app.FragmentActivity;
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
            final Grocery current = mGroceryList.get(position);
            holder.itemNameView.setText(current.getItem());
            holder.amountView.setText(Integer.toString(current.getAmount()));
            holder.unitsView.setText(current.getUnitOfMeasure());

            // set onClickListener for edit Grocery Item to pass a bundle and
            // open new GroceryEditFragment
            holder.editImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentActivity fa = (FragmentActivity) v.getContext();
                    GroceryEditFragment groceryEditFragment = new GroceryEditFragment();
                    Bundle bundle = current.createBundle();
                    groceryEditFragment.setArguments(bundle);
                    fa.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout_container,groceryEditFragment)
                            .addToBackStack("").commit();
                }
            });
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

    class GroceryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView itemNameView;
        private final TextView amountView;
        private final TextView unitsView;
        private final ImageButton editImageButton;

        private GroceryViewHolder(View itemView){
            super(itemView);
            this.itemNameView = itemView.findViewById(R.id.text_view_item_name);
            this.amountView = itemView.findViewById(R.id.text_view_item_quantity);
            this.unitsView = itemView.findViewById(R.id.text_view_item_unit);
            this.editImageButton = itemView.findViewById(R.id.image_button_edit);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Cross Out Item", Toast.LENGTH_SHORT).show();
        }
    }
}
