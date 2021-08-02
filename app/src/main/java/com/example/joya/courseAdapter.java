package com.example.joya;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class courseAdapter extends RecyclerView.Adapter<courseAdapter.ViewHolder> {

    helperCourses[] HelperCourses;
    Context context;

    public courseAdapter(helperCourses[] HelperCourses, Context context) {
        this.HelperCourses = HelperCourses;
        this.context = context;

    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.courses_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull courseAdapter.ViewHolder holder, int position) {

        final helperCourses data = HelperCourses[position];

        holder.textView.setText(data.getItems());
        holder.author.setText(data.getName());
        holder.imageView.setImageResource(data.getImage());


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,coursesDetails.class);
                intent.putExtra("name",data.getItems());
                intent.putExtra("author",data.getName());
                intent.putExtra("image",data.getImage());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return HelperCourses.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView, author;
        ImageView imageView;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tvCourseTitle);
            author = itemView.findViewById(R.id.tvCourseAuthor);
            imageView = itemView.findViewById(R.id.ivCourseImage);


        }
    }


}

//    String[] items , name;
//    int[] image;
//
//    public courseAdapter(String[] items, String[] name, int[] image) {
//        this.items = items;
//        this.name = name;
//        this.image = image;
//    }
//
//    @NonNull
//    @NotNull
//    @Override
//    public courseViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.courses_items, parent, false);
//        return new courseViewHolder(view);
//
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull @NotNull courseAdapter.courseViewHolder holder, int position) {
//
//        holder.textView.setText(items[position]);
//        holder.author.setText(name[position]);
//        holder.imageView.setImageResource((image[position]));
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.length;
//    }
//
//    public class courseViewHolder extends RecyclerView.ViewHolder {
//
//        TextView textView, author;
//        ImageView imageView;
//
//        public courseViewHolder(@NonNull @NotNull View itemView) {
//            super(itemView);
//
//            textView = itemView.findViewById(R.id.tvCourseTitle);
//            author = itemView.findViewById(R.id.tvCourseAuthor);
//            imageView = itemView.findViewById(R.id.ivCourseImage);
//
//
//        }
//    }

