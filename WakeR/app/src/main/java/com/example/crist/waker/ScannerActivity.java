package com.example.crist.waker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class ScannerActivity extends AppCompatActivity implements View.OnClickListener {
    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;
    private Button buttonScan;
    private TextView textViewName, textViewID;
    private IntentIntegrator qrScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        //init all
        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mainNav = (BottomNavigationView) findViewById(R.id.main_nav);
        qrScan = new IntentIntegrator(this);
        //testing
        buttonScan = (Button) findViewById(R.id.buttonScan);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewID = (TextView) findViewById(R.id.textViewID);
        buttonScan.setOnClickListener(this);



        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_wekker :
                         Intent intent = new Intent(ScannerActivity.this , MainActivity.class);
                         startActivity(intent);

                    case R.id.nav_calculator :
                        Intent intent1 = new Intent(ScannerActivity.this , SleepCalcActivity.class);
                        startActivity(intent1);
                    case R.id.nav_scan :

                     //   Intent intent1 = new Intent(ScannerActivity.this , SleepCalcActivity.class);
                     //   startActivity(intent1);



                }
                return true;
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //als er geen qr code is
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //als de qr code data bevat
                try {
                    //data naar json converteren voor database
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                    textViewName.setText(obj.getString("name"));
                    textViewID.setText(obj.getString("personalwakenr"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View view) {
qrScan.initiateScan();
    }
}
