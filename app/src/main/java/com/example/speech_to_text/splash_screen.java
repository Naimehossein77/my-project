package com.example.speech_to_text;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class splash_screen extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

 progressBar = (ProgressBar) findViewById(R.id.progressbarid);

 Thread thread = new Thread(new Runnable() {
     @Override
     public void run() {
         doWork();
         startApp();
     }
 });
thread.start();
    }

    public void doWork(){
        for(progress=1; progress<=100;progress = progress +1 ) {
            try {
                Thread.sleep(20);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }
public void startApp()
{
    Intent intent = new Intent(splash_screen.this,MainActivity.class);
    startActivity(intent);
    finish();
}
    }
