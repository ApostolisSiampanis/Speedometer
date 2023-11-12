package com.example.speedometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    EditText editTextNum;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        editTextNum = findViewById(R.id.editTextNumber);

        preferences = getApplicationContext().getSharedPreferences("MySharePrefs",Context.MODE_PRIVATE);
        editTextNum.setText(String.valueOf(preferences.getInt("speedLimitNum",5)));

    }

    public void saveSettings(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("speedLimitNum", Integer.parseInt(editTextNum.getText().toString()));
        editor.apply(); //Asynchronous method
        Toast.makeText(this, "The new speed limit has been saved", Toast.LENGTH_SHORT).show();
    }

    public void openRecords(View view){
        Intent intent = new Intent(this, RecordsActivity.class);
        startActivity(intent);
    }


}