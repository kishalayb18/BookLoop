package com.mypackage.bookloop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MyProfileActivity extends AppCompatActivity {

    LocalSession session;
    DatabaseReference reference;
    Button btnUpdate, resetPass;
    String uid;
    String name;
    String age;
    String email;
    TextInputLayout layName, layAge;
    //String pwd;
    TextInputEditText edit_name;
    TextInputEditText edit_email;
    //TextInputEditText edit_pwd;
    TextInputEditText edit_age;
    FirebaseAuth fAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        session=new LocalSession(MyProfileActivity.this);
        layName = findViewById(R.id.lay_name);
        layAge = findViewById(R.id.lay_age);
        edit_age=findViewById(R.id.inp_age);
        //edit_pwd=findViewById(R.id.inp_pass);
        edit_email=findViewById(R.id.inp_email);
        edit_name=findViewById(R.id.inp_name);
        btnUpdate=findViewById(R.id.btn_update);
        resetPass = findViewById(R.id.resetPassword);
        uid=session.getInfo(ConstantKeys.KEY_UID);
        reference = FirebaseDatabase.getInstance().getReference("BLUserAccount");
        fAuth=FirebaseAuth.getInstance();
        user=fAuth.getCurrentUser();

        //pass values to myprofile intent to here to view email address

        getAllInfo();

        btnUpdate.setOnClickListener(v -> {
            updateProfile();
        });

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.sendPasswordResetEmail(edit_email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MyProfileActivity.this,"Reset password link sent to your email",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MyProfileActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }

    private void updateProfile() {

        Map<String , Object> userJson=new HashMap<>();
        name = edit_name.getText().toString();
        age = edit_age.getText().toString();
        userJson.put(ConstantKeys.KEY_NAME, name);
        userJson.put(ConstantKeys.KEY_PHONE, age);

        layName.setError(null);
        layAge.setError(null);

        if(name.isEmpty()){
            layName.setError("Name can't be empty");
        }
        else if(age.isEmpty()){
            layAge.setError("Contact number can't be empty");
        }
        else {
            reference.child(uid).updateChildren(userJson, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    if (error == null) {
                        Toast.makeText(MyProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(MyProfileActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void getAllInfo() {
        Intent data = getIntent();
        String UPemail = data.getStringExtra("UPemail");
        reference.getRef().child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, String> user=(Map<String, String>)snapshot.getValue();
                BLUserAccount account = new BLUserAccount(user.get("userName"), user.get("userEmail"), user.get("userAge"));
                name=account.getUserName();
                email=account.getUserEmail();
                age=account.getUserAge();
                edit_name.setText(name);
                edit_age.setText(age);
                edit_email.setText(UPemail);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyProfileActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}


