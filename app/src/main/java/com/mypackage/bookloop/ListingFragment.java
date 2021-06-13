package com.mypackage.bookloop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ListingFragment extends Fragment {

    private ListingFragment listingFragment;
    private RecyclerView recyclerView_list;

    private List<Contacts> dataSource =new ArrayList<>(); //declaring a list
    Contacts contacts=new Contacts();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_listing, container, false);
        recyclerView_list=root.findViewById(R.id.recycler_list);

        addDatatoList();

        /*Code to set the arrangement of listing items
        * using linear format*/

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false); //defining layout of linear manager
        recyclerView_list.setLayoutManager(layoutManager); //allow to set layout for recycler view


        /* Establishing link between view and adapter*/

        RVAdapter adapter=new RVAdapter(getContext(),dataSource);   //creating object of adapter
        //code to use the adapter for establishing link
        recyclerView_list.setAdapter(adapter);



        return root;
    }

    private void addDatatoList() {
        contacts=new Contacts();
        contacts.setName("Rohit");
        dataSource.add(contacts); //adding to list

        contacts=new Contacts();
        contacts.setName("Bunty");
        dataSource.add(contacts); //adding to list

        contacts=new Contacts();
        contacts.setName("Swapnil");
        dataSource.add(contacts); //adding to list

        Contacts contacts=new Contacts();
        contacts.setName("Shiwangi");
        dataSource.add(contacts); //adding to list



    }
}