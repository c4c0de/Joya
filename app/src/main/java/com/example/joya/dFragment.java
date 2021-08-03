package com.example.joya;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

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


public class dFragment extends Fragment {

    TextView editBasicInformation, editPersonalDetails, signOut;

    TextView name, phoneNumber, email, rollNumber, DOB, bloodGroup, gender, permanentAdress, pinCode;

    private String strName, strMobileNumber, strEmail, strRollNumber, strDOB, strGender, strBloodGroup, strPermanentAdress, strPinCode;

    Button updateButton;

    String aa, bb;

    String userFromFireBase;

    FirebaseUser UserName;

    String userNumber;

    FirebaseDatabase rootnood;
    DatabaseReference reference;
    DatabaseReference reference1;
    DatabaseReference referenceA;

    Button tutorRegister;
    EditText tutorNumber, tutorName;

    CardView adminSpace;

    public dFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_d, container, false);

        editBasicInformation = v.findViewById(R.id.tvEditBasicInformation);

        signOut = v.findViewById(R.id.tvSignOut);

        name = v.findViewById(R.id.tvName);
        phoneNumber = v.findViewById(R.id.tvmobileNumber);
        email = v.findViewById(R.id.tvEmail);
        rollNumber = v.findViewById(R.id.tvRollNumber);
        DOB = v.findViewById(R.id.tvBateOfBirth);
        bloodGroup = v.findViewById(R.id.tvBloodGroup);
        gender = v.findViewById(R.id.tvGender);
        permanentAdress = v.findViewById(R.id.tvPermanentAdress);
        pinCode = v.findViewById(R.id.tvPinCode);

        tutorRegister = v.findViewById(R.id.btTutor);
        tutorName = v.findViewById(R.id.etTutorName);
        tutorNumber = v.findViewById(R.id.etTutorNumber);

        adminSpace = v.findViewById(R.id.cvAdmin);


        UserName = FirebaseAuth.getInstance().getCurrentUser();
        userFromFireBase = UserName.getPhoneNumber();

        referenceA = FirebaseDatabase.getInstance().getReference("adminUser").child(userFromFireBase).child("user");
        referenceA.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String currentUser = dataSnapshot.getValue(String.class);

                if (currentUser != null) {
                    if (currentUser.equals(userFromFireBase)) {

                        adminSpace.setVisibility(View.VISIBLE);

                    }
                } else {

                    adminSpace.setVisibility(View.GONE);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getContext(), "Database connection Lost", Toast.LENGTH_SHORT).show();

            }
        });


        tutorRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTutorInfo();
                tutorName.setText("");
                tutorNumber.setText("");

            }
        });


        displayUserNumber();

        displayAllInfo();


//        updateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                dataFromDataBAseThroughInfoUpdater();
//            }
//        });


        editBasicInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToBasicInformationUpdater = new Intent();
                goToBasicInformationUpdater.setClass(getActivity(), BasicInformationsUpdater.class);
                startActivity(goToBasicInformationUpdater);
            }
        });


        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent signOut = new Intent();
                signOut.setClass(getActivity(), OTP_authentication.class);
                startActivity(signOut);
            }
        });


        return v;
    }

    private void displayAllInfo() {

        userNumber = phoneNumber.getText().toString();

        reference = FirebaseDatabase.getInstance().getReference("BasicInformation");
        reference.child(userNumber).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {

                DataSnapshot dataSnapshot = task.getResult();
                String blood_group = String.valueOf(dataSnapshot.child("bloodGroup").getValue());
                String e_mail = String.valueOf(dataSnapshot.child("email").getValue());
                String D_O_B = String.valueOf(dataSnapshot.child("dob").getValue());
                String GENDER = String.valueOf(dataSnapshot.child("gender").getValue());
//                String mobile_number = String.valueOf(dataSnapshot.child("mobileNumber").getValue());
                String NAME = String.valueOf(dataSnapshot.child("name").getValue());
                String permanent_adress = String.valueOf(dataSnapshot.child("permanentAdress").getValue());
                String pin_code = String.valueOf(dataSnapshot.child("pinCode").getValue());
                String roll_number = String.valueOf(dataSnapshot.child("rollNumber").getValue());

                name.setText(NAME);
                email.setText(e_mail);
                bloodGroup.setText(blood_group);
                DOB.setText(D_O_B);
                gender.setText(GENDER);
                permanentAdress.setText(permanent_adress);
                pinCode.setText(pin_code);
                rollNumber.setText(roll_number);


            }
        });
    }

    private void displayUserNumber() {
        UserName = FirebaseAuth.getInstance().getCurrentUser();


        userFromFireBase = UserName.getPhoneNumber();

        phoneNumber.setText(userFromFireBase);

    }

    private void updateTutorInfo() {


        reference1 = FirebaseDatabase.getInstance().getReference("tutorUser");


        String Name = tutorName.getText().toString();
        String Number = tutorNumber.getText().toString();


        helperTutor helperClass = new helperTutor(Name, Number);

        reference1.child(Number).setValue(helperClass);

        Toast.makeText(getContext(), "UPDATED", Toast.LENGTH_SHORT).show();


    }


}