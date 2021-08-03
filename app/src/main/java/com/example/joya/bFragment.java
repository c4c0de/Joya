package com.example.joya;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;


public class bFragment extends Fragment {


    Spinner spinnerCourse, spinnerScheduledClass, spinnerCourseUpdate, spinnerCourseAdd, spinnerCourseMaterial;
    static final String[] paths = {"........", "Course 1", "Course 2"};


    TextView liveClass, className, scheduledClass, TVcourse1Date;

    TextView course1MorningClassName, course1EveningClassName, course2MorningClassName, course2EveningClassName;

    EditText course1dateOfPeriod, course1morningClass, course1eveningClass, course2dateOfPeriod, course2morningClass, course2eveningClass;

    Button onGoingClassCourse1, onGoingClassCourse2, updateCourse1Schedule, updateCourse2Schedule, addCourse1, addCourse2;

    CardView course1Date, course1MorningClass, course1EveningClass, course2Date, course2MorningClass, course2EveningClass, CVCourse1ScheduledClass, CVCourse2ScheduledClass;

    CardView tutor1, tutor2;

    RecyclerView recyclerViewCourse1, recyclerViewCourse2;

    FirebaseDatabase rootnood1, rootnood2;
    DatabaseReference reference1;
    DatabaseReference reference2;
    DatabaseReference referenceCourse1;
    DatabaseReference referenceCourse2;
    FirebaseDatabase reference;

    String currentUser;
    String userFromFireBase;
    FirebaseUser UserName;
    DatabaseReference referenceA;


    public bFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewB = inflater.inflate(R.layout.fragment_b, container, false);


        URL serverURL;

        try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions =
                    new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverURL)
                            .setWelcomePageEnabled(false)
                            .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        liveClass = viewB.findViewById(R.id.tvLiveClass);
        className = viewB.findViewById(R.id.tvOnGoingClass);
        scheduledClass = viewB.findViewById(R.id.tvScheduledClass);
        TVcourse1Date = viewB.findViewById(R.id.tvCourse1Date);
        course1MorningClassName = viewB.findViewById(R.id.tvCourse1MorningClassName);
        course1EveningClassName = viewB.findViewById(R.id.tvCourse1EveningClassName);
        course2MorningClassName = viewB.findViewById(R.id.tvCourse2MorningClassName);
        course2EveningClassName = viewB.findViewById(R.id.tvCourse2EveningClassName);


        onGoingClassCourse1 = viewB.findViewById(R.id.btOnGoingClassCourse1);
        onGoingClassCourse2 = viewB.findViewById(R.id.btOnGoingClassCourse2);
        updateCourse1Schedule = viewB.findViewById(R.id.btCourse1UpdateSchedule);
        updateCourse2Schedule = viewB.findViewById(R.id.btCourse2UpdateSchedule);
        addCourse1 = viewB.findViewById(R.id.btAddCourse1);
        addCourse2 = viewB.findViewById(R.id.btAddCourse2);


        spinnerCourse = viewB.findViewById(R.id.spinnerCourse);
        spinnerCourseUpdate = viewB.findViewById(R.id.spinnerCourseUpdate);
        spinnerCourseAdd = viewB.findViewById(R.id.spCourseAdd);
        spinnerCourseMaterial = viewB.findViewById(R.id.spStudyMaterial);

        course1dateOfPeriod = viewB.findViewById(R.id.etCourse1Date);
        course1morningClass = viewB.findViewById(R.id.etCourse1MorningClass);
        course1eveningClass = viewB.findViewById(R.id.etCourse1EveningClass);

        course2dateOfPeriod = viewB.findViewById(R.id.etCourse2Date);
        course2morningClass = viewB.findViewById(R.id.etCourse2MorningClass);
        course2eveningClass = viewB.findViewById(R.id.etCourse2EveningClass);


        CVCourse2ScheduledClass = viewB.findViewById(R.id.cvCourse2ScheduledClass);
        CVCourse1ScheduledClass = viewB.findViewById(R.id.cvCourse1ScheduledClass);
        course1Date = viewB.findViewById(R.id.cvCourse1Date);
        course1MorningClass = viewB.findViewById(R.id.cvCourse1MorningClass);
        course1EveningClass = viewB.findViewById(R.id.cvCourse1EveningClass);
        course2Date = viewB.findViewById(R.id.cvCourse2Date);
        course2MorningClass = viewB.findViewById(R.id.cvCourse2MorningClass);
        course2EveningClass = viewB.findViewById(R.id.cvCourse2EveningClass);
        tutor1 = viewB.findViewById(R.id.cvtutor1);
        tutor2 = viewB.findViewById(R.id.cvtutor2);

        recyclerViewCourse1 = viewB.findViewById(R.id.rvCourse1Material);
        recyclerViewCourse2 = viewB.findViewById(R.id.rvCourse2Material);


        UserName = FirebaseAuth.getInstance().getCurrentUser();
        userFromFireBase = UserName.getPhoneNumber();


        referenceA = FirebaseDatabase.getInstance().getReference("tutorUser").child(userFromFireBase).child("user");
        referenceA.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String currentUser = dataSnapshot.getValue(String.class);

                if (currentUser != null) {
                    if (currentUser.equals(userFromFireBase)) {
                        tutor1.setVisibility(View.VISIBLE);
                        tutor2.setVisibility(View.VISIBLE);
                    }
                } else {
                    tutor1.setVisibility(View.GONE);
                    tutor2.setVisibility(View.GONE);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getContext(), "Database connection Lost", Toast.LENGTH_SHORT).show();

            }
        });


//        referenceA = FirebaseDatabase.getInstance().getReference("tutorUser");
//        referenceA.child(userFromFireBase).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
//
//                DataSnapshot dataSnapshot = task.getResult();
//                currentUser = String.valueOf(dataSnapshot.child("user").getValue());
//
//
//                if (userFromFireBase.equals(currentUser)) {
//
//                    tutor1.setVisibility(View.VISIBLE);
//                    tutor2.setVisibility(View.VISIBLE);
//
//
//                } else {
//
//                    tutor1.setVisibility(View.GONE);
//                    tutor2.setVisibility(View.GONE);
//
//
//                }
//
//
//            }
//        });


        recyclerViewCourse1.setLayoutManager(new LinearLayoutManager(getContext()));


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(adapter);
        spinnerCourseUpdate.setAdapter(adapter);
        spinnerCourseAdd.setAdapter(adapter);
        spinnerCourseMaterial.setAdapter(adapter);


        spinnerCourseMaterial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Course 1")) {

                    recyclerViewCourse1.setVisibility(View.VISIBLE);
                    recyclerViewCourse2.setVisibility(View.GONE);


                    recyclerViewCourse1 = viewB.findViewById(R.id.rvCourse1Material);
                    recyclerViewCourse1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

                    helperSubject1[] helperSubject3 = new helperSubject1[]{
                            new helperSubject1("Course 1", "xyz", R.drawable.a),
                            new helperSubject1("Course 2", "xyz", R.drawable.b),
                            new helperSubject1("Course 3", "xyz", R.drawable.c),
                            new helperSubject1("Course 4", "xyz", R.drawable.d),
                            new helperSubject1("Course 1", "xyz", R.drawable.a),
                            new helperSubject1("Course 2", "xyz", R.drawable.b),
                            new helperSubject1("Course 3", "xyz", R.drawable.c)

                    };

                    videoAdapter videoAdapter1 = new videoAdapter(helperSubject3, getContext());
                    recyclerViewCourse1.setAdapter(videoAdapter1);


                } else if (selectedItem.equals("Course 2")) {

                    recyclerViewCourse1.setVisibility(View.GONE);
                    recyclerViewCourse2.setVisibility(View.VISIBLE);


                    recyclerViewCourse2 = viewB.findViewById(R.id.rvCourse2Material);
                    recyclerViewCourse2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

                    helperSubject1[] helperSubject3 = new helperSubject1[]{
                            new helperSubject1("Course 1", "xyz", R.drawable.a),
                            new helperSubject1("Course 2", "xyz", R.drawable.b),
                            new helperSubject1("Course 3", "xyz", R.drawable.c),
                            new helperSubject1("Course 4", "xyz", R.drawable.d),
                            new helperSubject1("Course 1", "xyz", R.drawable.a),
                            new helperSubject1("Course 2", "xyz", R.drawable.b),
                            new helperSubject1("Course 3", "xyz", R.drawable.c)

                    };

                    videoAdapter videoAdapter1 = new videoAdapter(helperSubject3, getContext());
                    recyclerViewCourse2.setAdapter(videoAdapter1);

                } else {

                    recyclerViewCourse1.setVisibility(View.GONE);
                    recyclerViewCourse2.setVisibility(View.GONE);

                }
            } // to close the onItemSelected


            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Course 1")) {

                    Toast.makeText(getContext(), "course 1 Selected", Toast.LENGTH_SHORT).show();

                    liveClass.setText("Live Class");
                    liveClass.setVisibility(View.VISIBLE);
                    className.setVisibility(View.VISIBLE);

                    onGoingClassCourse1.setVisibility(View.VISIBLE);


                    CVCourse1ScheduledClass.setVisibility(View.VISIBLE);
                    CVCourse2ScheduledClass.setVisibility(View.GONE);


                    onGoingClassCourse1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                                    .setRoom("Course1OnGoingClass")
                                    .setWelcomePageEnabled(false)
                                    .build();

                            JitsiMeetActivity.launch(getContext(), options);
                        }
                    });

                    Date d = new Date();
                    String date = (String) DateFormat.format("dd-MM-yyyy", d.getTime());


                    referenceCourse1 = FirebaseDatabase.getInstance().getReference("course1schedule");
                    referenceCourse1.child(date).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {

                            DataSnapshot dataSnapshot = task.getResult();
                            String course1MorningClass = String.valueOf(dataSnapshot.child("course1MorningClass").getValue());
                            String course1EveningClass = String.valueOf(dataSnapshot.child("getCourse1EveningClass").getValue());


                            course1MorningClassName.setText(course1MorningClass);
                            course1EveningClassName.setText(course1EveningClass);


                        }
                    });


                } else if (selectedItem.equals("Course 2")) {
                    Toast.makeText(getContext(), "course 2 Selected", Toast.LENGTH_SHORT).show();

                    liveClass.setText("Live Class");
                    liveClass.setVisibility(View.VISIBLE);
                    className.setVisibility(View.VISIBLE);

                    onGoingClassCourse1.setVisibility(View.VISIBLE);


                    CVCourse1ScheduledClass.setVisibility(View.GONE);
                    CVCourse2ScheduledClass.setVisibility(View.VISIBLE);

                    onGoingClassCourse2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                                    .setRoom("Course1OnGoingClass")
                                    .setWelcomePageEnabled(false)
                                    .build();

                            JitsiMeetActivity.launch(getContext(), options);
                        }
                    });


                    Date d = new Date();
                    String date = (String) DateFormat.format("dd-MM-yyyy", d.getTime());


                    referenceCourse2 = FirebaseDatabase.getInstance().getReference("course2schedule");
                    referenceCourse2.child(date).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {

                            DataSnapshot dataSnapshot = task.getResult();
                            String course2MorningClass = String.valueOf(dataSnapshot.child("course2MorningClass").getValue());
                            String course2EveningClass = String.valueOf(dataSnapshot.child("course2EveningClass").getValue());


                            course2MorningClassName.setText(course2MorningClass);
                            course2EveningClassName.setText(course2EveningClass);


                        }
                    });

                } else {
                    Toast.makeText(getContext(), "No Course Selected", Toast.LENGTH_SHORT).show();

                    liveClass.setText("select Your Course from Drop Down list");
                    CVCourse2ScheduledClass.setVisibility(View.GONE);
                    CVCourse1ScheduledClass.setVisibility(View.GONE);

                    className.setVisibility(View.GONE);
//                    className.setVisibility(View.GONE);
//                    scheduledClass.setVisibility(View.GONE);
//
//                    onGoingClassCourse1.setVisibility(View.GONE);
//                    onGoingClassCourse2.setVisibility(View.GONE);
//
//                    spinnerScheduledClass.setVisibility(View.GONE);
//
//                    CVCourse1ScheduledClass.setVisibility(View.GONE);

                }
            } // to close the onItemSelected


            @Override
            public void onNothingSelected(AdapterView<?> parent) {


//                Toast.makeText(getContext(), "my name is  nothing", Toast.LENGTH_SHORT).show();

            }
        });


        spinnerCourseUpdate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Course 1")) {


                    TVcourse1Date.setText("Date :");

                    TVcourse1Date.setVisibility(View.VISIBLE);


                    course1dateOfPeriod.setVisibility(View.VISIBLE);

                    course1Date.setVisibility(View.VISIBLE);
                    course1MorningClass.setVisibility(View.VISIBLE);
                    course1EveningClass.setVisibility(View.VISIBLE);

                    course2Date.setVisibility(View.GONE);
                    course2MorningClass.setVisibility(View.GONE);
                    course2EveningClass.setVisibility(View.GONE);

                    updateCourse1Schedule.setVisibility(View.VISIBLE);
                    updateCourse2Schedule.setVisibility(View.GONE);


                    updateCourse1Schedule.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            updateCourse1ScheduleInfo();

                        }
                    });


                } else if (selectedItem.equals("Course 2")) {

                    course1Date.setVisibility(View.GONE);
                    course1MorningClass.setVisibility(View.GONE);
                    course1EveningClass.setVisibility(View.GONE);

                    course2Date.setVisibility(View.VISIBLE);
                    course2MorningClass.setVisibility(View.VISIBLE);
                    course2EveningClass.setVisibility(View.VISIBLE);

                    updateCourse1Schedule.setVisibility(View.GONE);
                    updateCourse2Schedule.setVisibility(View.VISIBLE);

                    updateCourse2Schedule.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            updateCourse2ScheduleInfo();

                        }
                    });


                } else {


                    TVcourse1Date.setVisibility(View.VISIBLE);
                    course1Date.setVisibility(View.VISIBLE);

                    course1dateOfPeriod.setVisibility(View.GONE);
                    course1MorningClass.setVisibility(View.GONE);
                    course1EveningClass.setVisibility(View.GONE);

                    course2Date.setVisibility(View.GONE);
                    course2MorningClass.setVisibility(View.GONE);
                    course2EveningClass.setVisibility(View.GONE);

                    updateCourse1Schedule.setVisibility(View.GONE);
                    updateCourse2Schedule.setVisibility(View.GONE);

                    TVcourse1Date.setText("Select the course from drop down list");


                }
            } // to close the onItemSelected


            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        spinnerCourseAdd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Course 1")) {

                    addCourse1.setVisibility(View.VISIBLE);
                    addCourse2.setVisibility(View.GONE);

                    addCourse1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            startActivity(new Intent(getContext(), course1VideoUpload.class));
                        }
                    });


                } else if (selectedItem.equals("Course 2")) {

                    addCourse1.setVisibility(View.GONE);
                    addCourse2.setVisibility(View.VISIBLE);

                    addCourse1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            startActivity(new Intent(getContext(), course2VideoUpload.class));
                        }
                    });


                } else {

                    addCourse1.setVisibility(View.GONE);
                    addCourse2.setVisibility(View.GONE);

                }
            } // to close the onItemSelected


            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        return viewB;
    }


    private void updateCourse1ScheduleInfo() {

        rootnood1 = FirebaseDatabase.getInstance();
        reference1 = rootnood1.getReference("course1schedule");

        String Course1dateFromET = course1dateOfPeriod.getText().toString();
        String Course1morningClassFromET = course1morningClass.getText().toString();
        String Course1eveningClassFromET = course1eveningClass.getText().toString();


        helperCourse1ScheduleUpdate helperClass1 = new helperCourse1ScheduleUpdate(Course1dateFromET, Course1morningClassFromET, Course1eveningClassFromET);

        reference1.child(Course1dateFromET).setValue(helperClass1);

        Toast.makeText(getContext(), "UPDATED Course 1 Schedule", Toast.LENGTH_SHORT).show();

    }

    private void updateCourse2ScheduleInfo() {

        rootnood2 = FirebaseDatabase.getInstance();
        reference2 = rootnood2.getReference("course2schedule");

        String Course2dateFromET = course2dateOfPeriod.getText().toString();
        String Course2morningClassFromET = course2morningClass.getText().toString();
        String Course2eveningClassFromET = course2eveningClass.getText().toString();


        helperCourse2ScheduleUpdate helperClass2 = new helperCourse2ScheduleUpdate(Course2dateFromET, Course2morningClassFromET, Course2eveningClassFromET);

        reference2.child(Course2dateFromET).setValue(helperClass2);

        Toast.makeText(getContext(), "UPDATED Course 2 Schedule", Toast.LENGTH_SHORT).show();

    }


}