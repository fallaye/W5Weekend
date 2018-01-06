package mobileappscompany.w5weekend;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by fallaye on 1/5/18.
 */

public class AdapterBook extends RecyclerView.Adapter<AdapterBook.MyViewHolder> {

    private Context context;
    List<Book> data;

    public AdapterBook(Context context, List<Book> data){
        this.context=context;
        this.data=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
        .inflate(R.layout.container_book, parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Book currentBook =data.get(position);
        holder.tvTitle.setText("Title: " + currentBook.title);
        holder.tvAuthor.setText("Author: " + currentBook.author);
        //holder.ivBook.setText("Image: " + current.imageURL);
        // load image into imageview using glide
        /*Glide.with(context).load("http://192.168.1.7/test/images/" + current.image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(myHolder.ivBook);*/
        //using Picasso
        Picasso.with(context).load(currentBook.getImageURL())
                .into(holder.ivBook);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvAuthor;
        ImageView ivBook;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
            ivBook= (ImageView) itemView.findViewById(R.id.ivBook);
        }

    }


}
