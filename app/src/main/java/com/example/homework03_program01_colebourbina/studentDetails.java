package com.example.homework03_program01_colebourbina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class studentDetails extends AppCompatActivity {

    TextView tv_j_studentDetails_username;
    TextView tv_j_studentDetails_fname;
    TextView tv_j_studentDetails_lname;
    TextView tv_j_studentDetails_email;
    TextView tv_j_studentDetails_age;
    TextView tv_j_studentDetails_gpa;
    TextView tv_j_studentDetails_major;
    Button btn_j_studentDetails_edit;
    Button btn_j_studentDetails_back;
    Intent intent_main;
    Intent intent_editStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_student_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv_j_studentDetails_username = findViewById(R.id.tv_v_studentDetails_username);
        tv_j_studentDetails_fname = findViewById(R.id.tv_v_studentDetails_fname);
        tv_j_studentDetails_lname = findViewById(R.id.tv_v_studentDetails_lname);
        tv_j_studentDetails_email = findViewById(R.id.tv_v_studentDetails_email);
        tv_j_studentDetails_age = findViewById(R.id.tv_v_studentDetails_age);
        tv_j_studentDetails_gpa = findViewById(R.id.tv_v_studentDetails_gpa);
        tv_j_studentDetails_major = findViewById(R.id.tv_v_studentDetails_major);
        btn_j_studentDetails_edit = findViewById(R.id.btn_v_studentDetails_edit);
        btn_j_studentDetails_back = findViewById(R.id.btn_v_studentDetails_back);
        intent_main = new Intent(studentDetails.this, MainActivity.class);
        intent_editStudent = new Intent(studentDetails.this, editStudent.class);
    }

    private void backButtonClickEvent() {
        btn_j_studentDetails_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_main);
            }
        });
    }

    private void editStudentButtonClickEvent() {
        btn_j_studentDetails_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_main);
            }
        });
    }
}