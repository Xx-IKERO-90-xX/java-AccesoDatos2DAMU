package com.springcourse.activity.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.springcourse.activity.models.entity.Department;

import com.springcourse.activity.service.DepartmentService;

public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> findAll() {
        return departmentService.findAll();
    }

    @GetMapping
    public Department findById(@PathVariable(value = "id") int id) {
        Optional<Department> department = Optional.ofNullable(departmentService.findById(id));

        if (!department.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return department.get();
    }

    @PostMapping("/save")
    public Department saveDepartment(@Validated @RequestBody Department department) {
        return departmentService.saveDepartment(department);   
    }

    @PutMapping("/update/{id}")
    public Department updateDepartment(@PathVariable(value = "id") int id, @Validated @RequestBody Department updatedDepartment) {
        return departmentService.updateDepartment(updatedDepartment);
    }
}