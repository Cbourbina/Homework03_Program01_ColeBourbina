package com.example.homework03_program01_colebourbina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AddStudent extends AppCompatActivity {

    EditText et_j_addStudent_fname;
    EditText et_j_addStudent_lname;
    EditText et_j_addStudent_username;
    EditText et_j_addStudent_email;
    EditText et_j_addStudent_age;
    EditText et_j_addStudent_gpa;
    Spinner sp_j_addStudent_major;
    Button btn_j_addStudent_back;
    Button btn_j_addStudent_addStudent;
    Intent intent_main;
    ArrayList<String> listOfMajorNames;
    ArrayList<Major> listOfMajors;
    ArrayList<Student> listOfStudents;
    TextView tv_j_addStudent_error;
    DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        et_j_addStudent_fname = findViewById(R.id.et_v_addStudent_fname);
        et_j_addStudent_lname = findViewById(R.id.et_v_addStudent_lname);
        et_j_addStudent_username = findViewById(R.id.et_v_addStudent_username);
        et_j_addStudent_email = findViewById(R.id.et_v_addStudent_email);
        et_j_addStudent_age = findViewById(R.id.et_v_addStudent_age);
        et_j_addStudent_gpa = findViewById(R.id.et_v_addStudent_gpa);
        sp_j_addStudent_major = findViewById(R.id.sp_v_addStudent_major);
        btn_j_addStudent_back = findViewById(R.id.btn_v_addStudent_back);
        btn_j_addStudent_addStudent = findViewById(R.id.btn_v_addStudent_addStudent);
        tv_j_addStudent_error = findViewById(R.id.tv_v_addStudent_error);

        intent_main = new Intent(AddStudent.this, MainActivity.class);
        dbHelper = new DataBaseHelper(this);
        listOfMajorNames = dbHelper.getAllMajorNames();
        listOfMajors = dbHelper.getAllMajors();
        listOfStudents = dbHelper.getAllStudents();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listOfMajorNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_j_addStudent_major.setAdapter(adapter);

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
                int ageNum = -1;
                float gpaNum = -1;
                tv_j_addStudent_error.setVisibility(View.INVISIBLE);
                String fname = et_j_addStudent_fname.getText().toString().trim();
                String lname = et_j_addStudent_lname.getText().toString().trim();
                String username = et_j_addStudent_username.getText().toString().trim();
                String email = et_j_addStudent_email.getText().toString().trim();
                String age = et_j_addStudent_age.getText().toString().trim();
                String gpa = et_j_addStudent_gpa.getText().toString().trim();
                String majorName = sp_j_addStudent_major.getSelectedItem().toString();
                if (fname.isEmpty() || lname.isEmpty() || username.isEmpty() || email.isEmpty() || age.isEmpty() || gpa.isEmpty()) {
                    displayErrorEmptyFields();
                    return;
                }
                else {
                    ageNum = Integer.parseInt(age);
                    gpaNum = Float.parseFloat(gpa);
                }
                if (ageNum <= 0) {
                    displayErrorInvalidNumber();
                }
                else if (gpaNum < 0 || gpaNum > 4) {
                    displayErrorInvalidNumber();
                }
                else if (!isUsernameAvailable(username)) {
                    displayErrorInvalidUsername();
                }
                else {
                    Major major = getMajorGivenName(majorName);
                    dbHelper.addStudent(fname, lname, username, email, ageNum, gpaNum, major.getId());
                    clearFields();
                }
            }
        });
    }

    private void displayErrorEmptyFields() {
        tv_j_addStudent_error.setVisibility(View.VISIBLE);
        String errorMessage = "Error. Please fill out all fields.";
        tv_j_addStudent_error.setText(errorMessage);
    }

    private void displayErrorInvalidNumber() {
        tv_j_addStudent_error.setVisibility(View.VISIBLE);
        String errorMessage = "Error. Please enter a valid number.";
        tv_j_addStudent_error.setText(errorMessage);
    }

    private void displayErrorInvalidUsername() {
        tv_j_addStudent_error.setVisibility(View.VISIBLE);
        String errorMessage = "Error. Username already in use.";
        tv_j_addStudent_error.setText(errorMessage);
    }

    private Major getMajorGivenName(String majorName) {
        for (int i = 0; i < listOfMajors.size(); i++) {
            if (listOfMajors.get(i).getName().equals(majorName)) {
                return listOfMajors.get(i);
            }
        }
        return null;
    }

    private void clearFields() {
        et_j_addStudent_username.setText("");
        et_j_addStudent_fname.setText("");
        et_j_addStudent_lname.setText("");
        et_j_addStudent_age.setText("");
        et_j_addStudent_email.setText("");
        et_j_addStudent_age.setText("");
        et_j_addStudent_gpa.setText("");
    }

    private boolean isUsernameAvailable(String username) {
        for (int i = 0; i < listOfStudents.size(); i++) {
            if (listOfStudents.get(i).getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
}