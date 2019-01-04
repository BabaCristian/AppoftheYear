package com.example.crist.waker;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SleepCalcActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {


    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_calc);
        final TextView txtmessage  = findViewById(R.id.txtVmessage);
        final EditText fallasleeptime = (EditText) findViewById(R.id.editText);

        //init all
        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        Button btnwhenbed = (Button) findViewById(R.id.btnwhenbed);
        btnwhenbed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open timepicker
                android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Time Picker");

                TextView txtmessage  = findViewById(R.id.txtVmessage);
                txtmessage.setText("Go to bed at...");
            }
        });




       Button btnwhenup = (Button) findViewById(R.id.btnwhenup);
       btnwhenup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               txtmessage.setText("Wake up at...");
               StringBuilder sb = new StringBuilder();

               String amount = fallasleeptime.getText().toString();
               final int finalamount = Integer.parseInt(amount);
               Calendar c = Calendar.getInstance();
               SimpleDateFormat format = new SimpleDateFormat("HH:mm");
               Date date = c.getTime();
               for(int i = 1; i < 7; i++) {
                   c.setTime(date);
                   c.add(Calendar.HOUR, 1 * i);
                   c.add(Calendar.MINUTE, (30 * i) + finalamount);
                   String time;
                   if (i>4){
                       time = sb.append(format.format(c.getTime())).append("(best)").append(" \n").toString(); //append("Time to wake up : ")
                   }
                   else{
                       time = sb.append(format.format(c.getTime())).append(" \n").toString(); //append("Time to wake up : ")
                   }

                   TextView txtv = findViewById(R.id.txtView5);
                   txtv.setText(time);
               }

               txtmessage.setText("Wake up at...");



           }
       });

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

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        final TextView txtmessage  = findViewById(R.id.txtVmessage);
        TextView txtv = findViewById(R.id.txtView5);
        //int x = Math.abs(-5);
        StringBuilder sb2 = new StringBuilder();
        txtmessage.setText("Go to bed at...");
        String time;
        for(int i = 1; i < 7; i++) {
            int hournumber = hourOfDay;
            int minutenumber = minute + 15;
            int minutenumber2;
            hournumber = hournumber - 1*i;
            minutenumber = minutenumber + 30 * i;

            if (minutenumber > 0){
                minutenumber2 = minutenumber % 60;
                minutenumber = (int) Math.ceil((double) minutenumber/ (double) 60);
                hournumber = hournumber - minutenumber;
                minutenumber = 60 - minutenumber2;
            }
            if (hournumber < 0){
                hournumber = 24 - Math.abs(hournumber);
            }
            if (i>4){
                time = sb2.append(hournumber).append(":").append(minutenumber).append(" (best)").append(" \n").toString();
            }
            else{
                time = sb2.append(hournumber).append(":").append(minutenumber).append(" \n").toString();
            }

            txtv.setText(time);
        }
        //txvwhenbed.setText("");
    }
}
