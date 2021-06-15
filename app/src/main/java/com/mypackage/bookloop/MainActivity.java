package com.mypackage.bookloop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth=FirebaseAuth.getInstance();
        getSupportActionBar().setTitle("HOME");
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
            case R.id.MyProfile:
                Intent mp = new Intent(MainActivity.this, UserProfileActivity.class);
                mp.putExtra("email",auth.getCurrentUser().getEmail());
                mp.putExtra("name",auth.getCurrentUser().getDisplayName());
                mp.putExtra("phone",auth.getCurrentUser().getPhoneNumber());
                startActivity(mp);//message passing object
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
        });

        alertBuilder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LocalSession localSession = new LocalSession(MainActivity.this);
                localSession.clearAll();
                Intent r = new Intent(MainActivity.this, Login_Activity.class);
                startActivity(r);//message passing object
                Toast.makeText(MainActivity.this,"Logout successful", Toast.LENGTH_SHORT).show();
                MainActivity.this.finish();
            }
        });
        alertBuilder.setCancelable(false); //auto cancel suspended
        alertBuilder.show();

    }
}




