package com.mypackage.bookloop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class BookDetails extends AppCompatActivity {

    TextView showBookName, showAuthorName, showPublisherName, showSem, showBookDescription, showBookPrice, showSellerName, showSellerPhone, showSellerEmail;
    FirebaseAuth auth;

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
        showSellerEmail=findViewById(R.id.show_seller_email);
        showSellerEmail.setText(sellerEmail);
    }

    //adding menu to the app or action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //define the menu file
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //method to generate event against the menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) //allow us to use the id against the menu itself
        {

            case R.id.Logout:
                logout();
                break;
            case  R.id.home_menu:
                Intent hm = new Intent(BookDetails.this, MainActivity.class);
                startActivity(hm);
                break;
            case R.id.MyProfile:
                Intent mp = new Intent(BookDetails.this, MyProfileActivity.class);
                startActivity(mp);
                break;
            case R.id.MyUploads:
                Toast.makeText(BookDetails.this,"My Uploads", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Upload_new_book:
                Intent upl = new Intent(BookDetails.this, Upload_NewBook.class);
                startActivity(upl);//message passing object
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(BookDetails.this);
        alertBuilder.setTitle("Exit message");
        alertBuilder.setMessage("Confirm to exit");

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //cancel operation
                Toast.makeText(BookDetails.this,"Operation suspended", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        alertBuilder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LocalSession localSession = new LocalSession(BookDetails.this);
                localSession.clearAll();
                Intent r = new Intent(BookDetails.this, Login_Activity.class);
                startActivity(r);//message passing object
                Toast.makeText(BookDetails.this,"Logout successful", Toast.LENGTH_SHORT).show();
                BookDetails.this.finish();
            }
        });
        alertBuilder.setCancelable(false); //auto cancel suspended
        alertBuilder.show();
    }
}