package com.example.joya;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class demoRecycler extends AppCompatActivity {

    RecyclerView recyclerViewCourse1;
    DatabaseReference databaseReference;
    FirebaseDatabase reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_recycler);



        recyclerViewCourse1 = findViewById(R.id.rvCourse1Material);


        recyclerViewCourse1.setLayoutManager(new LinearLayoutManager(getApplication()));


        reference = FirebaseDatabase.getInstance();

        databaseReference = reference.getReference("course1Videos");



    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<helperCourse1VideoUpload> options =
                new FirebaseRecyclerOptions.Builder<helperCourse1VideoUpload>()
                        .setQuery(databaseReference, helperCourse1VideoUpload.class)
                        .build();

        FirebaseRecyclerAdapter<helperCourse1VideoUpload, Course1MaterialViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<helperCourse1VideoUpload, Course1MaterialViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull Course1MaterialViewHolder holder, int position, @NonNull @org.jetbrains.annotations.NotNull helperCourse1VideoUpload model) {


                holder.setPlayer( getApplication(), model.getTitle(), model.getVurl());

            }

            @NonNull
            @org.jetbrains.annotations.NotNull
            @Override
            public Course1MaterialViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course1_single_row, parent, false);
                return new Course1MaterialViewHolder(view);
            }
        };

        firebaseRecyclerAdapter.startListening();
        recyclerViewCourse1.setAdapter(firebaseRecyclerAdapter);

    }
}