package com.mypackage.bookloop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

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
    private Menu menu;

    BookListModel bookListModel=new BookListModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.fragment_listing, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_list);
        //recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("UploadedBooks");

        List<BookListModel> dataList = new ArrayList<>();

        RVAdapter adapter = new RVAdapter(getContext(), dataList);
        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    BookListModel bookListModel=dataSnapshot.getValue(BookListModel.class);
                    dataList.add(bookListModel);
                }
                Log.d("Success", "onDataChange: ");
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.search_item,menu);
        this.menu=menu;
        MenuItem menuItem=menu.findItem(R.id.search_action);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) this);

        super.onCreateOptionsMenu(menu, inflater);
    }
    /*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.grid_item, menu);
    this.menu = menu;
    Log.d("Menu created", "grid");
    final MenuItem menuItem = menu.findItem(R.id.search);

    SearchView searchView = (SearchView)MenuItemCompat.getActionView(menuItem);
    searchView.setOnQueryTextListener(this);

    super.onCreateOptionsMenu(menu, inflater);
    }
    * */
}