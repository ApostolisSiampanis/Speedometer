package com.example.speedometer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//Creation of DB and help with CRUD operations
public class DBHelper extends SQLiteOpenHelper {


    public static final String MOMENT = "Moment";
    public static final String ID = "ID";
    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE = "latitude";
    public static final String SPEED = "speed";
    public static final String TIMESTAMP = "timestamp";

    public DBHelper(@Nullable Context context) {
        super(context, "speedometer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + MOMENT + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                LONGITUDE + " REAL," +
                LATITUDE + " REAL," +
                SPEED + " REAL," +
                TIMESTAMP + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    public void insertExceedance(double longitude, double latitude, double speed, String timestamp) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LONGITUDE, longitude);
        values.put(LATITUDE, latitude);
        values.put(SPEED, speed);
        values.put(TIMESTAMP, timestamp);
        database.insert(MOMENT,null,values);
        database.close();
    }

    public List<TempLocationModel> getAllRecords() {
        List<TempLocationModel> tmpLocModelList = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from " + MOMENT,null);

        while (cursor.moveToNext()) {
            double longitude = cursor.getDouble(1);
            double latitude = cursor.getDouble(2);
            double speed = cursor.getDouble(3);
            String timestamp = cursor.getString(4);
            TempLocationModel tmpLocModel = new TempLocationModel(longitude,latitude,speed,timestamp);
            tmpLocModelList.add(tmpLocModel);
        }
        return tmpLocModelList;
    }

    public List<TempLocationModel> getWeekRecords() {
        List<TempLocationModel> tmpLocModelList = new ArrayList<>();

        // Calculate the start and end dates for the previous 7 days
        Calendar calendar = Calendar.getInstance();
        Date endDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_WEEK, -6); // Set to the start 7 days before
        Date startDate = calendar.getTime();
        // Format the dates as "yyyy-MM-dd HH:mm:ss"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDateStr = dateFormat.format(startDate);
        String endDateStr = dateFormat.format(endDate);

        SQLiteDatabase database = this.getReadableDatabase();
        String[] selectionArgs = {startDateStr, endDateStr};
        Cursor cursor = database.rawQuery("Select * from " + MOMENT + " WHERE timestamp >= ? AND timestamp <= ?",selectionArgs);

        while (cursor.moveToNext()) {
            double longitude = cursor.getDouble(1);
            double latitude = cursor.getDouble(2);
            double speed = cursor.getDouble(3);
            String timestamp = cursor.getString(4);
            TempLocationModel tmpLocModel = new TempLocationModel(longitude,latitude,speed,timestamp);
            tmpLocModelList.add(tmpLocModel);
        }
        return tmpLocModelList;
    }

    public List<TempLocationModel> getMonthRecords() {
        List<TempLocationModel> tmpLocModelList = new ArrayList<>();

        // Calculate the start and end dates for the previous 30 days
        Calendar calendar = Calendar.getInstance();
        Date endDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -30); // Set to the start 30 days before
        Date startDate = calendar.getTime();
        // Format the dates as "yyyy-MM-dd HH:mm:ss"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDateStr = dateFormat.format(startDate);
        String endDateStr = dateFormat.format(endDate);

        SQLiteDatabase database = this.getReadableDatabase();
        String[] selectionArgs = {startDateStr, endDateStr};
        Cursor cursor = database.rawQuery("Select * from " + MOMENT + " WHERE timestamp >= ? AND timestamp <= ?",selectionArgs);

        while (cursor.moveToNext()) {
            double longitude = cursor.getDouble(1);
            double latitude = cursor.getDouble(2);
            double speed = cursor.getDouble(3);
            String timestamp = cursor.getString(4);
            TempLocationModel tmpLocModel = new TempLocationModel(longitude,latitude,speed,timestamp);
            tmpLocModelList.add(tmpLocModel);
        }
        return tmpLocModelList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
