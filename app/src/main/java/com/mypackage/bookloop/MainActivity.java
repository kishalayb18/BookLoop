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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getSupportActionBar().setTitle("HOME");
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
            case R.id.MyProfile:

                Toast.makeText(MainActivity.this,"Login successful", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Search:
                Toast.makeText(MainActivity.this,"Login successful", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Upload_new_book:
                Intent upl = new Intent(MainActivity.this, Upload_NewBook.class);
                startActivity(upl);//message passing object
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        //perform operation for logging out
        /**
         * showing a dialogue to perform a convenient operation to the user
         */

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Exit message");
        alertBuilder.setMessage("Confirm to exit");
        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //cancel operation
                Toast.makeText(MainActivity.this,"Operation suspended", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }); //set -ve operation

        alertBuilder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"Logout successful", Toast.LENGTH_SHORT).show();
                MainActivity.this.finish();

            }
        });// set +ve operation
        alertBuilder.setCancelable(false); //auto cancel suspended
        alertBuilder.show();


    }
}




