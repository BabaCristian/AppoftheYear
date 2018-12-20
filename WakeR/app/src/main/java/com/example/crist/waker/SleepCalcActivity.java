package com.example.crist.waker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class SleepCalcActivity extends AppCompatActivity {
    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_calc);

        //init all
        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mainNav = (BottomNavigationView) findViewById(R.id.main_nav);



        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_wekker :
                        Intent intent = new Intent(SleepCalcActivity.this , MainActivity.class);
                        startActivity(intent);

                    case R.id.nav_calculator :
                          //Intent intent1 = new Intent(SleepCalcActivity.this , SleepCalcActivity.class);
                         //startActivity(intent1);
                    case R.id.nav_scan :

                        Intent intent1 = new Intent(SleepCalcActivity.this , ScannerActivity.class);
                        startActivity(intent1);



                }
                return true;
            }
        });
    }
}
