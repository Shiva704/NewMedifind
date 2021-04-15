package com.example.newmedifind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    ImageView Logoimage;
    TextView Slogan;
    TextInputLayout Username, Phonenumber,Email,Password,Confirmpassword;
    Button Loginback;

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
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void intia(){
      Logoimage=findViewById(R.id.LogoImage);
      Slogan=findViewById(R.id.Slogon);
     Username=findViewById(R.id.username);
      Phonenumber=findViewById(R.id.Phonenumber);
      Email=findViewById(R.id.email);
      Password=findViewById(R.id.password);
      Confirmpassword=findViewById(R.id.confirmpassword);
      Loginback=findViewById(R.id.Loginback);
    }
}