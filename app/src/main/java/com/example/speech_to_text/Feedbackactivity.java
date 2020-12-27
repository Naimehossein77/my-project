package com.example.speech_to_text;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Feedbackactivity extends AppCompatActivity implements OnClickListener {
    private Button clearbutton;
    private EditText efeedback;
    private EditText ename;
    private Button sendbutton;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbackactivity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sendbutton = (Button) findViewById(R.id.sendbuttonid);
        ename = (EditText) findViewById(R.id.nameid);
        efeedback = (EditText) findViewById(R.id.messageid);
        sendbutton.setOnClickListener(this);
        Button button = (Button) findViewById(R.id.clearbuttonid);
        clearbutton = button;
        button.setOnClickListener(this);
    }

    public void onClick(View v) {
        String str = "";
        try {
            if (v.getId() == R.id.sendbuttonid) {
                Toast.makeText(getApplicationContext(), "Tap gmail then tap send button", Toast.LENGTH_LONG).show();
                str = this.ename.getText().toString();
                String feedback = this.efeedback.getText().toString();
                if (v.getId() == R.id.sendbuttonid) {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/email");
                    intent.putExtra("android.intent.extra.EMAIL", new String[]{"naimehossein77@gmail.com", "kazinubilanushintajin@gmail.com"});
                    intent.putExtra("android.intent.extra.SUBJECT", "Feedback From Speech to text App");
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Hello i am ");
                    stringBuilder.append(str);
                    stringBuilder.append(",\n");
                    stringBuilder.append(feedback);
                    intent.putExtra("android.intent.extra.TEXT", stringBuilder.toString());
                    startActivity(Intent.createChooser(intent, "Feedback with "));
                }
                return;
            }
            this.ename.setText(str);
            this.efeedback.setText(str);
        } catch (Exception e) {
            Context applicationContext = getApplicationContext();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Exception");
            stringBuilder2.append(e);
            Toast.makeText(applicationContext, stringBuilder2.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        finish();
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
