package com.example.drf.mygolfstats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "statsTracker";

    //Golfers Table
    private static final String TABLE_GOLFERS = "golfers";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "firstName";
    private static final String COLUMN_SURNAME = "surname";

    //Courses Table
    private static final String TABLE_COURSES = "courses";
    private static final String COLUMN_COURSE = "course";
    private static final String COLUMN_PAR = "par";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_SLOPE = "slope";

    //Golfers table create statement
    private static final String CREATE_TABLE_GOLFERS = "CREATE TABLE " + TABLE_GOLFERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_SURNAME + " TEXT"
            + ")";

    //Courses table create statement
    private static final String CREATE_TABLE_COURSES = "CREATE TABLE " + TABLE_COURSES + "("
            + COLUMN_COURSE + " TEXT PRIMARY KEY,"
            + COLUMN_PAR + " INTEGER,"
            + COLUMN_RATING + " DOUBLE,"
            + COLUMN_SLOPE + " INTEGER"
            + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_GOLFERS);
        db.execSQL(CREATE_TABLE_COURSES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOLFERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        onCreate(db);
    }

    public boolean addGolfer (String name, String surname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_SURNAME, surname);
        long result = db.insert(TABLE_GOLFERS, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean updateGolfer(String id, String name, String surname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_NAME, name);
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
