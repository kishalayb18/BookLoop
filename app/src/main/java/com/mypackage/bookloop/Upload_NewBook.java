package com.mypackage.bookloop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Upload_NewBook extends AppCompatActivity {
    TextView uploadbook;
    Button btnUpload;
    TextInputLayout bknameInpt, authorInpt, publisherInpt, semInpt, descripInpt, priceInpt;
    TextInputEditText bknameEdit, authorEdit, publisherEdit, semEdit, descripEdit, priceEdit;

    DatabaseReference dataBasereference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__new_book);

        getSupportActionBar().setTitle("UPLOAD NEW BOOK");

        uploadbook=findViewById(R.id.txt_user);
        btnUpload=findViewById(R.id.upload_button);
        bknameInpt=findViewById(R.id.bookname);
        bknameEdit=findViewById(R.id.bookname_edit);
        authorInpt=findViewById(R.id.Author);
        authorEdit=findViewById(R.id.author_edit);
        publisherInpt=findViewById(R.id.publisher);
        publisherEdit=findViewById(R.id.publisher_edit);
        semInpt=findViewById(R.id.classorsem);
        semEdit=findViewById(R.id.classorsem_edit);
        descripInpt=findViewById(R.id.description);
        descripEdit=findViewById(R.id.description_edit);
        priceInpt=findViewById(R.id.Price);
        priceEdit=findViewById(R.id.price_edit);

        //ON CLICKING UPLOAD BUTTON
        btnUpload.setOnClickListener(v -> {
            String bookName=bknameEdit.getText().toString();
            String authorName=authorEdit.getText().toString().trim();
            String publisherName=publisherEdit.getText().toString();
            String sem=semEdit.getText().toString().trim();
            String description=descripEdit.getText().toString().trim();
            String price=priceEdit.getText().toString().trim();
            addBook(bookName, authorName, publisherName, sem,description,price);
            //Toast.makeText(this, "Signing up",Toast.LENGTH_SHORT).show();
        });

    }


    private void addBook(String bookName, String authorName, String publisherName, String sem, String description, String price) {
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        String uid=user.getUid();
        dataBasereference= FirebaseDatabase.getInstance().getReference("BLUserAccount");


    }

    /**
     * adding menu to the app or action bar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //define the menu file
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    /**
     * method to generate event against the menu
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) //allow us to use the id against the menu itself
        {
            case R.id.Logout:
                logout();
                break;

            case R.id.home_menu:
                Intent hm = new Intent(Upload_NewBook.this, MainActivity.class);
                startActivity(hm);//message passing object
                //Toast.makeText(Upload_NewBook.this,"Login successful", Toast.LENGTH_SHORT).show();
                break;

            case R.id.MyProfile:

                Toast.makeText(Upload_NewBook.this,"Login successful", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Search:
                Toast.makeText(Upload_NewBook.this,"Login successful", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        //perform operation for logging out
        /**
         * showing a dialogue to perform a convenient operation to the user
         */

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Upload_NewBook.this);
        alertBuilder.setTitle("Exit message");
        alertBuilder.setMessage("Confirm to exit");
        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //cancel operation
                Toast.makeText(Upload_NewBook.this,"Operation suspended", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }); //set -ve operation

        alertBuilder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Upload_NewBook.this,"Logout successful", Toast.LENGTH_SHORT).show();
                Upload_NewBook.this.finish();

            }
        });// set +ve operation
        alertBuilder.setCancelable(false); //auto cancel suspended
        alertBuilder.show();


    }
}

