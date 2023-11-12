package com.example.speedometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class AllRecordsActivity extends AppCompatActivity {

    ListView allRecordsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_records);
        allRecordsListView = findViewById(R.id.listView1);

        DBHelper dbHelper = new DBHelper(AllRecordsActivity.this);
        List<TempLocationModel> tmpLocModelList = dbHelper.getAllRecords();
        ArrayAdapter allRecordsArrayAdapter = new ArrayAdapter<TempLocationModel>(AllRecordsActivity.this, android.R.layout.simple_list_item_1, tmpLocModelList);
        allRecordsListView.setAdapter(allRecordsArrayAdapter);
    }
}