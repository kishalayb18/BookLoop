package com.mypackage.bookloop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

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

public class EditBookDetails extends AppCompatActivity {

    private LocalSession session;
    private DatabaseReference reference;
    String  authorName, bookDescription, bookId, bookName, bookPrice, publisherName, bookSellerName, bookSellerPhone, bookSem;
    private Button btnUpdateBook;
    private TextInputLayout layBookName,layAuthorName,layPublisherName,layBookSem,layBookDescription,layBookPrice,layBookSellerName,layBookSellerPhone;
    private TextInputEditText editBookName,editAuthorName,editPublisherName,editBookSem,editBookDescription,editBookPrice,editBookSellerName,editBookSellerPhone;

    private FirebaseAuth fAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book_details);
        session=new LocalSession(EditBookDetails.this);

        reference = FirebaseDatabase.getInstance().getReference("UploadedBooks");
        fAuth=FirebaseAuth.getInstance();
        user=fAuth.getCurrentUser();

        btnUpdateBook=findViewById(R.id.btn_bE_update);

        layBookName = findViewById(R.id.lay_bE_book_name);
        layAuthorName = findViewById(R.id.lay_bE_author_name);
        layPublisherName = findViewById(R.id.lay_bE_publisher_name);
        layBookSem = findViewById(R.id.lay_bE_sem);
        layBookDescription = findViewById(R.id.lay_bE_book_description);
        layBookPrice = findViewById(R.id.lay_bE_book_price);
        layBookSellerName = findViewById(R.id.lay_bE_seller_name);
        layBookSellerPhone = findViewById(R.id.lay_bE_seller_phone);

        editBookName=findViewById(R.id.inp_bE_book_name);
        editAuthorName=findViewById(R.id.inp_author_book_name);
        editPublisherName=findViewById(R.id.inp_bE_publisher_name);
        editBookSem=findViewById(R.id.inp_bE_sem);
        editBookDescription=findViewById(R.id.inp_bE_book_description);
        editBookPrice=findViewById(R.id.inp_bE_book_price);
        editBookSellerName=findViewById(R.id.inp_bE_seller_name);
        editBookSellerPhone=findViewById(R.id.inp_bE_seller_phone);


        authorName = getIntent().getStringExtra(ConstantKeys.KEY_AUTHOR_NAME);
        bookDescription = getIntent().getStringExtra(ConstantKeys.KEY_BOOK_DESCRIPTION);
        bookId = getIntent().getStringExtra(ConstantKeys.KEY_BOOK_ID);
        bookName = getIntent().getStringExtra(ConstantKeys.KEY_BOOK_NAME);
        bookPrice = getIntent().getStringExtra(ConstantKeys.KEY_BOOK_PRICE);
        publisherName = getIntent().getStringExtra(ConstantKeys.KEY_PUBLISHER_NAME);
        bookSellerName = getIntent().getStringExtra(ConstantKeys.KEY_SELLER_NAME);
        bookSellerPhone = getIntent().getStringExtra(ConstantKeys.KEY_SELLER_PHONE);
        bookSem = getIntent().getStringExtra(ConstantKeys.KEY_SEM);

        editBookName.setText(bookName);
        editAuthorName.setText(authorName);
        editPublisherName.setText(publisherName);
        editBookSem.setText(bookSem);
        editBookDescription.setText(bookDescription);
        editBookPrice.setText(bookPrice);
        editBookSellerName.setText(bookSellerName);
        editBookSellerPhone.setText(bookSellerPhone);


        btnUpdateBook.setOnClickListener(v -> {
            Toast.makeText(this,"Edit",Toast.LENGTH_SHORT);
            editBookDetails();
        });

    }

    private void editBookDetails() {

        Map<String , Object> bookJson=new HashMap<>();

        bookName = editBookName.getText().toString();
        authorName = editAuthorName.getText().toString();
        publisherName = editPublisherName.getText().toString();
        bookSem = editBookSem.getText().toString();
        bookDescription = editBookDescription.getText().toString();
        bookPrice = editBookPrice.getText().toString();
        bookSellerName = editBookSellerName.getText().toString();
        bookSellerPhone = editBookSellerPhone.getText().toString();

        bookJson.put(ConstantKeys.KEY_BOOK_NAME, bookName);
        bookJson.put(ConstantKeys.KEY_AUTHOR_NAME, authorName);
        bookJson.put(ConstantKeys.KEY_PUBLISHER_NAME, publisherName);
        bookJson.put(ConstantKeys.KEY_SEM, bookSem);
        bookJson.put(ConstantKeys.KEY_BOOK_DESCRIPTION, bookDescription);
        bookJson.put(ConstantKeys.KEY_BOOK_PRICE, bookPrice);
        bookJson.put(ConstantKeys.KEY_SELLER_NAME, bookSellerName);
        bookJson.put(ConstantKeys.KEY_SELLER_PHONE, bookSellerPhone);

        layBookName.setError(null);
        layAuthorName.setError(null);
        layPublisherName.setError(null);
        layBookSem.setError(null);
        layBookDescription.setError(null);
        layBookPrice.setError(null);
        layBookSellerName.setError(null);
        layBookSellerPhone.setError(null);


        if(bookName.isEmpty()){
            layBookName.setError("Can't be empty");
        }
        else if(authorName.isEmpty()){
            layAuthorName.setError("Can't be empty");
        }
        else if(publisherName.isEmpty()){
            layPublisherName.setError("Can't be empty");
        }
        else if(bookSem.isEmpty()){
            layBookSem.setError("Can't be empty");
        }
        else if(bookDescription.isEmpty()){
            layBookDescription.setError("Can't be empty");
        }
        else if(bookPrice.isEmpty()){
            layBookPrice.setError("Can't be empty");
        }
        else if(bookSellerName.isEmpty()){
            layBookSellerName.setError("Can't be empty");
        }
        else if(bookSellerPhone.isEmpty()){
            layBookSellerPhone.setError("Can't be empty");
        }
        else {
            reference.child(bookId).updateChildren(bookJson, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    if (error == null) {
                        Toast.makeText(EditBookDetails.this, "Updated", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(EditBookDetails.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}