package org.vtinstitute.models.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectCours {
    private Integer id;
    private Subject subject;
    private Cours course;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Cours getCourse() {
        return course;
    }

    public void setCourse(Cours course) {
        this.course = course;
    }
}