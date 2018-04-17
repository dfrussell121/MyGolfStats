package com.example.drf.mygolfstats;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CoursesActivity extends AppCompatActivity {

    DatabaseHelper myDB;


    Button btnViewCourses;
    Button btnAddCourse;
    Button btnUpdateCourse;
    Button btnDeleteCourse;
    EditText edName;
    EditText edPar;
    EditText edRating;
    EditText edSlope;
    EditText edCourseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDB = new DatabaseHelper(this);


        btnViewCourses = (Button) findViewById(R.id.buttonViewCourses);
        btnAddCourse = (Button) findViewById(R.id.buttonAddCourse);
        btnUpdateCourse = (Button) findViewById(R.id.buttonUpdateCourse);
        btnDeleteCourse = (Button) findViewById(R.id.buttonDeleteCourse);
        edName = (EditText) findViewById(R.id.editCourse);
        edPar = (EditText) findViewById(R.id.editPar);
        edRating = (EditText) findViewById(R.id.editRating);
        edSlope = (EditText) findViewById(R.id.editSlope);
        edCourseId = (EditText) findViewById(R.id.editCourseId);
        AddData();
        UpdateData();
        ViewAll();
        DeleteData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public void AddData() {
        btnAddCourse.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.addCourse(edName.getText().toString(),
                                edPar.getText().toString(),
                                edRating.getText().toString(),
                                edSlope.getText().toString() );

                        if(isInserted)
                            Toast.makeText(CoursesActivity.this,"Course Added",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(CoursesActivity.this,"Course NOT Added",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData() {
        btnUpdateCourse.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDB.updateCourse(edCourseId.getText().toString(),
                                edName.getText().toString(),
                                edPar.getText().toString(),
                                edRating.getText().toString(),
                                edSlope.getText().toString() );
                        if(isUpdate)
                            Toast.makeText(CoursesActivity.this,"Course Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(CoursesActivity.this,"Course NOT Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void DeleteData() {
        btnDeleteCourse.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDB.deleteCourse(edCourseId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(CoursesActivity.this,"Course Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(CoursesActivity.this,"Course NOT Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void ViewAll() {
        btnViewCourses.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDB.getAllCourses();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Par :"+ res.getString(2)+"\n");
                            buffer.append("Rating :"+ res.getString(3)+"\n");
                            buffer.append("Slope :"+ res.getString(4)+"\n\n");
                        }

                        // Show all courses
                        showMessage("Courses",buffer.toString());
                    }
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_golfers:
                Intent golfersIntent = new Intent(CoursesActivity.this, GolfersActivity.class);
                startActivity(golfersIntent);
                return true;
            case R.id.action_courses:
                Intent coursesIntent = new Intent(CoursesActivity.this, CoursesActivity.class);
                startActivity(coursesIntent);
                return true;
            case R.id.action_stats:
                Intent statsIntent = new Intent(CoursesActivity.this, StatisticsActivity.class);
                startActivity(statsIntent);
                return true;
            case R.id.action_about:
                AlertDialog aboutDialog = new AlertDialog.Builder(CoursesActivity.this).create();
                aboutDialog.setTitle("About this app");
                aboutDialog.setMessage("(c)2018 David Fisher");
                aboutDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                aboutDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showMessage(String title,String Message){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}