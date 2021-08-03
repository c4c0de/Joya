package com.example.joya;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;


public class cFragment extends Fragment {

    Button createClass, buttonClass1, buttonClass2, buttonClass3, buttonClass4, buttonClass5;

    LinearLayout student, nonStudent;

    String currentUser;
    String userFromFireBase;
    FirebaseUser UserName;
    DatabaseReference referenceA;


    public cFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c, container, false);

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


        referenceA = FirebaseDatabase.getInstance().getReference("Course1User");
        referenceA.child(userFromFireBase).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {

                DataSnapshot dataSnapshot = task.getResult();
                currentUser = String.valueOf(dataSnapshot.child("user").getValue());


                if (userFromFireBase.equals(currentUser)) {

                    student.setVisibility(View.VISIBLE);
                    nonStudent.setVisibility(View.GONE);


                } else {

                    student.setVisibility(View.GONE);
                    nonStudent.setVisibility(View.VISIBLE);

                }


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
}