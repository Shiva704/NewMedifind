package com.example.newmedifind;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class RecyclerEdit extends FirebaseRecyclerAdapter<UserHelperClass,RecyclerEdit.ViewHolder>{


    public RecyclerEdit(@NonNull FirebaseRecyclerOptions<UserHelperClass> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull UserHelperClass model) {

        String storename = model.getStorename();
        if (storename.equals("Apollo"))
        {
            holder.v.setVisibility(View.VISIBLE);
            holder.MedicineName.setText(model.getMedicinename()+position);
        }
        else {
            holder.v.setVisibility(View.GONE);
        }



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_quantity_row,parent,false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView MedicineName;
        View v;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            MedicineName=itemView.findViewById(R.id.medicinename);
            v=itemView;

        }
    }
}