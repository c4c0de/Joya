package com.example.joya;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


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

                Toast.makeText(getActivity(), "hellooooooo", Toast.LENGTH_SHORT).show();
            }
        });

//        Toast.makeText(getActivity(), "hellooooooo", Toast.LENGTH_SHORT).show();


        // Inflate the layout for this fragment
        return v;
    }
}