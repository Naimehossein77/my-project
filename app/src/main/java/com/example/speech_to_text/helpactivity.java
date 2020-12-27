package com.example.speech_to_text;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class helpactivity extends AppCompatActivity  {

    PDFView help_pdf;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Toast.makeText(getApplicationContext(),"pdf clicked",Toast.LENGTH_LONG).show();
        help_pdf=(PDFView)findViewById(R.id.helppdfid);
        help_pdf.fromAsset("help.pdf").load();



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


