package com.example.newmedifind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SerachTab extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    FirebaseDatabase rootnode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach_tab);

        recyclerView=findViewById(R.id.recyclerview);

        FirebaseRecyclerOptions<UserHelperClass> options =
                new FirebaseRecyclerOptions.Builder<UserHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("MedicineDetails"), UserHelperClass.class)
                        .build();


        recyclerAdapter=new RecyclerAdapter(options);

        recyclerView.setAdapter(recyclerAdapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        recyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        recyclerAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView= (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private  void processsearch(String s){
        FirebaseRecyclerOptions<UserHelperClass> options =
                new FirebaseRecyclerOptions.Builder<UserHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("MedicineDetails").orderByChild("medicinename").startAt(s).endAt(s+"\uf8ff"), UserHelperClass.class)
                        .build();

        recyclerAdapter= new RecyclerAdapter(options);
        recyclerAdapter.startListening();
        recyclerView.setAdapter(recyclerAdapter);

    }
}