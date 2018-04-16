package com.example.drf.mygolfstats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "statsTracker.db";

    //Golfers Table
    public static final String TABLE_GOLFERS = "golfers";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "firstName";
    public static final String COLUMN_SURNAME = "surname";

    //Courses Table
    public static final String TABLE_COURSES = "courses";
    public static final String COLUMN_COURSE = "course";
    public static final String COLUMN_PAR = "par";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_SLOPE = "slope";

//    //Golfers table create statement
//    public static final String CREATE_TABLE_GOLFERS = "CREATE TABLE " + TABLE_GOLFERS + "("
//            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//            + COLUMN_NAME + " TEXT,"
//            + COLUMN_SURNAME + " TEXT"
//            + ")";
//
//    //Courses table create statement
//    public static final String CREATE_TABLE_COURSES = "CREATE TABLE " + TABLE_COURSES + "("
//            + COLUMN_COURSE + " TEXT PRIMARY KEY,"
//            + COLUMN_PAR + " INTEGER,"
//            + COLUMN_RATING + " DOUBLE,"
//            + COLUMN_SLOPE + " INTEGER"
//            + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_GOLFERS +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT)");
        db.execSQL("create table " + TABLE_COURSES +" (COURSE TEXT PRIMARY KEY, PAR INTEGER, RATING DOUBLE, SLOPE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOLFERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        onCreate(db);
    }

    public boolean addGolfer (String firstName, String surname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, firstName);
        contentValues.put(COLUMN_SURNAME, surname);
        long result = db.insert(TABLE_GOLFERS, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean updateGolfer(String id, String firstName, String surname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_NAME, firstName);
        contentValues.put(COLUMN_SURNAME, surname);
        db.update(TABLE_GOLFERS, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    public Integer deleteGolfer (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_GOLFERS, "ID = ?", new String[] {id});

    }

    public Cursor getAllGolfers() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_GOLFERS,null);
        return res;
    }

    public boolean addCourse (String course, String par, String rating, String slope) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COURSE, course);
        contentValues.put(COLUMN_PAR, par);
        contentValues.put(COLUMN_RATING, rating);
        contentValues.put(COLUMN_SLOPE, slope);
        long result = db.insert(TABLE_COURSES, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean updateCourse(String course, String par, String rating, String slope){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COURSE, course);
        contentValues.put(COLUMN_PAR, par);
        contentValues.put(COLUMN_RATING, rating);
        contentValues.put(COLUMN_SLOPE, slope);
        db.update(TABLE_COURSES, contentValues, "ID = ?", new String[]{course});
        return true;
    }


    public Cursor getAllCourses() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_COURSES,null);
        return res;
    }


}
