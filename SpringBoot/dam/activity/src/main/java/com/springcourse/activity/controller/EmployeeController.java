package com.springcourse.activity.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.springcourse.activity.models.dao.EmployeeDAO;
import com.springcourse.activity.models.entity.Employee;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @GetMapping
    public List<Employee> findAllUsers() {
        return (List<Employee>) employeeDAO.findAll();
    }

    @GetMapping("/{id}")
    public Employee findUserById(@PathVariable(value = "id") int id) {
        // Lógica para obtener un empleado por ID
        Optional<Employee> employee = employeeDAO.findById(id);
        
        if (!employee.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return employee.get();
    }

    @PostMapping("/save")
    public Employee saveEmployee(@Validated @RequestBody Employee employee) {
        return employeeDAO.save(employee);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable long id, @Validated @RequestBody Employee employeeDetails) {
        Employee existingEmployee = employeeDAO.findById((int) id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found!"));

        existingEmployee.setEname(employeeDetails.getEname());
        existingEmployee.setJob(employeeDetails.getJob());
        existingEmployee.setDepartment(employeeDetails.getDepartment());

        return employeeDAO.save(existingEmployee);
    }
}
