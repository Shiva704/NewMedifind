package com.example.newmedifind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StoreLogin extends AppCompatActivity {

    ImageView Logoimage;
    TextView Hello, declaration;
    TextInputLayout Username, Password;
    Button Login_in , Forgot_password;
    FirebaseDatabase rootnode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_login);
        intia();
        Listner();
    }


    private Boolean validateName() {
        String val= Username.getEditText().getText().toString();

        if(val.isEmpty()){
            Username.setError("Field cannot be Empty");
            return false;
        }
        else{
            Username.setError(null);
            Username.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword(){
        String val=Password.getEditText().getText().toString();


        if(val.isEmpty()){
            Password.setError("Field cannot be Empty");
            return false;
        }
        else{
            Password.setError(null);
            Password.setErrorEnabled(false);
            return true;
        }
    }
    private  void isUser() {
        String UsernameEnteredValue= Username.getEditText().getText().toString().trim();
        String PasswordEnteredValue= Password.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Coustmers");

        Query CheckUser = reference.orderByChild("username").equalTo(UsernameEnteredValue);

        CheckUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    Username.setError(null);
                    Username.setErrorEnabled(false);

                    String PasswordFromDB = dataSnapshot.child(UsernameEnteredValue).child("password").getValue(String.class);

                    if(PasswordFromDB.equals(PasswordEnteredValue)){
                        Password.setError(null);
                        Password.setErrorEnabled(false);

                        String usernameFromDB = dataSnapshot.child(UsernameEnteredValue).child("username").getValue(String.class);
                        Intent intent= new Intent(getApplicationContext(),StoreHome.class);
                        intent.putExtra("username",usernameFromDB);
                        startActivity(intent);
                    }
                    else {
                        Password.setError("wrong password");
                        Password.requestFocus();
                    }
                } else{
                    Username.setError("User not Found");
                    Username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void Listner(){

        Login_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean validateName= validateName();
                Boolean validatePassword= validatePassword();

                if(!validateName | !validatePassword){
                    return;
                }
                else{
                    isUser();
                }

            }
        });
    }

    private void intia(){
        Logoimage= findViewById(R.id.LogoImage);
        //Hello= findViewById(R.id.Hello);
        declaration= findViewById(R.id.IamgeText);
        Username= findViewById(R.id.username);
        Password= findViewById(R.id.password);
        Login_in= findViewById(R.id.Login_in);
        //Forgot_password= findViewById(R.id.Forgot_Password);
    }
}