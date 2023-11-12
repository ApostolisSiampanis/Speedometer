package com.example.speedometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MonthRecordsActivity extends AppCompatActivity {

    ListView monthRecordsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_records);
        monthRecordsListView = findViewById(R.id.listView3);

        DBHelper dbHelper = new DBHelper(MonthRecordsActivity.this);
        List<TempLocationModel> tmpLocModelList = dbHelper.getAllRecords();
        ArrayAdapter monthRecordsArrayAdapter = new ArrayAdapter<TempLocationModel>(MonthRecordsActivity.this, android.R.layout.simple_list_item_1, tmpLocModelList);
        monthRecordsListView.setAdapter(monthRecordsArrayAdapter);
    }
}