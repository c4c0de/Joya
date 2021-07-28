package com.example.joya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BasicInformationsUpdater extends AppCompatActivity {

    EditText name, email, rollNumber, DOB, gender, bloodGroup, PermanentAdress, pinCode;
    Button updateInformation;

    TextView phoneNumber;

    FirebaseDatabase rootnood;
    DatabaseReference reference;

    FirebaseUser userMobileNumber;

    String userMobileNumberFromDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_informations_updater);


        name = findViewById(R.id.etName);
        email = findViewById(R.id.etEmail);
        phoneNumber = findViewById(R.id.tvmobileNumber);
        rollNumber = findViewById(R.id.etRollNumber);


        DOB = findViewById(R.id.etDateOfBirth);
        gender = findViewById(R.id.etGender);
        bloodGroup = findViewById(R.id.etBloodGroup);
        PermanentAdress = findViewById(R.id.etPermanentAdress);
        pinCode = findViewById(R.id.etPinCode);

        updateInformation = findViewById(R.id.btUpdateBasicInformation);


        displayUserNumber();


        updateInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = name.getText().toString();
                String Email = email.getText().toString();
                String PhoneNumber = phoneNumber.getText().toString();
                String RollNumber = rollNumber.getText().toString();

                String dateOfBirth = DOB.getText().toString();
                String Gender = gender.getText().toString();
                String BloodGroup = bloodGroup.getText().toString();
                String permanentAdress = PermanentAdress.getText().toString();
                String PinCode = pinCode.getText().toString();


                if (Name.isEmpty() && Email.isEmpty() && RollNumber.isEmpty() && dateOfBirth.isEmpty() && Gender.isEmpty() && BloodGroup.isEmpty()
                        && permanentAdress.isEmpty() && PinCode.isEmpty()) {
                    name.setError("Don't leave it blank");
                    email.setError("Don't leave it blank");
                    rollNumber.setError("Don't leave it blank");
                    DOB.setError("Don't leave it blank");
                    gender.setError("Don't leave it blank");
                    bloodGroup.setError("Don't leave it blank");
                    PermanentAdress.setError("Don't leave it blank");
                    pinCode.setError("Don't leave it blank");


                } else {
                    updateInfo();

//                transferInfo();

                    Intent intent = new Intent(getApplicationContext(), bottomDrawer.class);
                    startActivity(intent);
                }


            }
        });


    }

    private void displayUserNumber() {

        userMobileNumber = FirebaseAuth.getInstance().getCurrentUser();


        userMobileNumberFromDB = userMobileNumber.getPhoneNumber();

        phoneNumber.setText(userMobileNumberFromDB);

    }


//    private void transferInfo() {
//        String number = phoneNumber.getText().toString();
//
//        reference = FirebaseDatabase.getInstance().getReference("BasicInformation");
//        reference.child(number).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
//
//                DataSnapshot dataSnapshot = task.getResult();
//                String blood_group = String.valueOf(dataSnapshot.child("bloodGroup").getValue());
//                String D_O_B = String.valueOf(dataSnapshot.child("dob").getValue());
//                String GENDER = String.valueOf(dataSnapshot.child("gender").getValue());
//                String mobile_number = String.valueOf(dataSnapshot.child("mobileNumber").getValue());
//                String NAME = String.valueOf(dataSnapshot.child("name").getValue());
//                String permanent_adress = String.valueOf(dataSnapshot.child("permanentAdress").getValue());
//                String pin_code = String.valueOf(dataSnapshot.child("pinCode").getValue());
//                String roll_number = String.valueOf(dataSnapshot.child("rollNumber").getValue());
//
//                Intent intent = new Intent(getApplicationContext(), bottomDrawer.class);
//
//                intent.putExtra("bloodGroup", blood_group);
//                intent.putExtra("dob", D_O_B);
//                intent.putExtra("gender", GENDER);
//                intent.putExtra("mobileNumber", mobile_number);
//                intent.putExtra("name", NAME);
//                intent.putExtra("permanentAdress", permanent_adress);
//                intent.putExtra("pinCode", pin_code);
//                intent.putExtra("rollNumber", roll_number);
//
//
//                startActivity(intent);
//
//
//            }
//        });
//    }


    private void updateInfo() {
        rootnood = FirebaseDatabase.getInstance();
        reference = rootnood.getReference("BasicInformation");


        String Name = name.getText().toString();
        String Email = email.getText().toString();
        String PhoneNumber = phoneNumber.getText().toString();
        String RollNumber = rollNumber.getText().toString();

        String dateOfBirth = DOB.getText().toString();
        String Gender = gender.getText().toString();
        String BloodGroup = bloodGroup.getText().toString();
        String permanentAdress = PermanentAdress.getText().toString();
        String PinCode = pinCode.getText().toString();

        helperBasicInformationUpdate helperClass = new helperBasicInformationUpdate(Name, PhoneNumber, Email, RollNumber, dateOfBirth, Gender,
                BloodGroup, permanentAdress, PinCode);

        reference.child(PhoneNumber).setValue(helperClass);

        Toast.makeText(BasicInformationsUpdater.this, "UPDATED", Toast.LENGTH_SHORT).show();

    }


}