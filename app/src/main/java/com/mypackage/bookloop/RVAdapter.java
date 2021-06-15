package com.mypackage.bookloop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.UI> {

    Context mContext;
    private List<BookListModel> bookList;

    public RVAdapter(Context mContext, List<BookListModel> bookList) {
        this.mContext = mContext;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public RVAdapter.UI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(mContext); //
        View itemView = inflater.inflate(R.layout.recycler_item_ui,parent,false);
        UI ui=new UI(itemView);
        return ui;
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.UI holder, int position) {

        BookListModel bookListModel= bookList.get(position);
        holder.bookName.setText(bookListModel.getBookName());
        holder.sellerName.setText(bookListModel.getSellerName());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class UI extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView bookName;
        private TextView sellerName;

        public UI(@NonNull View itemView) {
            super(itemView);

            cardView=itemView.findViewById(R.id.ui_card);
            bookName=itemView.findViewById(R.id.ui_book_name);
            sellerName=itemView.findViewById(R.id.ui_seller_details);

        }
    }
}