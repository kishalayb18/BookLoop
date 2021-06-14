package com.mypackage.bookloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Myprofile extends AppCompatActivity {

    TextView btd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);


        btd = findViewById(R.id.btd);

        btd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myProfile= new Intent(Myprofile.this,MainActivity.class); //EXPLICIT INTENT
                startActivity(myProfile);
            }
        });
    }
}