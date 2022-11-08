package com.example.javalabandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static final String GROUP_NUMBER = "groupnumber";
private int seconds = 0;
private Boolean isRunning = true;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onBtnClick(View view){
 /*   CharSequence text = "Ви натиснули на кнопку";
    int duration = Toast.LENGTH_LONG;
    Toast toast = Toast.makeText(this,text,duration);
    toast.show();*/

        /*Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String grpNumb = (String) spinner.getSelectedItem();

        String txtStudents = "";
        for (Student s: Student.getStudents(grpNumb)){
            txtStudents+=s.getName() + "\n";
        }
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(txtStudents);*/

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String grpNumb = (String) spinner.getSelectedItem();


        Intent intent = new Intent(this, StudentsListActivity.class);
        intent.putExtra(StudentsListActivity.GROUP_NUMBER, grpNumb);
        startActivity(intent);

    }
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