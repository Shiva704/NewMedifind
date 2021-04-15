package com.example.newmedifind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    ImageView Logoimage;
    TextView Hello, declaration;
    TextInputLayout Username, Password;
    Button Login_in , Sign_up, Forgot_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intia();
        Listeners();
    }
    private void Listeners(){

        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void intia(){
        Logoimage= findViewById(R.id.LogoImage);
        Hello= findViewById(R.id.Hello);
       declaration= findViewById(R.id.IamgeText);
        Username= findViewById(R.id.username);
        Password= findViewById(R.id.password);
        Login_in= findViewById(R.id.Login_in);
        Sign_up= findViewById(R.id.Sigh_up);
        Forgot_password= findViewById(R.id.Forgot_Password);
    }
}

