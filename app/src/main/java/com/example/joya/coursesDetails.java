package com.example.joya;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class coursesDetails extends AppCompatActivity {

    ImageView imageView;
    TextView textView1 ,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_details);

        textView1 = findViewById(R.id.tvCourseName);
        textView2 = findViewById(R.id.tvAuthorName);

        imageView = findViewById(R.id.ivCourseImageLarge);


        imageView.setImageResource(getIntent().getIntExtra("image",0));

        textView1.setText(getIntent().getStringExtra("name"));
        textView2.setText(getIntent().getStringExtra("author"));

    }
}