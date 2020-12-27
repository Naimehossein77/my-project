package com.example.speech_to_text;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class about extends AppCompatActivity  {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //if (item.getItemId() != 16908332) {
       //     return super.onOptionsItemSelected(item);
       // }
        finish();
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


}


