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

public class StatisticsActivity extends AppCompatActivity {

    DatabaseHelper myDB;

    Button btnAddRound;
    Button btnViewRounds;
    EditText edGolferId;
    EditText edCourseId;
    EditText edScoreShot;
    EditText edDate;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDB = new DatabaseHelper(this);

        btnAddRound = (Button) findViewById(R.id.buttonAddRound);
        btnViewRounds = (Button) findViewById(R.id.buttonViewRounds);
        edGolferId = (EditText) findViewById(R.id.editGolferId);
        edCourseId = (EditText) findViewById(R.id.editCourseId);
        edScoreShot = (EditText) findViewById(R.id.editScoreShot);
        edDate = (EditText) findViewById(R.id.editDate);

        AddData();
        ViewAllRounds();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public void AddData() {
        btnAddRound.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.addRoundPlayed(edGolferId.getText().toString(),
                                edCourseId.getText().toString(),
                                edScoreShot.getText().toString());
                               // edDate.getText().toString());

                        if(isInserted)
                            Toast.makeText(StatisticsActivity.this,"Round Added",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(StatisticsActivity.this,"Round NOT Added",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void ViewAllRounds() {
        btnViewRounds.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDB.getRoundsPlayed();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Course ID#:"+ res.getString(0)+"\n");
                            buffer.append("Score Shot:"+ res.getString(1)+"\n\n");
                        }

                        // Show all rounds played
                        showMessage("Rounds Played",buffer.toString());
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
                Intent golfersIntent = new Intent(StatisticsActivity.this, GolfersActivity.class);
                startActivity(golfersIntent);
                return true;
            case R.id.action_courses:
                Intent coursesIntent = new Intent(StatisticsActivity.this, CoursesActivity.class);
                startActivity(coursesIntent);
                return true;
            case R.id.action_about:
                AlertDialog aboutDialog = new AlertDialog.Builder(StatisticsActivity.this).create();
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
