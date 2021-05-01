package com.example.newmedifind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditQuantity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerEdit recyclerEdit;
    FirebaseDatabase rootnode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quantity);

        recyclerView=findViewById(R.id.recyclerview);

        FirebaseRecyclerOptions<UserHelperClass> options =
                new FirebaseRecyclerOptions.Builder<UserHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("MedicineDetails"), UserHelperClass.class)
                        .build();


        recyclerEdit=new RecyclerEdit(options);

        recyclerView.setAdapter(recyclerEdit);

    }
    @Override
    protected void onStart() {
        super.onStart();
        recyclerEdit.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        recyclerEdit.stopListening();
    }
}