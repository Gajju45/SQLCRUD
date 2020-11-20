package com.android.gajju45.sqlcrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    DatabaseHelper myDb;
    Button btn_insertData, btnView ,btn_update,btnDelete;
    EditText etName, etSurname, etmarks,ettxtid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        btn_insertData = findViewById(R.id.insert);
        etName = findViewById(R.id.editTextName);
        etSurname = findViewById(R.id.editTextSurname);
        etmarks = findViewById(R.id.editTextMarks);
        btnView = findViewById(R.id.view);
        btn_update=findViewById(R.id.update);
        ettxtid=findViewById(R.id.editTextId);
        btnDelete=findViewById(R.id.delete);



        btn_insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isinsert = myDb.insertData(etName.getText().toString(), etSurname.getText().toString(), etmarks.getText().toString());

                if (isinsert == true) {
                    Toast.makeText(MainActivity.this, "Insert", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No insert", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor = myDb.getAllData();

                if (cursor.getCount() == 0) {
                    showMessage("Error", "Empty Data");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("ID: " + cursor.getString(0) + "\n");
                    buffer.append("NAME:" + cursor.getString(1) + "\n");
                    buffer.append("SURNAME: " + cursor.getString(2) + "\n");
                    buffer.append("MARKS: " + cursor.getString(3) + "\n");
                }

                showMessage("Data", buffer.toString());

            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate=myDb.updateData(ettxtid.getText().toString(),etName.getText().toString(),
                        etSurname.getText().toString(),etmarks.getText().toString());

                if (isUpdate == true)
                {
                    Toast.makeText(MainActivity.this, "Data update", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Not update", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Integer deletRow =myDb.deleteData(ettxtid.getText().toString());
              if (deletRow>0){
                  Toast.makeText(MainActivity.this, "Data Delete", Toast.LENGTH_SHORT).show();
              }
              else
              {
                  Toast.makeText(MainActivity.this, "Delete", Toast.LENGTH_SHORT).show();
              }
            }
        });
    }

    //for view data
    public void showMessage(String title, String Message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }


}