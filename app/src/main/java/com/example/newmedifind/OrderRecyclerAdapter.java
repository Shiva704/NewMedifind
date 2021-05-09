package com.example.newmedifind;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class OrderRecyclerAdapter extends FirebaseRecyclerAdapter<OrderAdder,OrderRecyclerAdapter.ViewHolder> {

    String image_name,tempimage;


    public OrderRecyclerAdapter(@NonNull FirebaseRecyclerOptions<OrderAdder> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull OrderAdder model) {

        //float price= Float.parseFloat(model.getMed_price())*Float.parseFloat(model.getMed_quantity());
        //String.valueOf(price)
        holder.MedicineName.setText(model.getMed_name());
        holder.MedicinePrice.setText(model.getMed_price());
        holder.StoreName.setText("Sold By "+model.getStore_name());
        holder.Quantity.setText("Quantity-"+model.getMed_quantity());

       // Glide.with(holder.imageView.getContext()).load(model.getUrl()).into(holder.imageView);
        String medicinename= model.getMed_name();
        image_name=medicinename;
        tempimage=image_name+".jpg";

        //medicineprice=getIntent().getStringExtra("medicineprice");
        //storename=getIntent().getStringExtra("storename");
        //contents=getIntent().getStringExtra("contents");

        StorageReference mStorageReference = FirebaseStorage.getInstance().getReference().child(tempimage);

        try {
            final File localFile= File.createTempFile(image_name,"jpg");
            mStorageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                           // Toast.makeText(OrderRecyclerAdapter.this,"Image Retrieved, you are blessed",Toast.LENGTH_SHORT).show();
                            Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            holder.imageView.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                  //  Toast.makeText(com.example.newmedifind.MedicinedetailActivity.this,"Error occured, you are not blessed",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderrow_item,parent,false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        ImageView imageView;
        TextView MedicineName,MedicinePrice,StoreName, Quantity;
        View v;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView11);
            MedicineName=itemView.findViewById(R.id.medicinename);
            MedicinePrice=itemView.findViewById(R.id.medicineprice);
            StoreName=itemView.findViewById(R.id.storename);
            Quantity=itemView.findViewById(R.id.quantity);
            v=itemView;

        }

    }
}