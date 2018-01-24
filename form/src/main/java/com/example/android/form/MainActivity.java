package com.example.android.form;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName, editSurname, editMarks, editTextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    Button btnviewUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editName = findViewById(R.id.editText_name);
        editSurname = findViewById(R.id.editText_surname);
        editMarks = findViewById(R.id.editText_Marks);
        editTextId = findViewById(R.id.editText_id);
        btnAddData = findViewById(R.id.button_add);
        btnviewAll = findViewById(R.id.button_viewAll);
        btnviewUpdate = findViewById(R.id.button_update);
        btnDelete = findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }


    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(MainActivity.this, "DATA DELETED", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "DATA NOT DELETED", Toast.LENGTH_LONG).show();
                        editName.setText("");
                        editMarks.setText("");
                        editSurname.setText("");
                        editTextId.setText("");
                    }
                }
        );
    }


    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int isUpdate = myDb.updateData(editTextId.getText().toString(),
                                editName.getText().toString(),
                                editSurname.getText().toString(), editMarks.getText().toString());
                        if (isUpdate>-1)
                            Toast.makeText(MainActivity.this, "DATA UPDATED", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "DATA NOT UPDATED", Toast.LENGTH_LONG).show();
                        editName.setText("");
                        editMarks.setText("");
                        editSurname.setText("");
                        editTextId.setText("");
                    }
                }
        );
    }


    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(editTextId.getText().toString().equals("")||editName.getText().toString().equals("")||editSurname.getText().toString().equals("")||editMarks.getText().toString().equals("")){
                            Toast.makeText(MainActivity.this, "Enter all information", Toast.LENGTH_LONG).show();
                        }
                        else {
                            boolean isInserted = myDb.insertData(editName.getText().toString(),
                                    editSurname.getText().toString(),
                                    editMarks.getText().toString());
                            if (isInserted == true)
                                Toast.makeText(MainActivity.this, "DATA INSERTION SUCCESSFUL", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(MainActivity.this, "DATA INSERTION FAILED", Toast.LENGTH_LONG).show();

                        }
                        editName.setText("");
                        editMarks.setText("");
                        editSurname.setText("");
                        editTextId.setText("");
                    }
                }
        );
    }


    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("NO DATA", "INFORMATION NOT FOUND");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Discipline :" + res.getString(2) + "\n");
                            buffer.append("Age :" + res.getString(3) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }


    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
