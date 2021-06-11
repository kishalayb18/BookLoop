package com.mypackage.bookloop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ListingFragment extends Fragment {

    private ListingFragment listingFragment;
    private RecyclerView recyclerView_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_listing, container, false);
        recyclerView_list=root.findViewById(R.id.recycler_list);

        /*Code to set the arrangement of listing items
        * using linear format*/

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false); //defining layout of linear manager
        recyclerView_list.setLayoutManager(layoutManager); //allow to set layout for recycler view






        return root;
    }
}