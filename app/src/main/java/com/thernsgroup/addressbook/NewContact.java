package com.thernsgroup.addressbook;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        Button submit=(Button) findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBconnectivity mDbHelper = new DBconnectivity(getApplicationContext());
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name",((EditText)findViewById(R.id.editText)).getText().toString());
                values.put("mobile",((EditText)findViewById(R.id.editText4)).getText().toString());
                values.put("email",((EditText)findViewById(R.id.editText3)).getText().toString());

                long newRowId = db.insert("contact", null, values);
                finish();
            }
        });

        Button cancel=(Button)findViewById(R.id.button2);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
