package com.example.speedometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class WeeksRecordsActivity extends AppCompatActivity {

    ListView weeksRecordsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weeks_records);
        weeksRecordsListView = findViewById(R.id.listView4);

        DBHelper dbHelper = new DBHelper(WeeksRecordsActivity.this);
        List<TempLocationModel> tmpLocModelList = dbHelper.getAllRecords();
        ArrayAdapter weeksRecordsArrayAdapter = new ArrayAdapter<TempLocationModel>(WeeksRecordsActivity.this, android.R.layout.simple_list_item_1, tmpLocModelList);
        weeksRecordsListView.setAdapter(weeksRecordsArrayAdapter);
    }
}