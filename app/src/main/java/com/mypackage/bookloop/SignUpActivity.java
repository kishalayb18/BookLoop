package com.mypackage.bookloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    TextView txtSignUpHeading, txtLogin;
    TextInputLayout layName, layEmail, layPwd, layConPwd, layPhn;
    TextInputEditText inpName, inpEmail,inpPhn, inpPass,inpConPass;
    Button btnSignUp;
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

        btnSignUp=findViewById(R.id.btn_signup);

//        txtLogin.setOnClickListener(v -> {
//            Intent r=new Intent(SignUpActivity.this, Login_Activity.class);
//            startActivity(r);
//        });

        btnSignUp.setOnClickListener(v -> {
            String name=inpName.getText().toString();
            String email=inpEmail.getText().toString().trim();
            String phn=inpPhn.getText().toString();
            String pass=inpPass.getText().toString().trim();
            addUser(email, pass, name, phn);
            Toast.makeText(this, "Signing up",Toast.LENGTH_SHORT).show();
        });

    }

    private void addUser(String email, String password, String name,String phn){
        mAuth=FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        FirebaseUser user=mAuth.getCurrentUser();
                        String uid=user.getUid();
                        user.sendEmailVerification();
                        addProfile(uid, name, email, phn);
                        Toast.makeText(this, "Success! Verification email sent.",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"This email is already used",Toast.LENGTH_SHORT).show();
                        mAuth.getCurrentUser().delete();
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(this,"Failed Due To "+e.getMessage(),Toast.LENGTH_SHORT).show()
                );
    }

    private void addProfile(String uid, String name, String email, String phn){
        reference= FirebaseDatabase.getInstance().getReference("BLUserAccount");
        Map<String , String> userJson=new HashMap<>();
        userJson.put("u_name", name);
        userJson.put("u_email", email);
        userJson.put("u_phone", phn);

        reference.child(uid).setValue(userJson)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(this,"Registration Successful",Toast.LENGTH_LONG).show();

//                        txtLogin.setOnClickListener(v -> {
//                            Intent r=new Intent(SignUpActivity.this, Login_Activity.class);
//                            startActivity(r);
//                        });
                    }
                    else {
                        Toast.makeText(this,"Registration Failed",Toast.LENGTH_SHORT).show();
                        mAuth.getCurrentUser().delete(); //TO DELETE THE CURRENT USER ACCOUNT FROM AUTHENTICATION
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this,"Failed Due To "+e.getMessage(),Toast.LENGTH_SHORT).show()
                );
    }
}