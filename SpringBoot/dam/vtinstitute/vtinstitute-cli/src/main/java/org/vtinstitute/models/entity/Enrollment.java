package org.vtinstitute.models.entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Enrollment {
    private Integer id;
    private Student student;
    private Cours course;
    private Integer year;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Cours getCourse() {
        return course;
    }

    public void setCourse(Cours course) {
        this.course = course;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}