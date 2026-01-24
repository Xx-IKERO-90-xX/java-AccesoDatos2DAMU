package org.example.models;

public class Subject {
    private int code;
    private String name;
    private int year;
    private int courseCode;

    public Subject(){ }

    public Subject(int code, String name, int year){
        this.code = code;
        this.name = name;
        this.year = year;
    }

    public int getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setYear(int year){
        this.year = year;
    }
    public int getYear() {
        return year;
    }

    public void setCourseCode(int code) {
        this.courseCode = code;
    }
    public int getCourseCode() {
        return courseCode;
    }
}
