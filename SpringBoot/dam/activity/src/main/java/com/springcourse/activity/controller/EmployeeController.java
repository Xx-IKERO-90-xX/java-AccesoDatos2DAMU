package com.springcourse.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.springcourse.activity.models.entity.Employee;
import com.springcourse.activity.service.EmployeeService;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> findAllUsers() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findUserById(@PathVariable(value = "id") int id) {
        return employeeService.findById(id);
    }

    @PostMapping("/save")
    public Employee saveEmployee(@Validated @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable long id, @Validated @RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }
}
