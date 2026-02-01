package com.springcourse.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springcourse.activity.models.entity.Employee;
import com.springcourse.activity.service.EmployeeService;
import com.springcourse.activity.models.entity.Department;
import com.springcourse.activity.service.DepartmentService;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class ViewController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public String index(Model model) {
        List<Employee> employees = employeeService.findAll();
        List<Department> departments = departmentService.findAll();
        
        int totalEmployees = employees.size();
        int totalDepartments = departments.size();
        
        model.addAttribute("totalEmployees", totalEmployees);
        model.addAttribute("totalDepartments", totalDepartments);

        return "index";
    }

    @GetMapping("/empleados")
    public String showEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();
        List<Department> departments = departmentService.findAll();

        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        model.addAttribute("employee", new Employee());
        model.addAttribute("department", new Department());

        return "employees";
    }

    @GetMapping("/departamentos")
    public String showDepartments(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "departments";
    }

    @PostMapping("/empleados/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        Department department = departmentService.findById(employee.getDepartment().getId());
        employee.setDepartment(department);
        employeeService.saveEmployee(employee);
        return "redirect:/empleados";
    }

}
