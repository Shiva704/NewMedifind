package com.example.newmedifind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStore extends AppCompatActivity {
    ImageView Logoimage;
    TextView Slogan;
    TextInputLayout StoreID,StoreUsername, StorePhonenumber, StorePassword, Location,Storename1;
    Button Loginback;
    FirebaseDatabase rootnode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_store);
        intia();
        Listners();
    }

    private Boolean validateID() {
        String val= StoreID.getEditText().getText().toString();

        if(val.isEmpty()){
            StoreID.setError("Field cannot be Empty");
            return false;
        }
        else{
            StoreID.setError(null);
            StoreID.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateStoreName() {
        String val= Storename1.getEditText().getText().toString();

        if(val.isEmpty()){
            Storename1.setError("Field cannot be Empty");
            return false;
        }
        else{
            Storename1.setError(null);
            Storename1.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateName() {
        String val = StoreUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            StoreUsername.setError("Field cannot be Empty");
            return false;
        } else if (val.length() >= 15) {
            StoreUsername.setError("Username too Long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            StoreUsername.setError("White space not allowed");
            return false;
        } else {
            StoreUsername.setError(null);
            StoreUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhonenumber() {
        String val = StorePhonenumber.getEditText().getText().toString();

        if (val.isEmpty()) {
            StorePhonenumber.setError("Field cannot be Empty");
            return false;
        } else {
            StorePhonenumber.setError(null);
            StorePhonenumber.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateLocation() {
        String val =Location.getEditText().getText().toString();

        if (val.isEmpty()) {
            Location.setError("Field cannot be Empty");
            return false;
        } else {
            Location.setError(null);
            Location.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = StorePassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";


        if (val.isEmpty()) {
            StorePassword.setError("Field cannot be Empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            StorePassword.setError("Password is too weak");
            return false;
        } else {
            StorePassword.setError(null);
            StorePassword.setErrorEnabled(false);
            return true;
        }
    }



    private void Listners() {

        Loginback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootnode = FirebaseDatabase.getInstance();
                reference = rootnode.getReference("StoreDetails");

                Boolean validateName = validateName();
                Boolean validatePhonenumber = validatePhonenumber();
                Boolean validatePassword = validatePassword();
                Boolean validateID=validateID();
                Boolean validateLocation= validateLocation();
                Boolean validateStoreName=validateStoreName();


                if (!validateName | !validatePhonenumber | !validateID | !validatePassword | !validateLocation | !validateStoreName) {
                    return;
                }


                String storeusername = StoreUsername.getEditText().getText().toString();
                String storephonenumber = StorePhonenumber.getEditText().getText().toString();
                String storename1 = Storename1.getEditText().getText().toString();
                String storepassword = StorePassword.getEditText().getText().toString();
                String location = Location.getEditText().getText().toString();
                String id1 = StoreID.getEditText().getText().toString();


                StoreUserHelperClass helperClass = new StoreUserHelperClass(storeusername,storephonenumber,storename1,storepassword,location,id1);
                reference.child(id1).setValue(helperClass);

            }
        });

    }

    private void intia() {
        Logoimage = findViewById(R.id.LogoImage);
        Slogan = findViewById(R.id.Slogon);
        StoreID=findViewById(R.id.addid);
        Storename1=findViewById(R.id.storename);
        StoreUsername = findViewById(R.id.storeusername);
        StorePhonenumber = findViewById(R.id.phonenumber);
        Location= findViewById(R.id.location);
        StorePassword = findViewById(R.id.password);
        Loginback = findViewById(R.id.Loginback);
    }

}
