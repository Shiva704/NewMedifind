package com.example.newmedifind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMedicine extends AppCompatActivity {

    TextView Slogan;
    TextInputLayout ID,MedicineName,MedicinePrice,Contents,Quantity,StoreName;
    Button ADD;
    FirebaseDatabase rootnode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);
        intia();
        Listners();
    }

    private Boolean validateMedicineName() {
        String val= MedicineName.getEditText().getText().toString();

        if(val.isEmpty()){
            MedicineName.setError("Field cannot be Empty");
            return false;
        }
        else{
            MedicineName.setError(null);
            MedicineName.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateContents() {
        String val= Contents.getEditText().getText().toString();

        if(val.isEmpty()){
            Contents.setError("Field cannot be Empty");
            return false;
        }
        else{
            Contents.setError(null);
            Contents.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateQuantity() {
        String val= Quantity.getEditText().getText().toString();

        if(val.isEmpty()){
            Quantity.setError("Field cannot be Empty");
            return false;
        }
        else{
            Quantity.setError(null);
            Quantity.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateStoreName() {
        String val= StoreName.getEditText().getText().toString();

        if(val.isEmpty()){
            StoreName.setError("Field cannot be Empty");
            return false;
        }
        else{
            StoreName.setError(null);
            StoreName.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateID() {
        String val= ID.getEditText().getText().toString();

        if(val.isEmpty()){
            ID.setError("Field cannot be Empty");
            return false;
        }
        else{
            ID.setError(null);
            ID.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateMedicinePrice() {
        String val=MedicinePrice.getEditText().getText().toString();

        if(val.isEmpty()){
            MedicinePrice.setError("Field cannot be Empty");
            return false;
        }
        else{
            MedicinePrice.setError(null);
            MedicinePrice.setErrorEnabled(false);
            return true;
        }
    }


    private void Listners(){


        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootnode= FirebaseDatabase.getInstance();
                reference= rootnode.getReference("MedicineDetails");

                Boolean validateMedicineName= validateMedicineName();
                Boolean validateContents=validateContents();
                Boolean validateQuantity=validateQuantity();
                Boolean validateStoreName= validateStoreName();
                Boolean validateID=validateID();
                Boolean validateMedicinePrice=validateMedicinePrice();

                if(!validateMedicineName | !validateContents | !validateMedicinePrice | !validateQuantity | !validateStoreName | !validateID){
                    return;
                }

                String medicinename= MedicineName.getEditText().getText().toString();
                String medicineprice= MedicinePrice.getEditText().getText().toString();
                String contents = Contents.getEditText().getText().toString();
                String quantity= Quantity.getEditText().getText().toString();
                String storename= StoreName.getEditText().getText().toString();
                String id= ID.getEditText().getText().toString();

                UserHelperClass helperClass= new UserHelperClass( medicinename,medicineprice,contents,storename,id,quantity);
                reference.child(id).setValue(helperClass);
                Toast.makeText(AddMedicine.this, "Medicine Added", Toast.LENGTH_SHORT).show();

            }
        });
    }




    private void intia(){
        Slogan=findViewById(R.id.meddicinedetails);
        ID=findViewById(R.id.addid);
        MedicineName=findViewById(R.id.addmedicinename);
        MedicinePrice=findViewById(R.id.addmedicineprice);
        Contents=findViewById(R.id.addcontents);
        Quantity=findViewById(R.id.addqunatity);
        StoreName=findViewById(R.id.addstorename);
        ADD=findViewById(R.id.addthemedicine);
    }
}