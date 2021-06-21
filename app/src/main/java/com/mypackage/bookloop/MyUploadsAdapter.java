package com.mypackage.bookloop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyUploadsAdapter extends RecyclerView.Adapter<MyUploadsAdapter.MyUploadsHolder> {

    Context mContext;
    private List<BookListModel> bookListFull;

    public MyUploadsAdapter(Context mContext, List<BookListModel> bookList) {
        this.mContext = mContext;
        this.bookListFull = bookList;
    }

    @NonNull
    @Override
    public MyUploadsAdapter.MyUploadsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.single_row_my_uploads,parent,false);
        MyUploadsAdapter.MyUploadsHolder myHolder= new MyUploadsHolder(itemView);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyUploadsHolder holder, int position) {

        BookListModel bookListModel= bookListFull.get(position);
        holder.bookName.setText("Book Name: "+bookListModel.getBookName());


        holder.editMyUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext,"Edit clicked",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext, MyProfileActivity.class);
                mContext.startActivity(intent);
                /**
                 * CODE TO EDIT SHOULD BE ADDED
                 */
            }
        });


        holder.deleteMyUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext,"Delete clicked",Toast.LENGTH_SHORT).show();
                /**
                 * CODE TO DELETE SHOULD BE ADDED
                 */
            }
        });

//        holder.itemView.setOnClickListener(v -> {
//            Intent intent=new Intent(mContext, BookDetails.class);
//            intent.putExtra(ConstantKeys.KEY_AUTHOR_NAME, bookListModel.getAuthorName());
//            intent.putExtra(ConstantKeys.KEY_BOOK_DESCRIPTION, bookListModel.getBookDescription());
//            intent.putExtra(ConstantKeys.KEY_BOOK_NAME, bookListModel.getBookName());
//            intent.putExtra(ConstantKeys.KEY_BOOK_PRICE, bookListModel.getBookPrice());
//            intent.putExtra(ConstantKeys.KEY_PUBLISHER_NAME, bookListModel.getPublisherName());
//            intent.putExtra(ConstantKeys.KEY_SELLER_NAME, bookListModel.getSellerName());
//            intent.putExtra(ConstantKeys.KEY_SELLER_PHONE, bookListModel.getSellerPhone());
//            intent.putExtra(ConstantKeys.KEY_SEM, bookListModel.getSem());
//
//            mContext.startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return bookListFull.size();
    }

    public class MyUploadsHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView bookName, editMyUploads, deleteMyUploads;


        //NEED TO ADD DELETE AND EDIT ICON
        public MyUploadsHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.ui_card_my_uploads);
            bookName=itemView.findViewById(R.id.ui_book_name_mu);
            editMyUploads=itemView.findViewById(R.id.edit_myuploads);
            deleteMyUploads=itemView.findViewById(R.id.delete_myuploads);
        }
    }

}
