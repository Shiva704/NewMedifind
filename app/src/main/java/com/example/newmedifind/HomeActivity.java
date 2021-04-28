package com.example.newmedifind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class HomeActivity extends AppCompatActivity {

    ImageView Logoimage;
    TextView Greetings,Popular;
    TextInputLayout Search;
    Button Loginback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intia();
        Intent intent = getIntent();
        String UsernameDisplay = "Greetings"+" "+ intent.getStringExtra("username");
        Greetings.setText(UsernameDisplay);

        Greetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,SerachTab.class);
                startActivity(intent);
            }
        });

    }

    private void intia(){
        Greetings=findViewById(R.id.greeting);
        Search=findViewById(R.id.search_bar);
    }
}