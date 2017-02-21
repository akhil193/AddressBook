package com.thernsgroup.addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        final DBconnectivity db = new DBconnectivity(this);
        Intent intent=getIntent();
        int id=intent.getIntExtra("ID",0);
        values val = db.getValuesById(id);
        ((EditText)findViewById(R.id.name)).setText(val.getName());
        ((EditText)findViewById(R.id.mobile)).setText(val.getMobile());
        ((EditText)findViewById(R.id.email)).setText(val.getEmail());
    }

    protected void save(View view)
    {

    }

    protected void cancel(View view)
    {
        finish();
    }
}
