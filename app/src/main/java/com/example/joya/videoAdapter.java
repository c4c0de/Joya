package com.example.joya;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class videoAdapter extends RecyclerView.Adapter<videoAdapter.ViewHolder> {

    helperSubject1[] helperSubject;
    Context context;

    public videoAdapter(helperSubject1[] helperSubject, Context context) {
        this.helperSubject = helperSubject;
        this.context = context;

    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.video_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull videoAdapter.ViewHolder holder, int position) {

        final helperSubject1 data = helperSubject[position];

        holder.textView.setText(data.getItems());
        holder.author.setText(data.getName());
        holder.imageView.setImageResource(data.getImage());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Subject1Details.class);
                intent.putExtra("name", data.getItems());
                intent.putExtra("author", data.getName());
                intent.putExtra("image", data.getImage());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return helperSubject.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView, author;
        ImageView imageView;
        CardView cardView;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tvSubject1Title);
            author = itemView.findViewById(R.id.tvSubject1Author);
            imageView = itemView.findViewById(R.id.ivSubject1Image);
            cardView = itemView.findViewById(R.id.cvtap);


        }
    }


}


