package com.mypackage.bookloop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class BookDetails extends AppCompatActivity {

    TextView showBookName, showAuthorName, showPublisherName, showSem, showBookDescription, showBookPrice, showSellerName, showSellerPhone, shiwSellerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        String bookName=getIntent().getStringExtra(ConstantKeys.KEY_BOOK_NAME);
        showBookName=findViewById(R.id.show_book_name);
        showBookName.setText(bookName);

        String authorName=getIntent().getStringExtra(ConstantKeys.KEY_AUTHOR_NAME);
        showAuthorName=findViewById(R.id.show_author_name);
        showAuthorName.setText(authorName);

        String publisherName=getIntent().getStringExtra(ConstantKeys.KEY_PUBLISHER_NAME);
        showPublisherName=findViewById(R.id.show_publisher_name);
        showPublisherName.setText(publisherName);

        String sem=getIntent().getStringExtra(ConstantKeys.KEY_SEM);
        showSem=findViewById(R.id.show_sem);
        showSem.setText(sem);

        String bookDescription=getIntent().getStringExtra(ConstantKeys.KEY_BOOK_DESCRIPTION);
        showBookDescription=findViewById(R.id.show_book_description);
        showBookDescription.setText(bookDescription);

        String bookPrice=getIntent().getStringExtra(ConstantKeys.KEY_BOOK_PRICE);
        showBookPrice=findViewById(R.id.show_book_price);
        showBookPrice.setText(bookPrice);

        String sellerName=getIntent().getStringExtra(ConstantKeys.KEY_SELLER_NAME);
        showSellerName=findViewById(R.id.show_seller_name);
        showSellerName.setText(sellerName);


        String sellerPhone=getIntent().getStringExtra(ConstantKeys.KEY_SELLER_PHONE);
        showSellerPhone=findViewById(R.id.show_seller_phone);
        showSellerPhone.setText(sellerPhone);

        String sellerEmail=getIntent().getStringExtra(ConstantKeys.KEY_SELLER_EMAIL);
        shiwSellerEmail=findViewById(R.id.show_seller_email);
        shiwSellerEmail.setText(sellerEmail);
    }
}