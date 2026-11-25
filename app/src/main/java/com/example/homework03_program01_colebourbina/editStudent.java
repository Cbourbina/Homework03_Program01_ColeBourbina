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

public class editStudent extends AppCompatActivity {

    TextView tv_j_editStudent_username;
    EditText et_j_editStudent_fname;
    EditText et_j_editStudent_lname;
    EditText et_j_editStudent_email;
    EditText et_j_editStudent_age;
    EditText et_j_editStudent_gpa;
    Spinner sp_j_editStudent_major;
    Intent intent_main;
    Button btn_j_editStudent_back;
    Button btn_j_editStudent_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_edit_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv_j_editStudent_username = findViewById(R.id.tv_v_editStudent_username);
        et_j_editStudent_fname = findViewById(R.id.et_v_editStudent_fname);
        et_j_editStudent_lname = findViewById(R.id.et_v_editStudent_lname);
        et_j_editStudent_email = findViewById(R.id.et_v_editStudent_email);
        et_j_editStudent_age = findViewById(R.id.et_v_editStudent_age);
        et_j_editStudent_gpa = findViewById(R.id.et_v_editStudent_gpa);
        sp_j_editStudent_major = findViewById(R.id.sp_v_editStudent_major);
        intent_main = new Intent(editStudent.this, MainActivity.class);
        btn_j_editStudent_back = findViewById(R.id.btn_v_editStudent_back);
        btn_j_editStudent_update = findViewById(R.id.btn_v_editStudent_update);

        backButtonClickEvent();
        updateButtonClickEvent();
    }

    private void backButtonClickEvent() {
        btn_j_editStudent_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_main);
            }
        });
    }

    private void updateButtonClickEvent() {
        btn_j_editStudent_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save edits
            }
        });
    }
}