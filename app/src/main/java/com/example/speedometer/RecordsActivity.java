package com.example.speedometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecordsActivity extends AppCompatActivity {

    Button allRecordsButton;
    Button weekRecordsButton;
    Button monthRecordsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        allRecordsButton = findViewById(R.id.button5);
        weekRecordsButton = findViewById(R.id.button6);
        monthRecordsButton = findViewById(R.id.button7);
    }

    public void openAllRecords(View view) {
        Intent intent = new Intent(this, AllRecordsActivity.class);
        startActivity(intent);
    }

    public void openWeekRecords(View view) {
        Intent intent = new Intent(this, WeeksRecordsActivity.class);
        startActivity(intent);
    }

    public void openMonthRecords(View view) {
        Intent intent = new Intent(this, MonthRecordsActivity.class);
        startActivity(intent);
    }

}