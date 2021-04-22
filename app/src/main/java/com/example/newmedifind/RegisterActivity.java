package com.example.newmedifind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    ImageView Logoimage;
    TextView Slogan;
    TextInputLayout Username, Phonenumber,Email,Password,Confirmpassword;
    Button Loginback;
    FirebaseDatabase rootnode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        intia();
    }
    private void Listners(){

        Loginback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootnode=FirebaseDatabase.getInstance();
                reference=rootnode.getReference("Coustmers");


                String username= Username.getEditText().getText().toString();
                String phonenumber= Phonenumber.getEditText().getText().toString();
                String email= Email.getEditText().getText().toString();
                String password= Password.getEditText().getText().toString();
                String confirmpassword= Confirmpassword.getEditText().getText().toString();

                UserHelperClass helperClass= new UserHelperClass( username,phonenumber,email,password,confirmpassword);

                reference.child(username).setValue(helperClass);
            }
        });


    }
    private void intia(){
      Logoimage=findViewById(R.id.LogoImage);
      Slogan=findViewById(R.id.Slogon);
     Username=findViewById(R.id.username);
      Phonenumber=findViewById(R.id.phonenumber);
      Email=findViewById(R.id.email);
      Password=findViewById(R.id.password);
      Confirmpassword=findViewById(R.id.confirmpassword);
      Loginback=findViewById(R.id.Loginback);
    }
}