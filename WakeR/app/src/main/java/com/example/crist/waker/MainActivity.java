package com.example.crist.waker;

import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    //nav
    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;

    // fragments
    private WekkerFragment wWekkerfragment;
    private SleepCalcFragment sSleepCalcFragment;
    private ScannerFragment sScannnerFRagment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init all
        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mainNav = (BottomNavigationView) findViewById(R.id.main_nav);
        wWekkerfragment = new WekkerFragment();
        sSleepCalcFragment = new SleepCalcFragment();
        sScannnerFRagment = new ScannerFragment();



        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_wekker :
                        SetFragment(wWekkerfragment);
                        return true;

                    case R.id.nav_calculator :
                        SetFragment(sSleepCalcFragment);
                        return true;


                    case R.id.nav_scan :
                        SetFragment(sScannnerFRagment);
                        return true;

                    default : return  false ;


                }
            }
        });
    }

    private void SetFragment(Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame , fragment );
        fragmentTransaction.commit();
    }
}
