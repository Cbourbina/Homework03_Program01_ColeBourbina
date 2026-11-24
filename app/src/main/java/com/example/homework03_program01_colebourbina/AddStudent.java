package com.example.homework03_program01_colebourbina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddStudent extends AppCompatActivity {

    EditText et_j_addStudent_fname;
    EditText et_j_addStudent_lname;
    EditText et_j_addStudent_username;
    EditText et_j_addStudent_email;
    Spinner sp_j_addStudent_age;
    Spinner sp_j_addStudent_gpa;
    Spinner sp_j_addStudent_major;
    Button btn_j_addStudent_back;
    Button btn_j_addStudent_addStudent;
    Intent intent_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        et_j_addStudent_fname = findViewById(R.id.et_v_addStudent_fname);
        et_j_addStudent_lname = findViewById(R.id.et_v_addStudent_lname);
        et_j_addStudent_username = findViewById(R.id.et_v_addStudent_username);
        et_j_addStudent_email = findViewById(R.id.et_v_addStudent_email);
        sp_j_addStudent_age = findViewById(R.id.sp_v_addStudent_age);
        sp_j_addStudent_gpa = findViewById(R.id.sp_v_addStudent_gpa);
        sp_j_addStudent_major = findViewById(R.id.sp_v_addStudent_major);
        btn_j_addStudent_back = findViewById(R.id.btn_v_addStudent_back);
        btn_j_addStudent_addStudent = findViewById(R.id.btn_v_addStudent_addStudent);

        intent_main = new Intent(AddStudent.this, MainActivity.class);

        backButtonClickEvent();
        addStudentButtonClickEvent();
    }

    private void backButtonClickEvent() {
        btn_j_addStudent_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_main);
            }
        });
    }

    private void addStudentButtonClickEvent() {
        btn_j_addStudent_addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}