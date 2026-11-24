package com.example.homework03_program01_colebourbina;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Student> listOfStudents;

    public StudentListAdapter(Context c, ArrayList<Student> list) {
        context = c;
        listOfStudents = list;
    }

    @Override
    public int getCount() {
        return listOfStudents.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfStudents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.studentlistviewcell, null);
        }

        TextView studentUsername = convertView.findViewById(R.id.tv_v_cell_username);
        TextView studentFname = convertView.findViewById(R.id.tv_v_cell_fname);
        TextView studentLname = convertView.findViewById(R.id.tv_v_cell_lname);
        TextView studentEmail = convertView.findViewById(R.id.tv_v_cell_email);
        TextView studentAge = convertView.findViewById(R.id.tv_v_cell_age);
        TextView studentGpa = convertView.findViewById(R.id.tv_v_cell_gpa);
        TextView studentMajor = convertView.findViewById(R.id.tv_v_cell_major);

        Student student = listOfStudents.get(position);

        studentUsername.setText(student.getUsername());
        studentFname.setText(student.getfName());
        studentLname.setText(student.getlName());
        studentEmail.setText(student.getEmail());
        studentAge.setText(String.valueOf(student.getAge()));
        studentGpa.setText(String.valueOf(student.getGpa()));
        studentMajor.setText(student.getMajor().getName());

        return convertView;
    }
}
