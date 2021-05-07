package com.example.newmedifind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StoreHome extends AppCompatActivity {

    Button Add_medicine, Edit_quantity, Add_Store;
    TextView Display;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_home);

        Edit_quantity=findViewById(R.id.Edit_Quantity);
        Add_medicine= findViewById(R.id.Add_Medicine);
        Display= findViewById(R.id.welcome);
        Add_Store=findViewById(R.id.Store_Add);

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

        Add_Store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(StoreHome.this,AddStore.class);
                startActivity(intent);
            }
        });
    }
}