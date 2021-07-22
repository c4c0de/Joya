package com.example.joya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.CDATASection;

import java.util.HashMap;
public class MainActivity extends AppCompatActivity {


    EditText CountryCode,PhoneNumber;
    Button SendOTPBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        });

    }
}
