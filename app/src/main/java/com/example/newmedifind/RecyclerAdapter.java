package com.example.newmedifind;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends FirebaseRecyclerAdapter<UserHelperClass,RecyclerAdapter.ViewHolder>{


    public RecyclerAdapter(@NonNull FirebaseRecyclerOptions<UserHelperClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull UserHelperClass model) {

        holder.MedicineName.setText(model.getMedicinename());
        holder.MedicinePrice.setText(model.getMedicineprice());
        holder.StoreName.setText("Storename-"+model.getStorename());
        holder.Quantity.setText("Quantity-"+model.getQuantity());

        String sum1=model.getStorename();
        String sum=model.getMedicinename();

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MedicinedetailActivity.class);
                intent.putExtra("id",getRef(position).getKey());
                intent.putExtra("medicinename",sum);
                intent.putExtra("storename",sum1);
                v.getContext().startActivity(intent);

            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView MedicineName,MedicinePrice,StoreName, Quantity;
        View v;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            MedicineName=itemView.findViewById(R.id.medicinename);
            MedicinePrice=itemView.findViewById(R.id.medicineprice);
            StoreName=itemView.findViewById(R.id.storename);
            Quantity=itemView.findViewById(R.id.quantity);
            v=itemView;

        }

    }
}