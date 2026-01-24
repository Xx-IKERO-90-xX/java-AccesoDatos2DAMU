package com.springcourse.activity.models.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "employee", schema = "public")
public class Employee {

    @Id
    @Column(name = "empno")
    private Integer id;

    @Column(name = "ename", length = 10)
    private String name;

    @Column(name = "job", length = 9)
    private String job;

    @ManyToOne
    @JoinColumn(name = "deptno") // Esta es la FK en la tabla employee
    private Department department;

    // Constructores, Getters y Setters
    public Employee() {}

    public Integer getId() {
        return id;
    }
    public void setEmpno(Integer id) {
        this.id = id;
    }
    public String getEname() {
        return name;
    }
    public void setEname(String name) {
        this.name = name;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
       
}