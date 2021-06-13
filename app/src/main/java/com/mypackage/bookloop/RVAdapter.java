package com.mypackage.bookloop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.UI> {

    Context mContext;
    private List<Contacts> contactsList;

    public RVAdapter(Context mContext, List<Contacts> contactsList) {
        this.mContext = mContext;
        this.contactsList = contactsList;
    }



    @NonNull
    @Override
    public RVAdapter.UI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //creating ui that need to be used for every item of recycler view

        LayoutInflater inflater= LayoutInflater.from(mContext); //
        View itemView=inflater.inflate(R.layout.recycler_item_ui,parent,false);
        UI ui=new UI(itemView);
        return ui;

    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.UI holder, int position) {

        //code to display the respective details
        Contacts contact= contactsList.get(position);
        holder.uiText.setText(contact.getName());


    }

    @Override
    public int getItemCount() {
        //number of items to display
        return contactsList.size();

    }

    public class UI extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView uiText;

        public UI(@NonNull View itemView) {
            super(itemView);

            //mapping
            cardView=itemView.findViewById(R.id.ui_card);
            uiText=itemView.findViewById(R.id.ui_seller_details);



        }
    }
}
