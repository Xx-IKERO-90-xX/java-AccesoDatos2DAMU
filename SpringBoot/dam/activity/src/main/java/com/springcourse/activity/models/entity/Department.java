package com.springcourse.activity.models.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "dept", schema = "public")
public class Department {
    @Id
    @Column(name = "deptno")
    private Integer id;

    @Column(name = "dname", length = 14)
    private String name;

    @Column(name = "loc", length = 13)
    private String loc;

    // Relación bidireccional: Un departamento tiene muchos empleados
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;

    // Constructores, Getters y Setters
    public Department() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer deptno) {
        this.id = deptno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return loc;
    }

    public void setLocation(String loc) {
        this.loc = loc;
    }
}
