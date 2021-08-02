package com.example.joya;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;


public class aFragment extends Fragment {

    RecyclerView courses;
    RecyclerView subject1;
    RecyclerView subject2;

    TextView viewAll1, viewAll2;

    String currentUser;
    String userFromFireBase;
    FirebaseUser UserName;
    DatabaseReference reference;




    public aFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a, container, false);





        viewAll1 = view.findViewById(R.id.tvViewAll1);
        viewAll2 = view.findViewById(R.id.tvViewAll2);

        UserName = FirebaseAuth.getInstance().getCurrentUser();
        userFromFireBase = UserName.getPhoneNumber();




        reference = FirebaseDatabase.getInstance().getReference("Course1User");

        reference = FirebaseDatabase.getInstance().getReference("Course1User");
        reference.child(userFromFireBase).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {

                DataSnapshot dataSnapshot = task.getResult();
                currentUser = String.valueOf(dataSnapshot.child("user").getValue());



                if ( userFromFireBase.equals(currentUser)) {

                    viewAll1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getContext(), allVideos.class));




                        }
                    });

                    viewAll2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getContext(), allVideos.class));




                        }
                    });

                }else {

                    viewAll1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getContext(), CourseEnroll.class));
                        }
                    });

                    viewAll2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getContext(), CourseEnroll.class));
                        }
                    });

                }


            }
        });


        courses = view.findViewById(R.id.rvCourses);
        courses.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        helperCourses[] HelperCourses = new helperCourses[]{
                new helperCourses("Course 1", "xyz", R.drawable.a),
                new helperCourses("Course 2", "xyz", R.drawable.b),
                new helperCourses("Course 3", "xyz", R.drawable.c),
                new helperCourses("Course 4", "xyz", R.drawable.d)

        };

        courseAdapter myCourseAdapter = new courseAdapter(HelperCourses, getContext());
        courses.setAdapter(myCourseAdapter);


        subject1 = view.findViewById(R.id.rvSubject1);
        subject1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        helperSubject1[] helperSubject = new helperSubject1[]{
                new helperSubject1("Course 1", "xyz", R.drawable.a),
                new helperSubject1("Course 2", "xyz", R.drawable.b),
                new helperSubject1("Course 3", "xyz", R.drawable.c),
                new helperSubject1("Course 4", "xyz", R.drawable.d)

        };

        subject1Adapter mySubject1Adapter = new subject1Adapter(helperSubject, getContext());
        subject1.setAdapter(mySubject1Adapter);


        subject2 = view.findViewById(R.id.rvSubject2);
        subject2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        helperSubject1[] helperSubject2 = new helperSubject1[]{
                new helperSubject1("Course a", "xyz", R.drawable.a),
                new helperSubject1("Course b", "xyz", R.drawable.b),
                new helperSubject1("Course c", "xyz", R.drawable.c),
                new helperSubject1("Course d", "xyz", R.drawable.d)

        };

        subject1Adapter mySubject1Adapter2 = new subject1Adapter(helperSubject2, getContext());
        subject2.setAdapter(mySubject1Adapter2);


        return view;
    }
}