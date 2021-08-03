package com.example.joya;

import android.content.Intent;
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
                new helperSubject1("Video 1", "xyz", R.raw.video2),
                new helperSubject1("Video 2", "xyz", R.raw.video3),
                new helperSubject1("Video 3", "xyz", R.raw.video),
                new helperSubject1("Video 4", "xyz", R.raw.video3),
                new helperSubject1("Video 5", "xyz", R.raw.video3),
                new helperSubject1("Video 6", "xyz", R.raw.video),
                new helperSubject1("Video 7", "xyz", R.raw.video3),
                new helperSubject1("Video 8", "xyz", R.raw.video2),
                new helperSubject1("Video 9", "xyz", R.raw.video3),
                new helperSubject1("Video a", "xyz", R.raw.video),
                new helperSubject1("Video b", "xyz", R.raw.video2),
                new helperSubject1("Video c", "xyz", R.raw.video3),
                new helperSubject1("Video d", "xyz", R.raw.video),
                new helperSubject1("Video e", "xyz", R.raw.video2)

        };

        videoAdapter videoAdapter1 = new videoAdapter(helperSubject3, getApplicationContext());
        recyclerView.setAdapter(videoAdapter1);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        startActivity(new Intent(allVideos.this,bottomDrawer.class));
    }
}