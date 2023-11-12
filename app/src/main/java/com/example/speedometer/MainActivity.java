package com.example.speedometer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    float speed;
    final int speedLimit = 10;

    SharedPreferences preferences;
    LocationManager locationManager;
    SimpleDateFormat sdf;
    private DBHelper dbHelper;
    MyTts myTts;
    Button buttonStartStop;
    Button buttonSettings;
    TextView speedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStartStop = findViewById(R.id.button);
        buttonSettings = findViewById(R.id.button2);
        speedTextView = findViewById(R.id.textView3);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        preferences = getSharedPreferences("MySharePrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("speedLimitNum", speedLimit);
        editor.apply(); //asynchronous method

        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        dbHelper = new DBHelper(MainActivity.this);
        myTts = new MyTts(this);
    }

    public void startGetSpeed(View view){
        if (buttonStartStop.getText().toString().equals("Start")) {
            buttonStartStop.setText("Stop");
            buttonSettings.setVisibility(View.INVISIBLE);
            if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
        else {
            locationManager.removeUpdates(this);
            myTts.stopSpeaking();
            setActivityBackgroundColor(Color.parseColor("#ffffff"));
            buttonStartStop.setText("Start");
            buttonSettings.setVisibility(View.VISIBLE);
            speedTextView.setText("0 Km/h");
        }
    }

    public void openSettings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        speed = (float) (location.getSpeed() * 3.6); //convert m/s to km/h
        speedTextView.setText(String.format("%s Km/h", speed));
        if (speed > (float) preferences.getInt("speedLimitNum",10)) {
            setActivityBackgroundColor(Color.parseColor("#c40808"));
            // Format the dates as "yyyy-MM-dd HH:mm:ss"
            String formattedDate = sdf.format(location.getTime());
            dbHelper.insertExceedance(location.getLongitude(),location.getLatitude(),location.getSpeed() * 3.6, formattedDate);
            myTts.speak("Slow down!");
        }
        else {
            setActivityBackgroundColor(Color.parseColor("#4287f5"));
            myTts.stopSpeaking();
        }
    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
}