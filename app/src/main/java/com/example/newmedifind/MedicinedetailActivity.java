package com.example.newmedifind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class MedicinedetailActivity extends AppCompatActivity {

    public String image_name,tempimage,medicinename,medicineprice,storename,contents;
    public TextView med_name,med_price,med_content,med_storename;
    public Button location,order;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicinedetail);

        med_name=(TextView)findViewById(R.id.forMedName);
        med_price=(TextView)findViewById(R.id.forMedPrice);
        med_content=(TextView)findViewById(R.id.forMedContent);
        med_storename=(TextView)findViewById(R.id.forMedStorename);

        reference= FirebaseDatabase.getInstance().getReference().child("MedicineDetails");

        medicinename=getIntent().getStringExtra("medicinename");
        image_name=medicinename;
        tempimage=image_name+".jpg";

        medicineprice=getIntent().getStringExtra("medicineprice");
        storename=getIntent().getStringExtra("storename");
        contents=getIntent().getStringExtra("contents");

        StorageReference mStorageReference = FirebaseStorage.getInstance().getReference().child(tempimage);

        try {
            final File localFile= File.createTempFile(image_name,"jpg");
            mStorageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(com.example.newmedifind.MedicinedetailActivity.this,"Image Retrieved, you are blessed",Toast.LENGTH_SHORT).show();
                            Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            ((ImageView) findViewById(R.id.imageViewmed)).setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(com.example.newmedifind.MedicinedetailActivity.this,"Error occured, you are not blessed",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        reference.child(medicinename).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    String medicinename=snapshot.child("medicinename").getValue().toString();
                    String medicineprice=snapshot.child("medicineprice").getValue().toString();
                    String storename=snapshot.child("storename").getValue().toString();
                    String contents=snapshot.child("contents").getValue().toString();

                    med_name.setText(medicinename);
                    med_price.setText(medicineprice);
                    med_storename.setText(storename);
                    med_content.setText(contents);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        location=(Button) findViewById(R.id.Location);
        order=(Button) findViewById(R.id.PlaceOrder);

//        location.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(MedicinedetailsActivity.this,   .class);
//
//                startActivity(intent);
//            }
//        });
//
//        order.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(MedicinedetailsActivity.this,   .class);
//
//                startActivity(intent);
//            }
//        });

    }
}