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
    public static final String COLUMN_GOLFER_ID = "golferId";
    public static final String COLUMN_NAME = "firstName";
    public static final String COLUMN_SURNAME = "surname";

    //Courses Table
    public static final String TABLE_COURSES = "courses";
    public static final String COLUMN_COURSE_ID = "courseId";
    public static final String COLUMN_COURSE = "course";
    public static final String COLUMN_PAR = "par";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_SLOPE = "slope";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_GOLFERS +" (golferId INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT, surname TEXT)");
        db.execSQL("create table " + TABLE_COURSES +" (courseId INTEGER PRIMARY KEY AUTOINCREMENT, course TEXT, par INTEGER, rating DOUBLE, slope INTEGER)");
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

    public boolean updateGolfer(String golferId, String firstName, String surname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_GOLFER_ID, golferId);
        contentValues.put(COLUMN_NAME, firstName);
        contentValues.put(COLUMN_SURNAME, surname);
        db.update(TABLE_GOLFERS, contentValues, "golferId = ?", new String[]{golferId});
        return true;
    }

    public Integer deleteGolfer (String golferId){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_GOLFERS, "golferId = ?", new String[] {golferId});

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

    public boolean updateCourse(String courseId, String course, String par, String rating, String slope){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COURSE_ID, courseId);
        contentValues.put(COLUMN_COURSE, course);
        contentValues.put(COLUMN_PAR, par);
        contentValues.put(COLUMN_RATING, rating);
        contentValues.put(COLUMN_SLOPE, slope);
        db.update(TABLE_COURSES, contentValues, "courseId = ?", new String[]{courseId});
        return true;
    }

    public Integer deleteCourse (String courseId){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_COURSES, "courseId = ?", new String[] {courseId});

    }


    public Cursor getAllCourses() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_COURSES,null);
        return res;
    }


}
