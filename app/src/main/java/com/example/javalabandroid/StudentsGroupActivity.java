package com.example.javalabandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class StudentsGroupActivity extends AppCompatActivity {
public static final String GROUP_NUMBER = "groupnumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_group2);

        Intent intent = getIntent();
        String grpNumber = intent.getStringExtra(GROUP_NUMBER);
        Groups groups = Groups.getGroup(grpNumber);

        EditText txtGrpNumber = (EditText) findViewById(R.id.txtGrpNumber);
        txtGrpNumber.setText(groups.getNumber());

        EditText txtFacultyName = (EditText) findViewById(R.id.facultyEdit);
        txtFacultyName.setText(groups.getFacultyName());

        TextView txtimgGrp = (TextView) findViewById(R.id.txtimgGrp);
        txtimgGrp.setText(groups.getNumber());

        TextView txtimgFacult = (TextView) findViewById(R.id.txtimgFacult);
        txtimgFacult.setText(groups.getFacultyName());

        if (groups.getEducationLevel() == 0){
            ((RadioButton)findViewById(R.id.edu_level_bachelor)).setChecked(true);
        }
        else{
            ((RadioButton)findViewById(R.id.edu_level_master)).setChecked(true);
        }
        ((CheckBox)findViewById(R.id.contract_flg)).setChecked(groups.isContractExistsFlg());
        ((CheckBox)findViewById(R.id.privilege_flg)).setChecked(groups.isContractExistsFlg());
    }
    public void OkBtnClick(View view){
        String outString = "Группа " + ((TextView) findViewById(R.id.txtGrpNumber)).getText()+"\n";
        outString += "Факультет " + ((TextView) findViewById(R.id.facultyEdit)).getText()+"\n";
        if (((RadioButton)findViewById(R.id.edu_level_master)).isChecked()){
            outString += "Рівень освіти "+ ((TextView)findViewById(R.id.edu_level_master));
        }
        else{
            outString += "Рівень освіти"+((TextView)findViewById(R.id.edu_level_bachelor));
        }
        if (((CheckBox)findViewById(R.id.contract_flg)).isChecked()){
            outString += "Контрактники є \n";
        }
        else{
            outString += "Контрактників немає \n";
        }
        if (((CheckBox)findViewById(R.id.privilege_flg)).isChecked()){
            outString += "Бюджетники є \n";
        }
        else{
            outString += "Бюджетників немає \n";
        }
        Toast.makeText(this, outString, Toast.LENGTH_LONG).show();
    }
}