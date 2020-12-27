package com.example.speech_to_text;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Builder alert_dialog;
    private Button button;
    private TextView circle;
    private Intent intent;
    private ProgressBar progressBar;
    private SpeechRecognizer speechRecognizer;
    private Spinner spinner;
    private TextToSpeech textToSpeech;
    private TextView textView;
    
    
    String begintext = "Speak Now";
    String buttontext = "Start";
    String change = "Change"; 
    String hint = "Your Text will appear here...";  
    String language = "en";
    String[] languagename;  
    String rmstext = "Listening...";
    String value;
    
    
   
    int xx = -2;

    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.zoomin);
        final Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.zoomout);
        languagename = getResources().getStringArray(R.array.language_name);
        spinner = (Spinner) findViewById(R.id.spinnerid);
        spinner.setAdapter(new ArrayAdapter(this, R.layout.sample_view, R.id.sampleid, this.languagename));
        button = (Button) findViewById(R.id.buttonid);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                MainActivity mainActivity = MainActivity.this;
                mainActivity.value = mainActivity.languagename[position];
                String e = "English";
                String u = "Urdu";
                String f = "French";
                String h = "Hindi";
                MainActivity mainActivity2;
                if ( value.equals("Bangla")) {
                     textView.setText("");
                    begintext = getResources().getString(R.string.bangla_begin);
                     
                     rmstext =  getResources().getString(R.string.bangla_rms);
                     
                     buttontext =  getResources().getString(R.string.bangla_button);
                     
                     change =  getResources().getString(R.string.bangla_change);
                     
                     hint =  getResources().getString(R.string.bangla_hint);
                     
                     button = (Button)  findViewById(R.id.buttonid);
                     button.setText( buttontext);
                     textView.setHint( hint);
                     
                     language =  getResources().getString(R.string.bangla_language);
                } else if ( value.equals(e)) {
                    textView.setText("");
                     begintext =  getResources().getString(R.string.english_begin);
                     
                     rmstext =  getResources().getString(R.string.english_rms);
                     
                     buttontext =  getResources().getString(R.string.english_button);
                     
                     change =  getResources().getString(R.string.english_change);
                     
                     hint =  getResources().getString(R.string.english_hint);
                     
                     button = (Button)  findViewById(R.id.buttonid);
                     button.setText( buttontext);
                     textView.setHint( hint);
                     
                     language =  getResources().getString(R.string.english_language);
                } else if ( value.equals(h)) {
                    textView.setText("");
                     begintext =  getResources().getString(R.string.hindi_begin);
                     
                     rmstext =  getResources().getString(R.string.hindi_rms);
                     
                     buttontext =  getResources().getString(R.string.hindi_button);
                     
                     change =  getResources().getString(R.string.hindi_change);
                     
                     hint =  getResources().getString(R.string.hindi_hint);
                     
                     button = (Button)  findViewById(R.id.buttonid);
                     button.setText( buttontext);
                     textView.setHint( hint);
                     
                     language =  getResources().getString(R.string.hindi_language);
                } else if ( value.equals(f)) {
                    textView.setText("");
                     begintext =  getResources().getString(R.string.french_begin);
                     
                     rmstext =  getResources().getString(R.string.french_rms);
                     
                     buttontext =  getResources().getString(R.string.french_button);
                     
                     change =  getResources().getString(R.string.french_change);
                     
                     hint =  getResources().getString(R.string.french_hint);
                     
                     button = (Button)  findViewById(R.id.buttonid);
                     button.setText( buttontext);
                     textView.setHint( hint);
                     
                     language =  getResources().getString(R.string.french_language);
                } else if ( value.equals(u)) {
                    textView.setText("");
                     begintext =  getResources().getString(R.string.urdu_begin);
                     
                     rmstext =  getResources().getString(R.string.urdu_rms);
                     
                     buttontext =  getResources().getString(R.string.urdu_button);
                     
                     change =  getResources().getString(R.string.urdu_change);
                     
                     hint =  getResources().getString(R.string.urdu_hint);
                     
                     button = (Button)  findViewById(R.id.buttonid);
                     button.setText( buttontext);
                     textView.setHint( hint);
                     
                     language =  getResources().getString(R.string.urdu_language);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"}, 0);
        textView = (TextView) findViewById(R.id.textviewid);
        circle = (TextView) findViewById(R.id.circleid);
        
        
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.intent = intent;
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        SpeechRecognizer createSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        this.speechRecognizer = createSpeechRecognizer;
        createSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            public void onReadyForSpeech(Bundle params) {
            }

            public void onBeginningOfSpeech() {
                textView.setText(begintext);
            }

            public void onRmsChanged(float rmsdB) {
                int x = (int) rmsdB;
                textView.setText(rmstext);
                if (xx < x) {
                    if (x != -2) {
                        findViewById(R.id.circleid).startAnimation(animation);
                    }
                } else if (x != -2) {
                    findViewById(R.id.circleid).startAnimation(animation1);
                }
                xx = x;
            }

            public void onBufferReceived(byte[] buffer) {
            }

            public void onEndOfSpeech() {
                findViewById(R.id.circleid).startAnimation(animation1);
            }

            public void onError(int error) {
                textView.setText("");
                findViewById(R.id.circleid).startAnimation(animation1);
            }

            public void onResults(Bundle results) {
                 findViewById(R.id.circleid).startAnimation(animation1);
                // speechRecognizer;
                ArrayList<String> matches = results.getStringArrayList("results_recognition");
                String string = "";
                 textView.setText("");
                if (matches != null) {
                    string = (String) matches.get(0);
                     textView.setText(string);

                }
            }

            public void onPartialResults(Bundle partialResults) {
            }

            public void onEvent(int eventType, Bundle params) {
            }
        });
    }

    public void startButton(View view) {
        String str = "android.speech.extra.LANGUAGE_PREFERENCE";
        try {
             intent.putExtra("android.speech.extra.LANGUAGE",  language);
             intent.putExtra(str, Locale.getDefault());
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         intent.putExtra(str, Locale.getDefault());
         speechRecognizer.startListening( intent);
         textView.setText("");
         textView.setHint( hint);
    }


    public void onBackPressed() {
        onClick(null);
    }

    public void onClick(View v) {
        Builder builder = new Builder(this);
         alert_dialog = builder;
        builder.setTitle(R.string.title_text);
         alert_dialog.setMessage(R.string.msg_text);
         alert_dialog.setIcon(R.drawable.index);
         alert_dialog.setPositiveButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                 finish();
            }
        });
         alert_dialog.setNegativeButton("No", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
         alert_dialog.create().show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.feedbackid) {
            startActivity(new Intent(getApplicationContext(), Feedbackactivity.class));
        } else if (item.getItemId() == R.id.aboutusid) {
            startActivity(new Intent(getApplicationContext(), about.class));
        } else if(item.getItemId()==R.id.helpid){
            startActivity(new Intent(getApplicationContext(),helpactivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
