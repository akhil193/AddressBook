package com.thernsgroup.addressbook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewContact.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        final DBconnectivity db = new DBconnectivity(this);
        Log.d("Reading: ", "Reading all data..");
        List<values> val = db.getAllvalues();

        LinearLayout linearLayoutMain = (LinearLayout) findViewById(R.id.contactHolder);

        if(((LinearLayout) linearLayoutMain).getChildCount() > 0)
            ((LinearLayout) linearLayoutMain).removeAllViews();

        for (final values cn : val) {

            ViewGroup.LayoutParams params;

            LinearLayout linearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                    Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(params2);

            TextView textView = new TextView(this);
            params2 = new LinearLayout.LayoutParams(
                    Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT,1);
            textView.setLayoutParams(params2);
            textView.setText(cn.getName());

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getApplicationContext(),ViewContact.class);
                    intent.putExtra("ID",cn.getId());
                    startActivity(intent);
                }
            });

            linearLayout.addView(textView);

            //phone button
            ImageButton imageButton = new ImageButton(this);
            params = textView.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;

            int imageResource = getResources().getIdentifier("@drawable/phone", null, getPackageName());

            Drawable res = getResources().getDrawable(imageResource);
            imageButton.setImageDrawable(res);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+cn.getMobile()));

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(callIntent);
                }
            });

            linearLayout.addView(imageButton);

            //email button
            imageButton = new ImageButton(this);
            params = textView.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;

            imageResource = getResources().getIdentifier("@drawable/email", null, getPackageName());

            res = getResources().getDrawable(imageResource);
            imageButton.setImageDrawable(res);

            linearLayout.addView(imageButton);

            linearLayoutMain.addView(linearLayout);

            String log = "Id: " + cn.getId() + " ,Name: "
                    + cn.getName() + " ,Mobile: "
                    + cn.getMobile() + " ,Email: "
                    + cn.getEmail();
            Log.d("value: ", log);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view)
    {

    }
}
