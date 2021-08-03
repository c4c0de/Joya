package com.example.joya;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;


public class cFragment extends Fragment {

    Spinner spinner;
    Button button1, button2;

    FirebaseDatabase rootnood;
    DatabaseReference reference;

    static final String[] paths = {"Course 1", "Course 2"};

    Button createClass, buttonClass1, buttonClass2, buttonClass3, buttonClass4, buttonClass5;

    LinearLayout student, nonStudent;

    String currentUser;
    String userFromFireBase;
    FirebaseUser UserName;
    DatabaseReference referenceA;

    ImageView questionMark;


    public cFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c, container, false);

        questionMark = view.findViewById(R.id.question);

        createClass = view.findViewById(R.id.btClassCustom);
        buttonClass1 = view.findViewById(R.id.btClass1);
        buttonClass2 = view.findViewById(R.id.btClass2);
        buttonClass3 = view.findViewById(R.id.btClass3);
        buttonClass4 = view.findViewById(R.id.btClass4);
        buttonClass5 = view.findViewById(R.id.btClass5);

        student = view.findViewById(R.id.llCourseStudent);
        nonStudent = view.findViewById(R.id.llNonCourseStudent);


        UserName = FirebaseAuth.getInstance().getCurrentUser();
        userFromFireBase = UserName.getPhoneNumber();

        referenceA = FirebaseDatabase.getInstance().getReference("Course1User").child(userFromFireBase).child("user");
        referenceA.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String currentUser = dataSnapshot.getValue(String.class);

                if (currentUser != null) {
                    if (currentUser.equals(userFromFireBase)) {
                        student.setVisibility(View.VISIBLE);
                        nonStudent.setVisibility(View.GONE);
                    }
                } else {
                    student.setVisibility(View.GONE);
                    nonStudent.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getContext(), "Database connection Lost", Toast.LENGTH_SHORT).show();

            }
        });


//        referenceA = FirebaseDatabase.getInstance().getReference("Course1User");
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
//                    student.setVisibility(View.VISIBLE);
//                    nonStudent.setVisibility(View.GONE);
//
//
//                } else {
//
//                    student.setVisibility(View.GONE);
//                    nonStudent.setVisibility(View.VISIBLE);
//
//                }
//
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull @org.jetbrains.annotations.NotNull Exception e) {
//                Toast.makeText(getContext(), "failedddd sjhbsjfd", Toast.LENGTH_SHORT).show();
//            }
//        });



        button1 = view.findViewById(R.id.joinBtn1);
        button2 = view.findViewById(R.id.joinBtn2);

        spinner = view.findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
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

                            startActivity(new Intent(getContext(), bottomDrawer.class));
                        }
                    });


                } else if (selectedItem.equals("Course 2")) {

                    button1.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.GONE);

                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            updateCourseUser1();

                            startActivity(new Intent(getContext(), allVideos.class));
                        }
                    });

                }
            } // to close the onItemSelected


            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


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

        createClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), codeGeneratorForStudyClass.class));
            }
        });

        buttonClass1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom("class1")
                        .setWelcomePageEnabled(false)
                        .build();

                JitsiMeetActivity.launch(getContext(), options);

            }
        });

        questionMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Generate Code to move to a virtual discussion room", Toast.LENGTH_LONG).show();
            }
        });


        buttonClass2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom("class2")
                        .setWelcomePageEnabled(false)
                        .build();

                JitsiMeetActivity.launch(getContext(), options);

            }
        });

        buttonClass3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom("class3")
                        .setWelcomePageEnabled(false)
                        .build();

                JitsiMeetActivity.launch(getContext(), options);

            }
        });

        buttonClass4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom("class4")
                        .setWelcomePageEnabled(false)
                        .build();

                JitsiMeetActivity.launch(getContext(), options);

            }
        });

        buttonClass5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom("class5")
                        .setWelcomePageEnabled(false)
                        .build();

                JitsiMeetActivity.launch(getContext(), options);

            }
        });


        return view;
    }

    private void updateCourseUser1() {


        UserName = FirebaseAuth.getInstance().getCurrentUser();
        String userFromFireBase = UserName.getPhoneNumber();

        rootnood = FirebaseDatabase.getInstance();
        reference = rootnood.getReference("Course1User");


        helperCourseUser helperClass1 = new helperCourseUser(userFromFireBase);

        reference.child(userFromFireBase).setValue(helperClass1);

        Toast.makeText(getContext(), "UPDATED", Toast.LENGTH_SHORT).show();


    }

}