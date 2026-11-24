package com.example.homework03_program01_colebourbina;

public class Major {
    private int id;
    private String name;
    private String prefix;

    public Major() {

    }

    public Major(int i, String n, String p) {
        id = i;
        name = n;
        prefix = p;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}