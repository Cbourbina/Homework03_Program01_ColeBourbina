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

public class editStudent extends AppCompatActivity {

    TextView tv_j_editStudent_username;
    TextView tv_j_editStudent_error;
    EditText et_j_editStudent_fname;
    EditText et_j_editStudent_lname;
    EditText et_j_editStudent_email;
    EditText et_j_editStudent_age;
    EditText et_j_editStudent_gpa;
    Spinner sp_j_editStudent_major;
    Intent intent_studentDetails;
    Button btn_j_editStudent_back;
    Button btn_j_editStudent_update;
    Bundle selectedStudent;
    String studentUsername;
    String studentFname;
    String studentLname;
    String studentEmail;
    int studentAge;
    float studentGpa;
    int studentMajorId;
    int studentMajorIndex;
    ArrayList<Major> listOfMajors;
    ArrayList<String> listOfMajorNames;
    DataBaseHelper dbHelper;

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

        dbHelper = new DataBaseHelper(this);
        selectedStudent  = getIntent().getExtras();
        tv_j_editStudent_username = findViewById(R.id.tv_v_editStudent_username);
        tv_j_editStudent_error = findViewById(R.id.tv_v_editStudent_error);
        et_j_editStudent_fname = findViewById(R.id.et_v_editStudent_fname);
        et_j_editStudent_lname = findViewById(R.id.et_v_editStudent_lname);
        et_j_editStudent_email = findViewById(R.id.et_v_editStudent_email);
        et_j_editStudent_age = findViewById(R.id.et_v_editStudent_age);
        et_j_editStudent_gpa = findViewById(R.id.et_v_editStudent_gpa);
        sp_j_editStudent_major = findViewById(R.id.sp_v_editStudent_major);
        intent_studentDetails = new Intent(editStudent.this, studentDetails.class);
        btn_j_editStudent_back = findViewById(R.id.btn_v_editStudent_back);
        btn_j_editStudent_update = findViewById(R.id.btn_v_editStudent_update);

        listOfMajors = dbHelper.getAllMajors();
        listOfMajorNames = dbHelper.getAllMajorNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listOfMajorNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_j_editStudent_major.setAdapter(adapter);

        if (selectedStudent != null)
        {
            studentUsername = selectedStudent.getString("username");
            studentFname = selectedStudent.getString("fname");
            studentLname = selectedStudent.getString("lname");
            studentEmail = selectedStudent.getString("email");
            studentAge = selectedStudent.getInt("age");
            studentGpa = selectedStudent.getFloat("gpa");
            studentMajorId = selectedStudent.getInt("majorId");
            studentMajorIndex = getMajorIndexPosition(dbHelper.getMajorGivenId(studentMajorId, false));

            tv_j_editStudent_username.setText(studentUsername);
            et_j_editStudent_fname.setText(studentFname);
            et_j_editStudent_lname.setText(studentLname);
            et_j_editStudent_email.setText(studentEmail);
            et_j_editStudent_age.setText(String.valueOf(studentAge));
            et_j_editStudent_gpa.setText(String.valueOf(studentGpa));
            sp_j_editStudent_major.setSelection(studentMajorIndex);

        }

        backButtonClickEvent();
        updateButtonClickEvent();
    }

    private void backButtonClickEvent() {
        btn_j_editStudent_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(intent_studentDetails);
            }
        });
    }

    public int getMajorIndexPosition(Major major) {
        for (int i = 0; i < listOfMajorNames.size(); i++)
        {
            if (listOfMajorNames.get(i).equals(major.getName())) {
                return i;
            }
        }
        return 0;
    }

    private void updateButtonClickEvent() {
        btn_j_editStudent_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ageNum = -1;
                float gpaNum = -1;
                tv_j_editStudent_error.setVisibility(View.INVISIBLE);
                String username = tv_j_editStudent_username.getText().toString();
                String fname = et_j_editStudent_fname.getText().toString().trim();
                String lname = et_j_editStudent_lname.getText().toString().trim();
                String email = et_j_editStudent_email.getText().toString().trim();
                String age = et_j_editStudent_age.getText().toString().trim();
                String gpa = et_j_editStudent_gpa.getText().toString().trim();
                String majorName = sp_j_editStudent_major.getSelectedItem().toString();
                if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || age.isEmpty() || gpa.isEmpty()) {
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
                else {
                    Major major = getMajorGivenName(majorName);
                    //dbHelper.addStudent(fname, lname, username, email, ageNum, gpaNum, major.getId());
                    dbHelper.updateStudent(username, fname,lname, email, age, gpa, String.valueOf(major.getId()));
                    finish();
                }
            }
        });
    }

    private void displayErrorEmptyFields() {
        tv_j_editStudent_error.setVisibility(View.VISIBLE);
        String errorMessage = "Error. Please fill out all fields.";
        tv_j_editStudent_error.setText(errorMessage);
    }

    private void displayErrorInvalidNumber() {
        tv_j_editStudent_error.setVisibility(View.VISIBLE);
        String errorMessage = "Error. Please enter a valid number.";
        tv_j_editStudent_error.setText(errorMessage);
    }

    private Major getMajorGivenName(String majorName) {
        for (int i = 0; i < listOfMajors.size(); i++) {
            if (listOfMajors.get(i).getName().equals(majorName)) {
                return listOfMajors.get(i);
            }
        }
        return null;
    }
}