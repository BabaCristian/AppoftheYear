package com.example.crist.waker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmSetActivity extends AppCompatActivity {


    TimePicker alarmtime;
    TextClock currentTime;

   /* AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;*/




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_set);
        alarmtime = findViewById(R.id.timePicker);
        currentTime = findViewById(R.id.txtClock);
        final Ringtone alarmSound = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)); //ALARMSOUND

        Button btnstop = (Button) findViewById(R.id.btnstop);
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringAlarmTime;
                Integer alarmHours = alarmtime.getCurrentHour();

                // stringAlarmTime = alarmHours.toString().concat(":").concat(stringAlarmTime).concat(" AM");
                alarmSound.stop();
            }
        });

        Button btnStart = (Button) findViewById(R.id.btnstart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if (currentTime.getText().toString().equals(AlarmTime())){
                    alarmSound.play();

                }
                else   {
                    alarmSound.stop();
                }

            }
        }, 0, 1000);//Check elke 1000ms.



    }




    public String AlarmTime(){
        Integer alarmHours = alarmtime.getCurrentHour();
        Integer alarmMinutes = alarmtime.getCurrentMinute();
        String stringAlarmMinutes;
        String stringAlarmTime;
        if (alarmMinutes < 10){
            stringAlarmMinutes = "0";
            stringAlarmMinutes = stringAlarmMinutes.concat(alarmMinutes.toString());
        }
        else{
            stringAlarmMinutes = alarmMinutes.toString();
        }
        if (alarmHours>12){
            alarmHours=alarmHours-12;
            stringAlarmTime = alarmHours.toString().concat(":").concat(stringAlarmMinutes).concat(" PM");
        }
        else{
            stringAlarmTime = alarmHours.toString().concat(":").concat(stringAlarmMinutes).concat(" AM");
        }
        return stringAlarmTime;
    }

}
