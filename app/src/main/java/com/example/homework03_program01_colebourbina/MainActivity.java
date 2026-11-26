package com.example.homework03_program01_colebourbina;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv_j_listOfStudents;
    Button btn_j_addStudent;
    Button btn_j_addMajor;
    Intent intent_j_addStudent;
    Intent intent_j_addMajor;
    Intent intent_j_studentDetails;
    StudentListAdapter slAdapter;
    DataBaseHelper dbHelper;
    Bundle selectedStudent = new Bundle();

    private ArrayList<Student> listOfStudents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lv_j_listOfStudents = findViewById(R.id.lv_v_listOfStudents);
        btn_j_addStudent = findViewById(R.id.btn_v_addStudent);
        btn_j_addMajor = findViewById(R.id.btn_v_addMajor);
        listOfStudents = new ArrayList<>();

        intent_j_addStudent = new Intent(MainActivity.this, AddStudent.class);
        intent_j_addMajor = new Intent(MainActivity.this, AddMajor.class);
        intent_j_studentDetails = new Intent(MainActivity.this, studentDetails.class);

        dbHelper = new DataBaseHelper(this);
        dbHelper.initAllTables();
        listOfStudents = dbHelper.getAllStudents();
        Log.d("Major Record Count: ", Integer.toString(dbHelper.countRecordsFromTable("majors")));
        Log.d("Student Record Count: ", Integer.toString(dbHelper.countRecordsFromTable("students")));
        //Major test = dbHelper.getMajorGivenId(2);
        //Log.d("Major With ID #2", test.getName());
        slAdapter = new StudentListAdapter(this, listOfStudents);
        lv_j_listOfStudents.setAdapter(slAdapter);

        addStudentButtonClickEvent();
        addMajorButtonClickEvent();
        setOnClickListenersForListView();
        Log.d("Student Count:", Integer.toString(listOfStudents.size()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listOfStudents.clear();
        listOfStudents.addAll(dbHelper.getAllStudents());
        slAdapter.notifyDataSetChanged();
    }

    private void addStudentButtonClickEvent() {
        btn_j_addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_j_addStudent);
            }
        });
    }

    private void addMajorButtonClickEvent() {
        btn_j_addMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_j_addMajor);
            }
        });
    }

    private void setOnClickListenersForListView() {
        lv_j_listOfStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedStudent.putString("username", listOfStudents.get(position).getUsername());
                selectedStudent.putString("fname", listOfStudents.get(position).getfName());
                selectedStudent.putString("lname", listOfStudents.get(position).getlName());
                selectedStudent.putString("email", listOfStudents.get(position).getEmail());
                selectedStudent.putInt("age", listOfStudents.get(position).getAge());
                selectedStudent.putFloat("gpa", listOfStudents.get(position).getGpa());
                selectedStudent.putInt("majorId", listOfStudents.get(position).getMajor().getId());

                intent_j_studentDetails.putExtras(selectedStudent);
                startActivity(intent_j_studentDetails);
            }
        });

        lv_j_listOfStudents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String username = listOfStudents.get(position).getUsername();
                dbHelper.deleteStudentByUsername(username);
                listOfStudents.clear();
                listOfStudents.addAll(dbHelper.getAllStudents());
                slAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}