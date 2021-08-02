package com.example.joya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CourseEnroll extends AppCompatActivity {

    Spinner spinner;

    Button button1, button2;

    FirebaseDatabase rootnood;
    DatabaseReference reference;


    FirebaseUser UserName;

    String userNumber;

    static final String[] paths = {"Course 1", "Course 2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_enroll);

        button1 = findViewById(R.id.joinBtn1);
        button2 = findViewById(R.id.joinBtn2);

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Course 1")) {

                    button1.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.GONE);

                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            updateCourseUser1();

                            startActivity(new Intent(CourseEnroll.this,allVideos.class));
                        }
                    });


                } else if (selectedItem.equals("Course 2")) {

                    button1.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.GONE);

                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            updateCourseUser1();

                            startActivity(new Intent(CourseEnroll.this,allVideos.class));
                        }
                    });

                }
            } // to close the onItemSelected


            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
    }

    private void updateCourseUser1() {


        UserName = FirebaseAuth.getInstance().getCurrentUser();
        String userFromFireBase = UserName.getPhoneNumber();

        rootnood = FirebaseDatabase.getInstance();
        reference = rootnood.getReference("Course1User");


        helperCourseUser helperClass1 = new helperCourseUser(userFromFireBase);

        reference.child(userFromFireBase).setValue(helperClass1);

        Toast.makeText(CourseEnroll.this, "UPDATED", Toast.LENGTH_SHORT).show();


    }

    private void updateCourseUser2() {


        UserName = FirebaseAuth.getInstance().getCurrentUser();
        String userFromFireBase = UserName.getPhoneNumber();

        rootnood = FirebaseDatabase.getInstance();
        reference = rootnood.getReference("Course2User");


        helperCourseUser helperClass1 = new helperCourseUser(userFromFireBase);

        reference.setValue(helperClass1);

        Toast.makeText(CourseEnroll.this, "UPDATED", Toast.LENGTH_SHORT).show();


    }
}