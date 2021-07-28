package com.example.joya;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bottomDrawer extends AppCompatActivity {


    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_drawer);
        //this line hide statusbar
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        navigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_bottom, new aFragment()).commit();
        navigationView.setSelectedItemId(R.id.a);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {

                    case R.id.a:
                        fragment = new aFragment();
                        break;

                    case R.id.b:
                        fragment = new bFragment();
                        break;

                    case R.id.c:
                        fragment = new cFragment();
                        break;

                    case R.id.d:
                        fragment = new dFragment();


                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_bottom, fragment).commit();

                return true;
            }
        });

//        sendDataToDFragment();





    }

//    private void sendDataToDFragment() {
//
//        Intent intent = getIntent();
//
//        String bloodGroup = intent.getStringExtra("bloodGroup");
//        String DOB = intent.getStringExtra("dob");
//        String gender = intent.getStringExtra("gender");
////        String mobileNumber = intent.getStringExtra("mobileNumber");
//        String name = intent.getStringExtra("name");
//        String permanentAdress = intent.getStringExtra("permanentAdress");
//        String pinCode = intent.getStringExtra("pinCode");
//        String rollNumber = intent.getStringExtra("rollNumber");
//
//        dFragment a = new dFragment();
//
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//
//        Bundle data = new Bundle();
//        data.putString("myData", name);
//        data.putString("bloodGroup", bloodGroup);
//        data.putString("dob", DOB);
//        data.putString("gender", gender);
////        data.putString("mobileNumber", mobileNumber);
//        data.putString("permanentAdress", permanentAdress);
//        data.putString("pinCode", pinCode);
//        data.putString("rollNumber", rollNumber);
//
//        a.setArguments(data);
//
////                fragmentTransaction.replace(R.id.nav_bottom, a).commit();


//    }


}