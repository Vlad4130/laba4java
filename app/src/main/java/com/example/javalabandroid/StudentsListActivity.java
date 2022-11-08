package com.example.javalabandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StudentsListActivity extends AppCompatActivity {
    public static final String GROUP_NUMBER = "groupnumber";


        private int seconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        setContentView(R.layout.activity_main);

        runTimer();

        Intent intent = getIntent();
        String grpNumber = intent.getStringExtra(GROUP_NUMBER);
        String txtStudent = "";
        for (Student s: Student.getStudents(grpNumber)){
            txtStudent += s.getName()+"\n";
        }
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(txtStudent);

        textSize = textView.getTextSize();
        if(savedInstanceState!=null)
        {
            textSize = savedInstanceState.getFloat("textSize");
            seconds = savedInstanceState.getInt("seconds");
        }
        runTimer();
    }
    public void onSendBtnClick(View view){
        TextView textView = (TextView) findViewById(R.id.text);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,textView.getText().toString());
        intent.putExtra(Intent.EXTRA_SUBJECT,"Список студентів");
        startActivity(intent);
    }
    public void onPlusBtnClick(View view){
        textSize = textSize * 1.1f;
        TextView textView = findViewById(R.id.text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putFloat("textSize",textSize);
        outState.putInt("seconds",seconds);
    }
    private float textSize = 0;
    @Override
    protected void onStart(){
        super.onStart();
        isRunning = true;
    }
    @Override
    protected void onStop(){
        super.onStop();
        isRunning = false;
    }
    private Boolean isRunning = true;
    private void runTimer(){
        final TextView timeView = (TextView) findViewById(androidx.core.R.id.time);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if(isRunning) {
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

}