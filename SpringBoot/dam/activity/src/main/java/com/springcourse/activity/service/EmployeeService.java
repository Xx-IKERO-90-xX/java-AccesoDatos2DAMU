package com.springcourse.activity.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springcourse.activity.models.dao.EmployeeDAO;
import com.springcourse.activity.models.entity.Employee;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeDAO employeeDAO;

    public List<Employee> findAll() {
        return (List<Employee>) employeeDAO.findAll();
    }

    public Employee findById(int id) {
        Optional<Employee> employee = employeeDAO.findById(id);
        
        if (!employee.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return employee.get();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeDAO.save(employee);
    }

    public Employee updateEmployee(Employee employeeDetails) {
        Employee existingEmployee = employeeDAO.findById(employeeDetails.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found!"));

        existingEmployee.setEname(employeeDetails.getEname());
        existingEmployee.setJob(employeeDetails.getJob());
        existingEmployee.setDepartment(employeeDetails.getDepartment());

        return employeeDAO.save(existingEmployee);
    }

    public Employee deleteEmployee(int id) {
        Employee employee = employeeDAO.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found!"));
        
        employeeDAO.delete(employee);
        return employee;
    }
}