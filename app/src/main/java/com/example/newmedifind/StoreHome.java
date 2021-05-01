package com.example.newmedifind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StoreHome extends AppCompatActivity {

    Button Add_medicine, Edit_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_home);

        Add_medicine= findViewById(R.id.Add_Medicine);
        Edit_quantity = findViewById(R.id.Edit_Quantity);
        Add_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreHome.this,AddMedicine.class);
                startActivity(intent);
            }
        });

        Edit_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreHome.this, EditQuantity.class);
                startActivity(intent);
            }
        });
    }
}