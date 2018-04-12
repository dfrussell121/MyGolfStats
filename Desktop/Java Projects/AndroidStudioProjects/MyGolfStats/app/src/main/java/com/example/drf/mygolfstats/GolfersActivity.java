package com.example.drf.mygolfstats;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GolfersActivity extends MainActivity {

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





}
