package com.example.android.mealprepper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.mealprepper.grocerylist.GroceryFragmentActivity;

public class MainActivity extends AppCompatActivity {
    private Button groceryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create onClickListener for Grocery Button
        groceryButton = findViewById(R.id.button_grocery_activity);
        groceryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GroceryFragmentActivity.class);
                startActivity(intent);
            }
        });

    }
}
