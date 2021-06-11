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

    TextInputLayout layoutName,layoutMail,layoutPwd,layoutRePwd,layoutPhone;
    TextView loginText;
    public TextInputEditText editName, editMail, editPhone, editPassword, editRePassword;
    public Button btnSubmit;
    public  String name,mail,phone,pwd1,pwd2;
    FirebaseAuth auth;  //FOR FIREBASE AUTHENTICATION
    DatabaseReference ref;  //FOR REAL TIME DATABASE



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        layoutName=findViewById(R.id.layout_user_name);
        layoutMail=findViewById(R.id.layout_email);
        layoutPhone=findViewById(R.id.layout_phone);
        layoutPwd=findViewById(R.id.layout_password);
        layoutRePwd=findViewById(R.id.layout_reenter_pwd);

        editName=findViewById(R.id.user_name);
        editMail=findViewById(R.id.user_mail);
        editPhone=findViewById(R.id.user_phone);
        editPassword=findViewById(R.id.user_password);
        editRePassword=findViewById(R.id.user_confirm_password);
        btnSubmit=findViewById(R.id.btn_signup);
        loginText = findViewById(R.id.Login);

        //navigate to login from sign up
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LOGIC TO NAVIGATE TO SIGN UP SCREEN
                Intent r = new Intent(SignUpActivity.this, Login_Activity.class); //EXPLICIT INTENT
                startActivity(r);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=editName.getText().toString().trim();
                mail=editMail.getText().toString().trim();
                phone=editPhone.getText().toString().trim();
                pwd1=editPassword.getText().toString().trim();
                pwd2=editRePassword.getText().toString().trim();

                layoutName.setError(null);
                layoutMail.setError(null);
                layoutPhone.setError(null);
                layoutPwd.setError(null);
                layoutRePwd.setError(null);

                if(name.isEmpty())
                {
                    layoutName.setError("NAME FIELD CANNOT BE EMPTY");
                }
                else if(mail.isEmpty())
                {
                    layoutMail.setError("EMAIL FIELD CANNOT BE EMPTY");
                }
                else if(phone.isEmpty())
                {
                    layoutPhone.setError("EMAIL FIELD CANNOT BE EMPTY");
                }
                else if(pwd1.isEmpty())
                {
                    layoutPwd.setError("PASSWORD FIELD CANNOT BE EMPTY");
                }
                else if(pwd1.length()<8)
                {
                    layoutPwd.setError("PASSWORD MUST BE ATLEAST OF 8 CHARACTERS");
                }
                else if(pwd2.isEmpty())
                {
                    layoutRePwd.setError("PLEASE CONFIRM YOUR PASSWORD");
                }
                else if(!pwd1.equals(pwd2))
                {
                    layoutRePwd.setError("PASSWORD MISMATCH");
                }
                else
                {
                    //Toast.makeText(SignUpActivity.this,"REGISTRATION SUCCESSFULL\n\nNAME: "+name+"\nEmail: "+mail+"\nPhone: "+phone,Toast.LENGTH_SHORT).show();

                    addUser(mail,pwd1,name,phone);
                    Intent route= new Intent(SignUpActivity.this,Login_Activity.class); //EXPLICIT INTENT
                    startActivity(route);
                }
            }

        });
    }

    public void addUser(String email, String password, String name, String ph)
    {
        //ACESS OUR AUTHENTICATION SERVICE
        auth=FirebaseAuth.getInstance();

        //ADD USER IN AUTHENTICATION
        auth.createUserWithEmailAndPassword(email, password)
                //TO CHECK IF THE DATA HAS ADDED UP SUCCESSFULLY OR NOT
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        //IF & ONLY IF THE EMAIL IS UNIQUE AND FOLLOWS THE PATTERN OF AN EMAIL

                        //THE PROVIDED EMAIL IS EXISTING OR NOT ***VERIFYING THE EMAIL***

                        FirebaseUser user= auth.getCurrentUser();    //we are accessing the current user whose account is just created
                        String uid=user.getUid();   //UNIQUE ID FOR EVERY USER ACCOUNT

                        /**
                         * TO CHECK IF THE EMAIL IS EXISTING OR NOT
                         */
                        user.sendEmailVerification();   //ON VERIFICATION YOU WILL BE ALLOWED TO ACCESS THE APP FURTHER
                        addProfile(uid,name,email,ph);
                        Toast.makeText(this,"Email verification sent",Toast.LENGTH_SHORT).show();


                    }
                    else {
                        Toast.makeText(this,"This email is already used",Toast.LENGTH_SHORT).show();
                    }
                })
                //HELP US TO CHECK ANY FALIURE EXCEPTION TAKING PLACE OR NOT
                .addOnFailureListener(e -> Toast.makeText(this,"Failed Due To "+e.getMessage(),Toast.LENGTH_SHORT).show()
                );
    }

    private void addProfile(String uid, String name, String email, String ph) {
        //  HERE ALL THE DATA RELEVANT TO THE USER WILL STORED IN THE DATABASE
        ref= FirebaseDatabase.getInstance().getReference("UserAccount");

        //USER DETAILS IN MAP FORMAT
        Map<String,String> userJason=new HashMap<>();
        userJason.put("u_name",name);
        userJason.put("u_email",email);
        userJason.put("u_ph",ph);

        //TO ADD THE USER DETAILS
        ref.child(uid).setValue(userJason)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(this,"Registration Successful",Toast.LENGTH_SHORT).show();

                        //ADD CODE TO NAV TO LOGIN SCREEN

                    }
                    else {
                        Toast.makeText(this,"Registration Failed",Toast.LENGTH_SHORT).show();
                        auth.getCurrentUser().delete(); //TO DELETE THE CURRENT USER ACCOUNT FROM AUTHENTICATION
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this,"Failed Due To "+e.getMessage(),Toast.LENGTH_SHORT).show()
                );

    }
}