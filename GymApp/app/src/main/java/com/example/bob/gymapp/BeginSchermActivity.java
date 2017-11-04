package com.example.bob.gymapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class BeginSchermActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUESTCODE = 2;
    Button btnPull, btnPush, btnLegs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_scherm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnPull = (Button) findViewById(R.id.pull);
        btnPull.setOnClickListener(BeginSchermActivity.this);

        btnPush = (Button) findViewById(R.id.push);
        btnPush.setOnClickListener(BeginSchermActivity.this);

        btnLegs = (Button) findViewById(R.id.legs);
        btnLegs.setOnClickListener(BeginSchermActivity.this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.pull:

                Intent intent = new Intent(BeginSchermActivity.this, MainActivity.class);
                intent.putExtra("STRING_I_NEED", "PULL");
                startActivityForResult(intent, REQUESTCODE);
                break;

            case R.id.push:
                Intent intent2 = new Intent(BeginSchermActivity.this, MainActivity.class);
                intent2.putExtra("STRING_I_NEED", "PUSH");
                startActivityForResult(intent2, REQUESTCODE);
                break;

            case R.id.legs:
                Intent intent3 = new Intent(BeginSchermActivity.this, MainActivity.class);
                intent3.putExtra("STRING_I_NEED", "LEGS");
                startActivityForResult(intent3, REQUESTCODE);

                break;

            default:
                break;
        }
    }
}
