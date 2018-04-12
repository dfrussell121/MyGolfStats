package com.example.drf.mygolfstats;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CoursesActivity extends MainActivity {

    Button btnViewCourses;
    Button btnAddCourse;
    Button btnUpdateCourse;
    EditText edName;
    EditText edPar;
    EditText edRating;
    EditText edSlope;

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
        edName = (EditText) findViewById(R.id.editCourse);
        edPar = (EditText) findViewById(R.id.editPar);
        edRating = (EditText) findViewById(R.id.editRating);
        edSlope = (EditText) findViewById(R.id.editSlope);

        AddData();
        UpdateData();
        ViewAll();
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

                        if(isInserted == true)
                            Toast.makeText(CoursesActivity.this,"Golfer Added",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(CoursesActivity.this,"Golfer NOT Added",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData() {
        btnUpdateCourse.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDB.updateCourse(edName.getText().toString(),
                                edPar.getText().toString(),
                                edRating.getText().toString(),
                                edSlope.getText().toString() );
                        if(isUpdate == true)
                            Toast.makeText(CoursesActivity.this,"Course Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(CoursesActivity.this,"Course NOT Updated",Toast.LENGTH_LONG).show();
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
                            buffer.append("Name :"+ res.getString(0)+"\n");
                            buffer.append("Par :"+ res.getString(1)+"\n");
                            buffer.append("Rating :"+ res.getString(2)+"\n");
                            buffer.append("Slope :"+ res.getString(3)+"\n\n");
                        }

                        // Show all courses
                        showMessage("Courses",buffer.toString());
                    }
                }
        );
    }

}