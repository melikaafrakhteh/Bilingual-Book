package com.afrakhteh.book.ui.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.afrakhteh.book.R;
import com.afrakhteh.book.data.entities.BookModel;
import com.afrakhteh.book.ui.article.MainActivity;
import com.afrakhteh.book.ui.detail.DetailActivity;
import com.afrakhteh.book.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private Context context;
    private List<BookModel> model;

    public BookAdapter(Context context, List<BookModel> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_recycler_layout, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final BookModel bookModel = model.get(position);
        holder.title.setText(bookModel.getTitle());
        holder.desc.setText(bookModel.getDescription());

        Picasso.get().load(bookModel.getImage()).into(holder.image);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookAdapter.this.context, DetailActivity.class);
                i.putExtra(Constants.ID, bookModel.getId());
                i.putExtra(Constants.TITLE, bookModel.getTitle());
                i.putExtra(Constants.DESCRPTION, bookModel.getDescription());
                i.putExtra(Constants.IMAGE, bookModel.getImage());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(i);
            }
        });

    }

    public void setModel(List<BookModel> models) {
        model = models;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout layout;
        private TextView title;
        private TextView desc;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layout);
            title = itemView.findViewById(R.id.item_title);
            desc = itemView.findViewById(R.id.item_description);
            image = itemView.findViewById(R.id.item_image);
        }
    }
}
