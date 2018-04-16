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

public class GolfersActivity extends AppCompatActivity {

    DatabaseHelper myDB;

    Button btnAddGolfer;
    Button btnUpdateGolfer;
    Button btnViewGolfers;
    Button btnDeleteGolfer;
    EditText edFirstName;
    EditText edSurname;
    EditText edGolferId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_golfers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDB = new DatabaseHelper(this);

        btnAddGolfer = (Button) findViewById(R.id.buttonAddGolfer);
        btnUpdateGolfer = (Button) findViewById(R.id.buttonUpdateGolfer);
        btnViewGolfers = (Button) findViewById(R.id.buttonViewGolfers);
        btnDeleteGolfer = (Button) findViewById(R.id.buttonDeleteGolfer);
        edFirstName = (EditText) findViewById(R.id.editFirstName);
        edSurname = (EditText) findViewById(R.id.editSurname);
        edGolferId = (EditText) findViewById(R.id.editGolferId);

        AddData();
        DeleteData();
        UpdateData();
        ViewAll();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public void AddData() {
        btnAddGolfer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.addGolfer(edFirstName.getText().toString(),
                                edSurname.getText().toString() );

                        if(isInserted == true)
                            Toast.makeText(GolfersActivity.this,"Golfer Added",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(GolfersActivity.this,"Golfer NOT Added",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData() {
        btnUpdateGolfer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDB.updateGolfer(edFirstName.getText().toString(),
                                edSurname.getText().toString(),
                                edGolferId.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(GolfersActivity.this,"Golfer Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(GolfersActivity.this,"Golfer NOT Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void ViewAll() {
        btnViewGolfers.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDB.getAllGolfers();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Surname :"+ res.getString(2)+"\n\n");
                        }

                        // Show all golfers
                        showMessage("Golfers",buffer.toString());
                    }
                }
        );
    }

    public void DeleteData() {
        btnDeleteGolfer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDB.deleteGolfer(edGolferId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(GolfersActivity.this,"Golfer Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(GolfersActivity.this,"Golfer NOT Deleted",Toast.LENGTH_LONG).show();
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
                setContentView(R.layout.activity_golfers);
                return true;
            case R.id.action_courses:
                Intent intent = new Intent(GolfersActivity.this, CoursesActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_stats:
                setContentView(R.layout.activity_statistics);
                return true;
            case R.id.action_about:
                AlertDialog aboutDialog = new AlertDialog.Builder(GolfersActivity.this).create();
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
