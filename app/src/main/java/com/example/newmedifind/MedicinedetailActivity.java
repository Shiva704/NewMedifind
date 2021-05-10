package com.example.newmedifind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    public String image_name,tempimage,medicinename,medicineprice,storename,contents,medicinequantity;
    public TextView med_name,med_price,med_content,med_storename;
    public Button location,order;
    public EditText et;
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

        String id=getIntent().getStringExtra("id");
        String username=LoginActivity.getUsername();

        reference.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    String medicinename=snapshot.child("medicinename").getValue().toString();
                    String medicinePrice=snapshot.child("medicineprice").getValue().toString();
                    String storeName=snapshot.child("storename").getValue().toString();
                    String contents=snapshot.child("contents").getValue().toString();

                    medicineprice=medicinePrice;
                    storename=storeName;
                    med_name.setText(medicinename);
                    med_price.setText("Rs."+medicinePrice);
                    med_storename.setText("Storename :"+ storeName);
                    med_content.setText(contents);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        medicinename=getIntent().getStringExtra("medicinename");
        String passstore=getIntent().getStringExtra("storename");
        //Log.i("value1",passstore);

        image_name=medicinename;
        tempimage=image_name+".jpg";

        //medicineprice=getIntent().getStringExtra("medicineprice");
        //storename=getIntent().getStringExtra("storename");
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


        EditText et = (EditText) findViewById(R.id.editText1);
        et.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "15")});
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MedicinedetailActivity.this,"Enter only number between 1 to 15",Toast.LENGTH_SHORT).show();
            }

        });




        location=(Button) findViewById(R.id.Location);
        order=(Button) findViewById(R.id.PlaceOrder);

       location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MedicinedetailActivity.this, MapsActivity.class);
                intent.putExtra("storename1",passstore);
                startActivity(intent);
            }
        });

        reference= FirebaseDatabase.getInstance().getReference("OrderSummary").child(username);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et.length()==0){
                    et.setError("enter Quantity");
                }
                else {
                    medicinequantity = et.getText().toString();

                    OrderAdder adder = new OrderAdder(medicinename, medicinequantity, medicineprice, storename);
                    reference.child(id).setValue(adder);
                    Toast.makeText(MedicinedetailActivity.this, "Item added to Cart", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}