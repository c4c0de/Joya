package com.example.joya;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class aFragment extends Fragment {

    RecyclerView courses;
    RecyclerView subject1;
    RecyclerView subject2;


    public aFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a, container, false);


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