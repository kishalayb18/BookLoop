package com.mypackage.bookloop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.UI> implements Filterable {

    Context mContext;
    private List<BookListModel> bookListFull;
    private List<BookListModel> bookListSearched;

    public RVAdapter(Context mContext, List<BookListModel> bookList) {
        this.mContext = mContext;
        this.bookListFull = bookList;
        this.bookListSearched=new ArrayList<>(bookList);
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

        BookListModel bookListModel= bookListFull.get(position);
        holder.bookName.setText("Book Name: "+bookListModel.getBookName());
        holder.sellerName.setText("Seller Name: "+bookListModel.getSellerName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(mContext, BookDetails.class);
            intent.putExtra(ConstantKeys.KEY_AUTHOR_NAME, bookListModel.getAuthorName());
            intent.putExtra(ConstantKeys.KEY_BOOK_DESCRIPTION, bookListModel.getBookDescription());
            intent.putExtra(ConstantKeys.KEY_BOOK_NAME, bookListModel.getBookName());
            intent.putExtra(ConstantKeys.KEY_BOOK_PRICE, bookListModel.getBookPrice());
            intent.putExtra(ConstantKeys.KEY_PUBLISHER_NAME, bookListModel.getPublisherName());
            intent.putExtra(ConstantKeys.SELLER_NAME, bookListModel.getSellerName());
            intent.putExtra(ConstantKeys.SELLER_PHONE, bookListModel.getSellerPhone());
            intent.putExtra(ConstantKeys.KEY_SEM, bookListModel.getSem());

            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return bookListFull.size();
    }

    @Override
    public Filter getFilter() {
        return bookFilter;
    }

    private final Filter bookFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<BookListModel> filteredOutBookList= new ArrayList<>();

            if(constraint==null||constraint.length()==0)
            {
                filteredOutBookList.addAll(bookListFull);
            }
            else
            {
                String searchKey=constraint.toString().toLowerCase().trim();

                for(BookListModel book:bookListFull)
                {
                    if(book.getBookName().toLowerCase().contains(searchKey))
                    {
                        filteredOutBookList.add(book);
                    }
                }
            }

            FilterResults results= new FilterResults();
            results.values=filteredOutBookList;
            results.count=filteredOutBookList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            bookListSearched.clear();
            bookListSearched.addAll((ArrayList)results.values);
            notifyDataSetChanged();

        }
    };


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