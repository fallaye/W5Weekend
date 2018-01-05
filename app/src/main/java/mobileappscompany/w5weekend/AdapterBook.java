package mobileappscompany.w5weekend;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

/**
 * Created by fallaye on 1/5/18.
 */

public class AdapterBook extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<Book> data= Collections.emptyList();
    Book current;
    int currentPos=0;

    public AdapterBook(Context context, List<Book> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_book, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyHolder myHolder= (MyHolder) holder;
        Book current=data.get(position);
        myHolder.tvTitle.setText("Title: " + current.title);
        myHolder.tvAuthor.setText("Author: " + current.author);
        // load image into imageview using glide
        Glide.with(context).load("http://192.168.1.7/test/images/" + current.image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(myHolder.ivBook);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        ImageView ivBook;
        TextView tvTitle;
        TextView tvAuthor;

        public MyHolder(View itemView) {
            super(itemView);
            ivBook= (ImageView) itemView.findViewById(R.id.ivBook);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
        }

    }


}
