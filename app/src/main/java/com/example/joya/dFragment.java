package com.example.joya;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;


public class dFragment extends Fragment {

    Button btLogout;


    public dFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_d, container, false);

        btLogout = v.findViewById(R.id.btLogout);

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(getApplicationContext(), OTP_authentication.class));
//                finish();
                Intent i = new Intent();
                i.setClass(getActivity(), OTP_authentication.class);
                startActivity(i);

                Toast.makeText(getActivity(), "hellooooooo", Toast.LENGTH_SHORT).show();
            }
        });

//        Toast.makeText(getActivity(), "hellooooooo", Toast.LENGTH_SHORT).show();


        // Inflate the layout for this fragment
        return v;
    }
}