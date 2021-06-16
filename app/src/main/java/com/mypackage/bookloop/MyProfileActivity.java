package com.mypackage.bookloop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
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
    //String pwd;
    TextInputEditText edit_name;
    TextInputEditText edit_email;
    //TextInputEditText edit_pwd;
    TextInputEditText edit_age;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        session=new LocalSession(MyProfileActivity.this);
        edit_age=findViewById(R.id.inp_age);
        //edit_pwd=findViewById(R.id.inp_pass);
        edit_email=findViewById(R.id.inp_email);
        edit_name=findViewById(R.id.inp_name);
        btnUpdate=findViewById(R.id.btn_update);
        resetPass = findViewById(R.id.resetPassword);
        uid=session.getInfo(ConstantKeys.KEY_UID);
        reference = FirebaseDatabase.getInstance().getReference("BLUserAccount");
        user=FirebaseAuth.getInstance().getCurrentUser();

        getAllInfo();

        btnUpdate.setOnClickListener(v -> {
                updateProfile();
        });

        resetPass.setOnClickListener(v -> {
            resetPassword();
        });

    }

    private void updateProfile() {

        Map<String , Object> userJson=new HashMap<>();
        name=edit_name.getText().toString();
        age=edit_age.getText().toString();
        //pwd=edit_pwd.getText().toString();
        userJson.put(ConstantKeys.KEY_NAME, name);
        userJson.put(ConstantKeys.KEY_PHONE, age);
        //userJson.put("u_pwd", pwd);

        reference.child(uid).updateChildren(userJson, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error==null){
                    Toast.makeText(MyProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MyProfileActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getAllInfo() {
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

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyProfileActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    /*Reset Password*/

    private  void resetPassword(){
        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetPassword = new EditText(v.getContext());

                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter new password greater than 6 characters.");
                passwordResetDialog.setView(resetPassword);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newPassword = resetPassword.getText().toString();
                        user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MyProfileActivity.this, "Password Reset Successfully.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MyProfileActivity.this, "Password Reset Failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close
                    }
                });
                passwordResetDialog.create().show();
            }
        });

    }


}


