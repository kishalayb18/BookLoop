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
    TextInputEditText inpName, inpEmail,inpPhn, inpPass,inpConPass;
    Button btnSignup;
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
        layConPwd=findViewById(R.id.layout_reenter_pwd);

        inpName=findViewById(R.id.user_name);
        inpEmail=findViewById(R.id.user_mail);
        inpPhn=findViewById(R.id.user_phone);
        inpPass=findViewById(R.id.user_password);
        inpConPass=findViewById(R.id.user_confirm_password);

        btnSignup=findViewById(R.id.btn_signup);

        private void addUser(String email, String password)
        {
            mAuth=FirebaseAuth.getInstance();

            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener()
        }
    }
}