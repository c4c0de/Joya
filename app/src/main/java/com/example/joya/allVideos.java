package com.example.joya;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class allVideos extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_videos);


        recyclerView = findViewById(R.id.rvAllVideo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        helperSubject1[] helperSubject3= new helperSubject1[]{
                new helperSubject1("Course 1", "xyz", R.drawable.a),
                new helperSubject1("Course 2", "xyz", R.drawable.b),
                new helperSubject1("Course 3", "xyz", R.drawable.c),
                new helperSubject1("Course 4", "xyz", R.drawable.d),
                new helperSubject1("Course 1", "xyz", R.drawable.a),
                new helperSubject1("Course 2", "xyz", R.drawable.b),
                new helperSubject1("Course 3", "xyz", R.drawable.c),
                new helperSubject1("Course 1", "xyz", R.drawable.a),
                new helperSubject1("Course 2", "xyz", R.drawable.b),
                new helperSubject1("Course 3", "xyz", R.drawable.c),
                new helperSubject1("Course 4", "xyz", R.drawable.d),
                new helperSubject1("Course 1", "xyz", R.drawable.a),
                new helperSubject1("Course 2", "xyz", R.drawable.b),
                new helperSubject1("Course 3", "xyz", R.drawable.c)

        };

        videoAdapter videoAdapter1 = new videoAdapter(helperSubject3, getApplicationContext());
        recyclerView.setAdapter(videoAdapter1);


    }
}