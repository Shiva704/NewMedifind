package com.example.newmedifind;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerEdit extends FirebaseRecyclerAdapter<UserHelperClass,RecyclerEdit.ViewHolder>{


    public RecyclerEdit(@NonNull FirebaseRecyclerOptions<UserHelperClass> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, final int position, @NonNull UserHelperClass model) {

        String storename = model.getStorename();
        if (storename.equalsIgnoreCase(StoreLogin.getUsername()))
        {
            holder.v.setVisibility(View.VISIBLE);
            holder.MedicineName.setText(model.getMedicinename());
            holder.PresentQuantity.setText(model.getQuantity());

            holder.Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final DialogPlus dialogPlus = DialogPlus.newDialog(holder.MedicineName.getContext())
                            .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.edit_dialog))
                            .setExpanded(true, 500)
                            .create();

                    View myview = dialogPlus.getHolderView();
                    EditText quantity = myview.findViewById(R.id.newquantity);
                    Button submit = myview.findViewById(R.id.submit);

                    quantity.setText(model.getQuantity());
                    dialogPlus.show();

                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Map<String, Object> map = new HashMap<>();
                                map.put("contents", model.getContents().toString());
                                map.put("id", model.getId().toString());
                                map.put("medicinename", model.getMedicinename().toString());
                                map.put("medicineprice", model.getMedicineprice().toString());
                                map.put("storename", model.getStorename().toString());
                                map.put("quantity", quantity.getText().toString());

                                FirebaseDatabase.getInstance().getReference().child("MedicineDetails")
                                        .child(getRef(position).getKey()).updateChildren(map)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                dialogPlus.dismiss();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                dialogPlus.dismiss();
                                            }
                                        });
                            }
                        });
                }
            });
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

        TextView MedicineName, PresentQuantity, Quantitytext;
        ImageView Edit;
        View v;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Edit=itemView.findViewById(R.id.edit_icon);
            MedicineName=itemView.findViewById(R.id.medicinename);
            PresentQuantity=itemView.findViewById(R.id.medicinequantity);
            Quantitytext=itemView.findViewById(R.id.quantext);
            v=itemView;

        }
    }
}