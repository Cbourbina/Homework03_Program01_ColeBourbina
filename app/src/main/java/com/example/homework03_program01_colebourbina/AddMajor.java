package com.example.homework03_program01_colebourbina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddMajor extends AppCompatActivity {

    EditText et_j_addMajor_name;
    EditText et_j_addMajor_prefix;
    EditText et_j_addMajor_id;
    Button btn_j_addMajor_back;
    Button btn_j_addMajor_addMajor;

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
        et_j_addMajor_id = findViewById(R.id.et_v_addMajor_id);
        btn_j_addMajor_back = findViewById(R.id.btn_v_addMajor_back);
        btn_j_addMajor_addMajor = findViewById(R.id.btn_v_addMajor_addMajor);
        intent_main = new Intent(AddMajor.this, MainActivity.class);

        backButtonClickEvent();
    }

    private void backButtonClickEvent() {
        btn_j_addMajor_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_main);
            }
        });
    }
}