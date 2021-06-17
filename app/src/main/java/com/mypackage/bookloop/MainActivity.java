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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    RecyclerView recyclerView;
    DatabaseReference database;
    RVAdapter rvAdapter;
    List<BookListModel> bookListModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("HOME");

        //RecyclerView start

        recyclerView =findViewById(R.id.recycler_list);
        database= FirebaseDatabase.getInstance().getReference("UploadedBooks");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookListModelList= new ArrayList<BookListModel>();
        rvAdapter=new RVAdapter(this,bookListModelList);
        recyclerView.setAdapter(rvAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookListModelList.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    BookListModel bookListModel=dataSnapshot.getValue(BookListModel.class);
                    bookListModelList.add(bookListModel);
                }
                rvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //RecyclerViewEnd
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
                Intent mp = new Intent(MainActivity.this, MyProfileActivity.class);
                //mp.putExtra("Uemail", auth.getCurrentUser().getEmail());
                startActivity(mp);
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




