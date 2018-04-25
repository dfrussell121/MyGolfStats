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
    public static final String COLUMN_GOLFER_ID = "golfer_id";
    public static final String COLUMN_NAME = "first_name";
    public static final String COLUMN_SURNAME = "surname";

    //Courses Table
    public static final String TABLE_COURSES = "courses";
    public static final String COLUMN_COURSE_ID = "course_id";
    public static final String COLUMN_COURSE = "course";
    public static final String COLUMN_PAR = "par";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_SLOPE = "slope";

    //Rounds Played Table
    public static final String TABLE_ROUNDS_PLAYED = "rounds_played";
    //public static final String COLUMN_GOLFER_ID = "golfer_id";  already declared
    //public static final String COLUMN_COURSE_ID = "course_id";  already declared
    public static final String COLUMN_SCORE_SHOT = "score_shot";
    //public static final String COLUMN_DATE = "date";

    //Statistics Table
    public static final String TABLE_STATISTICS = "statistics";
    //public static final String COLUMN_GOLFER_ID = "golfer_id";  already declared
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_HANDICAP = "handicap";
    public static final String COLUMN_ROUNDS_PLAYED = "rounds_played";
    public static final String COLUMN_AVERAGE_SCORE = "average_score";
    public static final String COLUMN_AVERAGE_SCORE_TO_PAR = "average_score_to_par";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_GOLFERS +" (golfer_id INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT, surname TEXT)");
        db.execSQL("create table " + TABLE_COURSES +" (course_id INTEGER PRIMARY KEY AUTOINCREMENT, course TEXT, par INTEGER, rating DOUBLE, slope INTEGER)");
        db.execSQL("create table " + TABLE_ROUNDS_PLAYED +" (golfer_id INTEGER, course_id INTEGER, score_shot INTEGER, " +
                "FOREIGN KEY(golfer_id) REFERENCES TABLE_GOLFERS(gofler_id), FOREIGN KEY(course_id) REFERENCES TABLE_COURSES(course_id))");
        db.execSQL("create table " + TABLE_STATISTICS +" (golfer_id INTEGER, first_name TEXT, last_name TEXT, handicap DOUBLE, " +
                "rounds_played INTEGER, average_score DOUBLE, average_score_to_par DOUBLE, " +
                "FOREIGN KEY(golfer_id, first_name, surname) REFERENCES TABLE_GOLFERS(golfer_id, first_name, surname))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOLFERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROUNDS_PLAYED);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATISTICS);
        onCreate(db);
    }

    public boolean addGolfer (String first_name, String surname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, first_name);
        contentValues.put(COLUMN_SURNAME, surname);
        long result = db.insert(TABLE_GOLFERS, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean updateGolfer(String golfer_id, String first_name, String surname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_GOLFER_ID, golfer_id);
        contentValues.put(COLUMN_NAME, first_name);
        contentValues.put(COLUMN_SURNAME, surname);
        db.update(TABLE_GOLFERS, contentValues, "golfer_id = ?", new String[]{golfer_id});
        return true;
    }

    public Integer deleteGolfer (String golfer_id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_GOLFERS, "golfer_id = ?", new String[] {golfer_id});

    }

    public Cursor getAllGolfers() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_GOLFERS,null);
        return res;
    }

    public boolean addCourse(String course, String par, String rating, String slope) {
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
        db.update(TABLE_COURSES, contentValues, "course_id = ?", new String[]{courseId});
        return true;
    }

    public Integer deleteCourse(String courseId){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_COURSES, "course_id = ?", new String[] {courseId});

    }


    public Cursor getAllCourses() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_COURSES,null);
        return res;
    }

    public boolean addRoundPlayed(String golfer_id, String course_id, String score_shot) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_GOLFER_ID, golfer_id);
        contentValues.put(COLUMN_COURSE_ID, course_id);
        contentValues.put(COLUMN_SCORE_SHOT, score_shot);
      //  contentValues.put(COLUMN_DATE, date);
        long result = db.insert(TABLE_ROUNDS_PLAYED, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getRoundsPlayed() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_ROUNDS_PLAYED, null);
        return res;
    }


}