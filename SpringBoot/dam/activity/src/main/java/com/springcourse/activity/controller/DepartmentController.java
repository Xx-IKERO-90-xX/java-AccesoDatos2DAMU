package com.springcourse.activity.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


import com.springcourse.activity.models.entity.Department;
import com.springcourse.activity.models.dao.DepartmentDAO;

public class DepartmentController {
    @Autowired
    private DepartmentDAO departmentDAO;

    @GetMapping
    public List<Department> findAll() {
        return (List<Department>) departmentDAO.findAll();
    }

    @GetMapping
    public Department findById(@PathVariable(value = "id") int id) {
        Optional<Department> department = departmentDAO.findById(id);

        if (!department.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return department.get();
    }

    @PostMapping("/save")
    public Department saveDepartment(@Validated @RequestBody Department department) {
        return departmentDAO.save(department);   
    }

    @PutMapping("/update/{id}")
    public Department updateDepartment(@PathVariable long id, @Validated @RequestBody Department newDepartment) {
        Department existingDepartment = departmentDAO.findById((int) id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found!"));

        existingDepartment.setName(newDepartment.getName());
        existingDepartment.setLocation(newDepartment.getLocation());

        return departmentDAO.save(existingDepartment);
    }
}