package com.mypackage.bookloop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class SignupActivity extends AppCompatActivity {

    TextView txtSignUpHeading;
    TextInputLayout layName, layEmail, layPwd, layConPwd, layPhn;
    TextInputEditText inpName, inpEmail, inpPass, inpAge;
    Button btnRegister;
    DatabaseReference reference;
    //LocalSession session;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtSignUpHeading=findViewById(R.id.signUp_heading);
        layName=findViewById(R.id.layout_user_name);
        layEmail=findViewById(R.id.layout_email);
        layPhn=findViewById(R.id.layout_phone);
        layPwd=findViewById(R.id.edit_password);
        layConPwd=findViewById(R.id.confirm_password);

        inpName=findViewById(R.id.user_name);
    }
}