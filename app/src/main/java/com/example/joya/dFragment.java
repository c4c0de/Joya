package com.example.joya;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;


public class dFragment extends Fragment {

    TextView editBasicInformation, editPersonalDetails, signOut;


    public dFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.fragment_d, container, false);

        editBasicInformation = v.findViewById(R.id.tvEditBasicInformation);

        signOut = v.findViewById(R.id.tvSignOut);

        editBasicInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToBasicInformationUpdater = new Intent();
                goToBasicInformationUpdater.setClass(getActivity(),BasicInformationsUpdater.class);
                startActivity(goToBasicInformationUpdater);
            }
        });



        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent signOut = new Intent();
                signOut.setClass(getActivity(),OTP_authentication.class);
                startActivity(signOut);
            }
        });





        return v;
    }
}