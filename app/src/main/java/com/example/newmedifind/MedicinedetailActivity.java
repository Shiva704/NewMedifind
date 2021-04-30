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
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class MedicinedetailActivity extends AppCompatActivity {

    public String image_name,tempimage,m_name,m_price,m_storename,m_content;
    public TextView med_name,med_price,med_content,med_storename;
    public Button location,order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicinedetail);

        m_name=getIntent().getStringExtra("Name");
        image_name=m_name;
        tempimage=image_name+".jpg";

        m_price=getIntent().getStringExtra("Name");
        m_storename=getIntent().getStringExtra("Name");
        m_content=getIntent().getStringExtra("Name");

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

        med_name=(TextView)findViewById(R.id.forMedName);
        med_price=(TextView)findViewById(R.id.forMedPrice);
        med_content=(TextView)findViewById(R.id.forMedContent);
        med_storename=(TextView)findViewById(R.id.forMedStorename);

        med_name.setText(m_name);
        med_price.setText(m_price);
        med_storename.setText(m_storename);
        med_content.setText(m_content);

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