package com.example.homework03_program01_colebourbina;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SearchStudent extends AppCompatActivity {

    EditText et_j_searchStudent_username;
    EditText et_j_searchStudent_fname;
    EditText et_j_searchStudent_lname;
    CheckBox cb_j_searchStudent_major;
    Spinner sp_j_searchStudent_major;
    EditText et_j_searchStudent_gpaMin;
    EditText et_j_searchStudent_gpaMax;
    ListView lv_j_searchStudent_listOfStudents;
    Button btn_j_searchStudent_search;
    Button btn_j_searchStudent_back;
    ArrayList<Student> filteredListOfStudents;
    StudentListAdapter slAdapter;
    ArrayList<Major> listOfMajors;
    ArrayList<String> listOfMajorNames;
    DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_search_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DataBaseHelper(this);
        et_j_searchStudent_username = findViewById(R.id.et_v_searchStudent_username);
        et_j_searchStudent_fname = findViewById(R.id.et_v_searchStudent_fname);
        et_j_searchStudent_lname = findViewById(R.id.et_v_searchStudent_lname);
        cb_j_searchStudent_major = findViewById(R.id.cb_v_searchStudent_major);
        sp_j_searchStudent_major = findViewById(R.id.sp_v_searchStudent_major);
        et_j_searchStudent_gpaMin = findViewById(R.id.et_v_searchStudent_gpaMin);
        et_j_searchStudent_gpaMax = findViewById(R.id.et_v_searchStudent_gpaMax);
        lv_j_searchStudent_listOfStudents = findViewById(R.id.lv_v_searchStudent_listOfStudents);
        btn_j_searchStudent_search = findViewById(R.id.btn_v_searchStudent_search);
        btn_j_searchStudent_back = findViewById(R.id.btn_v_searchStudent_back);
        filteredListOfStudents = new ArrayList<>();
        listOfMajors = dbHelper.getAllMajors();
        listOfMajorNames = dbHelper.getAllMajorNames();
        slAdapter = new StudentListAdapter(this, filteredListOfStudents);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listOfMajorNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_j_searchStudent_major.setAdapter(adapter);
        lv_j_searchStudent_listOfStudents.setAdapter(slAdapter);
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        btn_j_searchStudent_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_j_searchStudent_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fnameFilter = et_j_searchStudent_fname.getText().toString().trim();
                String lnameFilter = et_j_searchStudent_lname.getText().toString().trim();
                String usernameFilter = et_j_searchStudent_username.getText().toString().trim();
                String gpaMinFilter = et_j_searchStudent_gpaMin.getText().toString().trim();
                String gpaMaxFilter = et_j_searchStudent_gpaMax.getText().toString().trim();
                String major;
                if (cb_j_searchStudent_major.isChecked()) {
                    major = String.valueOf(getMajorGivenName(sp_j_searchStudent_major.getSelectedItem().toString()).getId());
                }
                else {
                    major = "";
                }
                filteredListOfStudents.clear();
                filteredListOfStudents.addAll(dbHelper.getFilteredStudents(usernameFilter, fnameFilter, lnameFilter, major, gpaMinFilter, gpaMaxFilter));
                slAdapter.notifyDataSetChanged();
            }
        });
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