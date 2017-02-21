package com.thernsgroup.addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ViewContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        final DBconnectivity db = new DBconnectivity(this);
        Intent intent=getIntent();
        long id= intent.getLongExtra("ID",0);
        values val = db.getValuesById(id);
        ((TextView)findViewById(R.id.name)).setText(val.getName());
        ((TextView)findViewById(R.id.mobile)).setText(val.getMobile());
        ((TextView)findViewById(R.id.email)).setText(val.getEmail());
    }

    protected void cancel(View v)
    {
        finish();
    }

    protected  void edit(View v)
    {
        int id=getIntent().getIntExtra("ID",0);
        Intent intent=new Intent(this,EditContact.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }
}
