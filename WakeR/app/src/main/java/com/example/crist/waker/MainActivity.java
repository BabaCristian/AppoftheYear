package com.example.crist.waker;

import android.app.FragmentTransaction;
import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //nav
    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;

    // fragments


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoToAlarmSet();


//LIST ALARMS??




        //init all
        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_wekker :
                       // Intent intent = new Intent(MainActivity.this , ScannerActivity.class);
                       // startActivity(intent);

                    case R.id.nav_calculator :
                        Intent intent = new Intent(MainActivity.this , SleepCalcActivity.class);
                        startActivity(intent);
                    case R.id.nav_scan :

                        Intent intent1 = new Intent(MainActivity.this , ScannerActivity.class);
                        startActivity(intent1);



                }
                return true;
            }
        });
    }


    public void GoToAlarmSet() {
        Button btnAlarmSet = (Button) findViewById(R.id.btnAddAlarm);
        btnAlarmSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AlarmSetActivity.class));
            }
        });
    }
}

