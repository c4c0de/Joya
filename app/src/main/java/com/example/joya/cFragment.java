package com.example.joya;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;


public class cFragment extends Fragment {

    Button createClass, buttonClass1, buttonClass2, buttonClass3, buttonClass4, buttonClass5;

    LinearLayout student, nonStudent;


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