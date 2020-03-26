package com.example.android.mealprepper.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.mealprepper.Model.Grocery;
import com.example.android.mealprepper.R;
import com.example.android.mealprepper.TestData.GroceryTestData;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroceryListAdapter extends RecyclerView.Adapter<GroceryListAdapter.GroceryViewHolder> {
    public final LayoutInflater mInflater;
    private List<Grocery> mGroceryList; //Cached copy of the Groceries

    /**
     * Constructor
     * @param context context of the calling activity
     */
    public GroceryListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public GroceryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.grocery_list_item, parent, false);
        return new GroceryViewHolder(itemView);
    }


    /**
     * OnBindView checks to see if a grocerylist has already been populated
     * If so, then it get the grocery and populates a view in the holder.
     * If it has not then it will create a blank grocery item that states
     * to add groceries
     * @param holder the ViewHolder holding the view
     * @param position the position in the RecycleView of the holder
     */
    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {
        if(mGroceryList != null){
            Grocery grocery = mGroceryList.get(position);
            holder.item.setText(grocery.getItem());
            holder.quantity.setText(Integer.toString(grocery.getQuantity()));
            holder.units.setText(grocery.getUnitOfMeasurement());
        } else {
            //covers the case of data not being ready.
            holder.item.setText("Add groceries");
            holder.quantity.setText(" ");
            holder.units.setText(" ");
        }
    }

    /**
     * Setter for the GroceryList List. Notifies of data change.
     * @param groceries List of groceries
     */
    public void setGroceryList(List<Grocery> groceries){
        mGroceryList = groceries;
        notifyDataSetChanged();
    }

    /**
     * Returns a count of the number of Groceries in GroceryList.
     * When it is first called mGroceryList will be null because it has
     * not been updated. We cannot return null so we return 0.
     * @return returns the number of groceries in mGroceryList
     */
    @Override
    public int getItemCount() {
        if(mGroceryList != null){
            return mGroceryList.size();
        }else { return 0;}
    }


    class GroceryViewHolder extends RecyclerView.ViewHolder{
        private TextView item, quantity, units;
        private ImageButton editButton;


        GroceryViewHolder(View view) {
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

            //view.setOnClickListener(this);
        }


      /*  @Override
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

        }*/
    }







}
