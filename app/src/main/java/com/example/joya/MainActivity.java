package com.example.joya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.CDATASection;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    EditText PhoneNumber, countryCode, enterOTP;


    Button SendOTPBtn, verifyOTP, resendOTP;
    String userPhoneNumber, verificationID;
    PhoneAuthProvider.ForceResendingToken token;
    FirebaseAuth FAuth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;

    EditText CountryCode,PhoneNumber;
    Button SendOTPBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        PhoneNumber = findViewById(R.id.PhoneNumber);
        SendOTPBtn = findViewById(R.id.SndOTPBtn);
        countryCode = findViewById(R.id.countryCode);

        verifyOTP = findViewById(R.id.verifyOTP);
        resendOTP = findViewById(R.id.resendOTP);
        enterOTP = findViewById(R.id.enterOTP);

        FAuth = FirebaseAuth.getInstance();

        SendOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countryCode.getText().toString().isEmpty()){
                    countryCode.setError("Required");
                    return;
                }
                if (PhoneNumber.getText().toString().isEmpty()) {
                    PhoneNumber.setError("Phone Number is Required");
                    return;
                }


                userPhoneNumber ="+"+countryCode.getText().toString()+ PhoneNumber.getText().toString();
                verifyPhoneNumber(userPhoneNumber);
                Toast.makeText(MainActivity.this, "" + userPhoneNumber, Toast.LENGTH_SHORT).show();

            }
        });


        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyPhoneNumber(userPhoneNumber);
                resendOTP.setEnabled(false);
            }
        });

        verifyOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(enterOTP.getText().toString().isEmpty()){
                    enterOTP.setError("Enter OTP first");
                    return;
                }

                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID,enterOTP.getText().toString());
                authenticateUser(credential);

            }
        });

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                authenticateUser(phoneAuthCredential);

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationID = s;
                token = forceResendingToken;


                PhoneNumber.setVisibility(View.GONE);
                SendOTPBtn.setVisibility(View.GONE);
                countryCode.setVisibility(View.GONE);


                resendOTP.setVisibility(View.VISIBLE);
                verifyOTP.setVisibility(View.VISIBLE);
                enterOTP.setVisibility(View.VISIBLE);
                resendOTP.setEnabled(false);


        CountryCode = findViewById(R.id.CC);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        SendOTPBtn = findViewById(R.id.SndOTPBtn);

        SendOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CountryCode.getText().toString().isEmpty()){
                    CountryCode.setError("Required");
                    return;
                }
                if (PhoneNumber.getText().toString().isEmpty()){
                    PhoneNumber.setError("Phone Number is Required");
                    return;
                }

                Toast.makeText(MainActivity.this, "+"+CountryCode.getText().toString()+PhoneNumber.getText().toString(), Toast.LENGTH_SHORT).show();


            }


            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                resendOTP.setEnabled(true);
            }
        };


    }

    public void verifyPhoneNumber(String phoneNum) {

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(FAuth)
                .setActivity(this)
                .setPhoneNumber(phoneNum)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setCallbacks(callbacks)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    public void authenticateUser(PhoneAuthCredential credentials) {
        FAuth.signInWithCredential(credentials).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainPage.class));
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainPage.class));
            finish();
        }

    }
}