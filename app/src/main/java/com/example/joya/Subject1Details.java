package com.example.joya;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Subject1Details extends AppCompatActivity {

    ImageView imageView;
    TextView textView1 ,textView2;
    VideoView videoView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject1_details);

        textView1 = findViewById(R.id.tvCourseName);
        textView2 = findViewById(R.id.tvAuthorName);

        imageView = findViewById(R.id.ivCourseImageLarge);
        videoView = findViewById(R.id.videoView);

        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+getIntent().getIntExtra("image",0));
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);


//        imageView.setImageResource(getIntent().getIntExtra("image",0));

        textView1.setText(getIntent().getStringExtra("name"));
        textView2.setText(getIntent().getStringExtra("author"));


    }
}