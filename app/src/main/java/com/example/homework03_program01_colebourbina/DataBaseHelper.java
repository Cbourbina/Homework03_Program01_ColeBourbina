package com.example.homework03_program01_colebourbina;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String database_name = "studentInfo";
    private static final String students_table_name = "students";
    private static final String majors_table_name = "majors";

    public DataBaseHelper(Context c)
    {
        super(c, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + students_table_name + " (username text primary key not null, fname text, lname text, email text, age integer, gpa real, major_id integer, foreign key(major_id) references " + majors_table_name + "(major_id));");
        db.execSQL("CREATE TABLE " + majors_table_name + " (major_id integer primary key autoincrement not null, major_name text, major_prefix text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + students_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + majors_table_name + ";");

        onCreate(db);
    }

    public String getStudents_table_name() {
        return students_table_name;
    }

    public String getMajors_table_name() {
        return majors_table_name;
    }

    public void initAllTables() {
        initMajors();
        initStudents();
    }

    private void initStudents() {
        if (countRecordsFromTable(students_table_name) == 0) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("INSERT INTO " + students_table_name + " (username, fname, lname, email, age, gpa, major_id) VALUES ('Jsmith', 'John', 'Smith', 'jsmith@university.edu', 23, 3.2, 1);");
            db.execSQL("INSERT INTO " + students_table_name + " (username, fname, lname, email, age, gpa, major_id) VALUES ('Egarcia', 'Elena', 'Garcia', 'egarcia@gmail.com', 20, 3.8, 2);");
            db.execSQL("INSERT INTO " + students_table_name + " (username, fname, lname, email, age, gpa, major_id) VALUES ('Mchen', 'Marcus', 'Chen', 'mchen@outlook.com', 21, 3.6, 1);");
            db.execSQL("INSERT INTO " + students_table_name + " (username, fname, lname, email, age, gpa, major_id) VALUES ('Spatel', 'Sophia', 'Patel', 'spatel@university.edu', 19, 3.9, 3);");
            db.execSQL("INSERT INTO " + students_table_name + " (username, fname, lname, email, age, gpa, major_id) VALUES ('Dwilson', 'David', 'Wilson', 'dwilson@yahoo.com', 22, 2.9, 4);");
            db.execSQL("INSERT INTO " + students_table_name + " (username, fname, lname, email, age, gpa, major_id) VALUES ('Ajohnson', 'Ava', 'Johnson', 'ajohnson@example.com', 21, 3.4, 3);");
            db.execSQL("INSERT INTO " + students_table_name + " (username, fname, lname, email, age, gpa, major_id) VALUES ('Lkim', 'Liam', 'Kim', 'lkim@gmail.com', 20, 3.7, 2);");
            db.execSQL("INSERT INTO " + students_table_name + " (username, fname, lname, email, age, gpa, major_id) VALUES ('Nbrown', 'Maya', 'Brown', 'mbrown@university.edu', 22, 3.1, 4);");
            db.execSQL("INSERT INTO " + students_table_name + " (username, fname, lname, email, age, gpa, major_id) VALUES ('Ctaylor', 'Caleb', 'Taylor', 'ctaylor@outlook.com', 24, 3.5, 1);");
            db.execSQL("INSERT INTO " + students_table_name + " (username, fname, lname, email, age, gpa, major_id) VALUES ('Olee', 'Olivia', 'Lee', 'olee@example.org', 19, 3.8, 3);");
            db.close();
        }
    }

    private void initMajors() {
        if (countRecordsFromTable(majors_table_name) == 0) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("INSERT INTO " + majors_table_name + " (major_name, major_prefix) VALUES ('Computer Science','CIS')");
            db.execSQL("INSERT INTO " + majors_table_name + " (major_name, major_prefix) VALUES ('Mechanical Engineering', 'ME');");
            db.execSQL("INSERT INTO " + majors_table_name + " (major_name, major_prefix) VALUES ('Biology', 'BIO');");
            db.execSQL("INSERT INTO " + majors_table_name + " (major_name, major_prefix) VALUES ('Business Administration', 'BUS');");
            db.close();
        }
    }

    public int countRecordsFromTable(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int)(DatabaseUtils.queryNumEntries(db, tableName));
        db.close();
        return numRows;
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> listOfStudents = new ArrayList<>();
        String selectAll = "SELECT * FROM " + students_table_name;
        if (countRecordsFromTable(students_table_name) != 0)
        {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectAll, null);
            int recordCount = countRecordsFromTable(students_table_name);
            if (recordCount != 0) {
                cursor.moveToFirst();
                String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
                String fname = cursor.getString(cursor.getColumnIndexOrThrow("fname"));
                String lname = cursor.getString(cursor.getColumnIndexOrThrow("lname"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
                float gpa = cursor.getFloat(cursor.getColumnIndexOrThrow("gpa"));
                Major major = getMajorGivenId(cursor.getInt(cursor.getColumnIndexOrThrow("major_id")));
                Student student = new Student(fname, lname, username, email, age, gpa, major);
                listOfStudents.add(student);
                for (int i = 0; i < recordCount - 1; i++)
                {
                    cursor.moveToNext();
                    username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
                    fname = cursor.getString(cursor.getColumnIndexOrThrow("fname"));
                    lname = cursor.getString(cursor.getColumnIndexOrThrow("lname"));
                    email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                    age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
                    gpa = cursor.getFloat(cursor.getColumnIndexOrThrow("gpa"));
                    major = getMajorGivenId(cursor.getInt(cursor.getColumnIndexOrThrow("major_id")));
                    student = new Student(fname, lname, username, email, age, gpa, major);
                    listOfStudents.add(student);
                }
                cursor.close();
                db.close();
            }
        }
        return listOfStudents;
    }

    public Major getMajorGivenId(int major_id) {
        Major major;
        SQLiteDatabase db = this.getReadableDatabase();
        String checkMajorId = "SELECT * FROM " + majors_table_name + " WHERE major_id = '" + major_id + "';";
        Cursor cursor = db.rawQuery(checkMajorId, null);
        cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            int id = major_id;
            String name = cursor.getString(cursor.getColumnIndexOrThrow("major_name"));
            String prefix = cursor.getString(cursor.getColumnIndexOrThrow("major_prefix"));
            major = new Major(id, name, prefix);
        }
        else
        {
            major = null;
        }
        db.close();
        return major;
    }
}
