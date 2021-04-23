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
        Listners();
    }
    private Boolean validateName() {
        String val= Username.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if(val.isEmpty()){
            Username.setError("Field cannot be Empty");
            return false;
        }
        else if(val.length()>=15){
            Username.setError("Username too Long");
            return false;
        }
        else if(!val.matches(noWhiteSpace)){
            Username.setError("White space not allowed");
            return false;
        }
        else{
            Username.setError(null);
            Username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhonenumber() {
        String val=Phonenumber.getEditText().getText().toString();

        if(val.isEmpty()){
            Phonenumber.setError("Field cannot be Empty");
            return false;
        }
        else{
            Phonenumber.setError(null);
            Phonenumber.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail(){
        String val=Email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            Email.setError("Field cannot be Empty");
            return false;
        }
        else if(!val.matches(emailPattern)){
            Email.setError("Inavlid Email type");
            return false;
        }
        else{
            Email.setError(null);
            Email.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword(){
        String val=Password.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";


        if(val.isEmpty()){
            Password.setError("Field cannot be Empty");
            return false;
        }
        else if (!val.matches(passwordVal)) {
            Password.setError("Password is too weak");
            return false;
        }
        else{
            Password.setError(null);
            Password.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateConfirmPassword(){
        String val=Confirmpassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";


        if(val.isEmpty()){
            Confirmpassword.setError("Field cannot be Empty");
            return false;
        }
        else if (!val.matches(passwordVal)) {
            Confirmpassword.setError("Password is too weak");
            return false;
        }
        else{
            Confirmpassword.setError(null);
            Confirmpassword.setErrorEnabled(false);
            return true;
        }
    }

    private void Listners(){

        Loginback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootnode=FirebaseDatabase.getInstance();
                reference= rootnode.getReference("Coustmers");

                Boolean validateName = validateName();
                Boolean validatePhonenumber= validatePhonenumber();
                Boolean validateEmail = validateEmail();
                Boolean validatePassword = validatePassword();
                Boolean validateConfirmPassword = validateConfirmPassword();


                if( !validateName | !validatePhonenumber | !validateEmail | !validatePassword | !validateConfirmPassword){
                    return;
                }


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