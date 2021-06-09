package com.mypackage.bookloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Login_Activity extends AppCompatActivity {
    
    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUp = findViewById(R.id.Signup);
        signUp.setOnClickListener(v -> {
            Intent r = new Intent(Login_Activity.this, SignUpActivity.class);
            startActivity(r);
        });

//        @Override
//        public void onClick(View v){
//            switch (v.getId()) {
//                case R.id.Signup:
//                    startActivity(new Intent(this, SignUpActivity.class));
//                    break;
//            }
//        }

    }


}