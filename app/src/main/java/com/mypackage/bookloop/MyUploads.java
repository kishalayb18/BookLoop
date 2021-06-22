package com.mypackage.bookloop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyUploads extends AppCompatActivity {

    FirebaseUser user;
    RecyclerView recyclerView;
    DatabaseReference database;
    MyUploadsAdapter myUploadsAdapter;
    List<BookListModel> bookListModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_uploads);
        getSupportActionBar().setTitle("MY UPLOADS");

        user=FirebaseAuth.getInstance().getCurrentUser();
        String myEmail= user.getEmail();

        recyclerView =findViewById(R.id.recycler_list_myUploads);
        database= FirebaseDatabase.getInstance().getReference("UploadedBooks");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookListModelList= new ArrayList<BookListModel>();
        myUploadsAdapter=new MyUploadsAdapter(this,bookListModelList);
        recyclerView.setAdapter(myUploadsAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookListModelList.clear();


                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    BookListModel bookListModel=dataSnapshot.getValue(BookListModel.class);
                    if(bookListModel.getSellerEmail().equals(myEmail)){
                        bookListModelList.add(bookListModel);
                    }
                }
                myUploadsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}