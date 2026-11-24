package com.example.homework03_program01_colebourbina;

public class Student {
    private String fName;
    private String lName;
    private String username;
    private String email;
    private int age;
    private float gpa;
    private Major major;

    public Student() {

    }

    public Student(String f, String l, String u, String e, int a, float g, Major m) {
        fName = f;
        lName = l;
        username = u;
        email = e;
        age = a;
        gpa = g;
        major = m;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }
}
