package com.example.joya;

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

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;


public class bFragment extends Fragment {


    Spinner spinnerCourse, spinnerScheduledClass, spinnerCourseUpdate;
    static final String[] paths = {"........", "Course 1", "Course 2"};

    static final String[] pathsUpdate = {"Course 1", "Course 2"};

    TextView liveClass, className, scheduledClass;

    EditText course1dateOfPeriod, course1morningClass, course1eveningClass, course2dateOfPeriod, course2morningClass, course2eveningClass;

    Button onGoingClassCourse1, onGoingClassCourse2, updateCourse1Schedule, updateCourse2Schedule;


    FirebaseDatabase rootnood1, rootnood2;
    DatabaseReference reference1, reference2;


    public bFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewB = inflater.inflate(R.layout.fragment_b, container, false);


        Date d = new Date();
        String date = (String) DateFormat.format("dd-MM-yyyy ", d.getTime());


//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(d);
//        String[] days = new String[]{"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
//        String day = days[calendar.get(Calendar.DAY_OF_WEEK)-1];


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

        onGoingClassCourse1 = viewB.findViewById(R.id.btOnGoingClassCourse1);
        onGoingClassCourse2 = viewB.findViewById(R.id.btOnGoingClassCourse2);


        spinnerCourse = viewB.findViewById(R.id.spinnerCourse);
        spinnerScheduledClass = viewB.findViewById(R.id.spinnerScheduledClass);
        spinnerCourseUpdate = viewB.findViewById(R.id.spinnerCourseUpdate);

        course1dateOfPeriod = viewB.findViewById(R.id.etCourse1Date);
        course1morningClass = viewB.findViewById(R.id.etCourse1MorningClass);
        course1eveningClass = viewB.findViewById(R.id.etCourse1EveningClass);

        course2dateOfPeriod = viewB.findViewById(R.id.etCourse2Date);
        course2morningClass = viewB.findViewById(R.id.etCourse2MorningClass);
        course2eveningClass = viewB.findViewById(R.id.etCourse2EveningClass);

        updateCourse1Schedule = viewB.findViewById(R.id.btCourse1UpdateSchedule);
        updateCourse2Schedule = viewB.findViewById(R.id.btCourse2UpdateSchedule);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(adapter);


        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Course 1")) {

                    Toast.makeText(getContext(), "course 1 Selected", Toast.LENGTH_SHORT).show();

                    liveClass.setText("Live Class");
                    liveClass.setVisibility(View.VISIBLE);
                    className.setVisibility(View.VISIBLE);
                    scheduledClass.setVisibility(View.VISIBLE);

                    onGoingClassCourse1.setVisibility(View.VISIBLE);
                    onGoingClassCourse2.setVisibility(View.GONE);

                    spinnerScheduledClass.setVisibility(View.VISIBLE);

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


                } else if (selectedItem.equals("Course 2")) {
                    Toast.makeText(getContext(), "course 2 Selected", Toast.LENGTH_SHORT).show();

                    liveClass.setText("Live Class");
                    liveClass.setVisibility(View.VISIBLE);
                    className.setVisibility(View.VISIBLE);
                    scheduledClass.setVisibility(View.VISIBLE);

                    onGoingClassCourse1.setVisibility(View.GONE);
                    onGoingClassCourse2.setVisibility(View.VISIBLE);

                    spinnerScheduledClass.setVisibility(View.VISIBLE);

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

                } else {
                    Toast.makeText(getContext(), "No Course Selected", Toast.LENGTH_SHORT).show();

                    liveClass.setText("Select Your Course");
                    className.setVisibility(View.GONE);
                    scheduledClass.setVisibility(View.GONE);

                    onGoingClassCourse1.setVisibility(View.GONE);
                    onGoingClassCourse2.setVisibility(View.GONE);

                    spinnerScheduledClass.setVisibility(View.GONE);

                }
            } // to close the onItemSelected


            @Override
            public void onNothingSelected(AdapterView<?> parent) {


//                Toast.makeText(getContext(), "my name is  nothing", Toast.LENGTH_SHORT).show();

            }
        });

        ArrayAdapter<String> adapters = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, pathsUpdate);
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourseUpdate.setAdapter(adapters);


        spinnerCourseUpdate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }

            public void onNothingSelected(AdapterView<?> parent) {


//                Toast.makeText(getContext(), "my name is  nothing", Toast.LENGTH_SHORT).show();

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