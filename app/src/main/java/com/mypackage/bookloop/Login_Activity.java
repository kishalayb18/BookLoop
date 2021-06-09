package com.mypackage.bookloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Login_Activity extends AppCompatActivity {
    
    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUp = findViewById(R.id.Signup);
        signUp.setOnClickListener(v -> {
            Intent r = new Intent(Login_Activity.this, SignupActivity.class);
            startActivity(r);
            Login_Activity.this.finish();
        });
    }


}