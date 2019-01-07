package com.example.crist.waker;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmSetActivity extends AppCompatActivity /* implements TimePickerDialog.OnTimeSetListener*/ {


    TimePicker alarmtime;
    TextClock currentTime;

    private TextView mTextView;

    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;
    boolean setAlarm = false;
    AlertDialog.Builder myAlertBuilder;
    private AlertDialog.Builder builder;
    //Ringtone alarmSound = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)); //ALARMSOUND




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_set);
        alarmtime = findViewById(R.id.timePicker);
        currentTime = findViewById(R.id.txtClock);

        //ALERTDIALOG
        myAlertBuilder = new
                AlertDialog.Builder(AlarmSetActivity.this);
        myAlertBuilder.setTitle("Alert");
        myAlertBuilder.setMessage("Click SCAN to continue, or Cancel :");
        // Add the dialog buttons.
        myAlertBuilder.setPositiveButton("SCAN", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked OK button.
                        Intent intent = new Intent(AlarmSetActivity.this, ScannerActivity.class);
                        intent.putExtra("setalarm", setAlarm );
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "waiting to scan",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        myAlertBuilder.setNegativeButton("Cancel", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User cancelled the dialog.
                        Toast.makeText(getApplicationContext(), "Pressed Cancel",
                                Toast.LENGTH_SHORT).show();
                    }
                });



        final Ringtone alarmSound = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)); //ALARMSOUND

        Button btnstop = (Button) findViewById(R.id.btnstop);
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringAlarmTime;
                Integer alarmHours = alarmtime.getCurrentHour();

                // stringAlarmTime = alarmHours.toString().concat(":").concat(stringAlarmTime).concat(" AM");
               // alarmSound.stop();
                if (setAlarm == true && currentTime.getText().toString().equals(AlarmTime())) {
                    myAlertBuilder.show();
                }
            }
        });

        Button btnStart = (Button) findViewById(R.id.btnstart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm = true ;

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if (setAlarm == true && currentTime.getText().toString().equals(AlarmTime())){
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


    /*
    mTextView = findViewById(R.id.textView);


        Button btnStart = (Button) findViewById(R.id.btnstart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        Button btnstop = (Button) findViewById(R.id.btnstop);
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringAlarmTime;
                Integer alarmHours = alarmtime.getCurrentHour();

                // stringAlarmTime = alarmHours.toString().concat(":").concat(stringAlarmTime).concat(" AM");
                alarmSound.stop();
             //cancelAlarm();
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

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute   );
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        startAlarm(c);



        /*TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(hourOfDay + " : " + minute);
    }

    private void updateTimeText(Calendar c){
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        mTextView.setText(timeText);
    }

    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

       // alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        alarmSound.play();


    }

    private void cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
        mTextView.setText("Alarm Canceled");
    }*/
}
