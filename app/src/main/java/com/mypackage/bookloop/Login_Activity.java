package com.mypackage.bookloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener{
    TextView Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Signup = (TextView) findViewById(R.id.Signup);
        Signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Signup:
                startActivity(new Intent(this,SignupActivity.class));
                break;
        }
    }
}