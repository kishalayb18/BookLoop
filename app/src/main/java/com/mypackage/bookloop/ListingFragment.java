package com.mypackage.bookloop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ListingFragment extends Fragment {

    private ListingFragment listingFragment;

    BookListModel bookListModel=new BookListModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.fragment_listing, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recycler_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false); //defining layout of linear manager
        recyclerView.setLayoutManager(layoutManager); //allow to set layout for recycler view

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("UploadedBooks");

        List<BookListModel> dataList = new ArrayList<>();

        RVAdapter adapter = new RVAdapter(getContext(), dataList);   //creating object of adapter
        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    BookListModel bookListModel=dataSnapshot.getValue(BookListModel.class);
                    dataList.add(bookListModel);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }

}