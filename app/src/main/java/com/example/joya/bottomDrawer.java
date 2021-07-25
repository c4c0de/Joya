package com.example.joya;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

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
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_bottom, new aFragment()).commit();
        navigationView.setSelectedItemId(R.id.a);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){

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

    }
}