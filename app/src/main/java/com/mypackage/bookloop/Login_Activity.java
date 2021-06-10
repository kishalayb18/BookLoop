package com.mypackage.bookloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;

public class Login_Activity extends AppCompatActivity {
    
    TextView signUp;
    TextInputLayout layEmail, layPwd;
    TextInputEditText inpEmail,inpPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        layEmail=findViewById(R.id.layout_email);
        layPwd=findViewById(R.id.edit_password);
        inpEmail=findViewById(R.id.user_mail);
        inpPass=findViewById(R.id.user_password);
        btnLogin=findViewById(R.id.login_btn);
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