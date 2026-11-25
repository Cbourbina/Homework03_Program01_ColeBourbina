package com.example.homework03_program01_colebourbina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AddMajor extends AppCompatActivity {

    EditText et_j_addMajor_name;
    EditText et_j_addMajor_prefix;
    Button btn_j_addMajor_back;
    Button btn_j_addMajor_addMajor;
    TextView tv_j_addMajor_error;
    DataBaseHelper dbHelper;
    ArrayList<Major> listOfMajors;

    Intent intent_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_add_major);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        et_j_addMajor_name = findViewById(R.id.et_v_addMajor_name);
        et_j_addMajor_prefix = findViewById(R.id.et_v_addMajor_prefix);
        btn_j_addMajor_back = findViewById(R.id.btn_v_addMajor_back);
        btn_j_addMajor_addMajor = findViewById(R.id.btn_v_addMajor_addMajor);
        intent_main = new Intent(AddMajor.this, MainActivity.class);
        tv_j_addMajor_error = findViewById(R.id.tv_v_addMajor_error);
        dbHelper = new DataBaseHelper(this);
        listOfMajors = dbHelper.getAllMajors();

        backButtonClickEvent();
        addMajorButtonClickEvent();
    }

    private void backButtonClickEvent() {
        btn_j_addMajor_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_main);
            }
        });
    }

    private void addMajorButtonClickEvent() {
        btn_j_addMajor_addMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_j_addMajor_error.setVisibility(View.VISIBLE);
                String name = et_j_addMajor_name.getText().toString().trim();
                String prefix = et_j_addMajor_prefix.getText().toString().trim();
                if (name.isEmpty() || prefix.isEmpty()) {
                    displayErrorEmptyFields();
                }
                else if (!isMajorNameAvailable(name)) {
                    displayErrorInvalidName();
                }
                else if (!isMajorPrefixAvailable(prefix)) {
                    displayErrorInvalidPrefix();
                }
                else {
                    dbHelper.addMajor(name, prefix);
                    clearFields();
                }
            }
        });
    }

    private void displayErrorEmptyFields() {
        tv_j_addMajor_error.setVisibility(View.VISIBLE);
        String errorMessage = "Error. Please fill out all fields.";
        tv_j_addMajor_error.setText(errorMessage);
    }

    private void displayErrorInvalidName() {
        tv_j_addMajor_error.setVisibility(View.VISIBLE);
        String errorMessage = "Error. Major name already in use.";
        tv_j_addMajor_error.setText(errorMessage);
    }

    private void displayErrorInvalidPrefix() {
        tv_j_addMajor_error.setVisibility(View.VISIBLE);
        String errorMessage = "Error. Major prefix already in use.";
        tv_j_addMajor_error.setText(errorMessage);
    }

    private boolean isMajorNameAvailable(String majorName) {
        for (int i = 0; i < listOfMajors.size(); i++) {
            if (listOfMajors.get(i).getName().equals(majorName)) {
                return false;
            }
        }
        return true;
    }

    private boolean isMajorPrefixAvailable(String prefix) {
        for (int i = 0; i < listOfMajors.size(); i++) {
            if (listOfMajors.get(i).getPrefix().equals(prefix)) {
                return false;
            }
        }
        return true;
    }

    private void clearFields() {
        et_j_addMajor_name.setText("");
        et_j_addMajor_prefix.setText("");
    }
}