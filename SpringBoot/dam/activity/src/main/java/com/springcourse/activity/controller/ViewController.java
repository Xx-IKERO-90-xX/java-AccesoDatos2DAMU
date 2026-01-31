package com.springcourse.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.springcourse.activity.models.entity.Employee;
import com.springcourse.activity.service.EmployeeService;

import org.springframework.ui.Model;

@Controller
public class ViewController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String index() {return "index";}

    @GetMapping("/empleados")
    public String showEmployees(Model model) 
    {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employees";
    }
}
