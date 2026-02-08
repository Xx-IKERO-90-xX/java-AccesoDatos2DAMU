package com.vtinstitute.vtinstitute_restapi.controller;

import com.vtinstitute.vtinstitute_restapi.model.entity.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.vtinstitute.vtinstitute_restapi.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable(value = "id") String id) {
        return studentService.findById(id);
    } 

    @PostMapping("/add")
    public Student add(@Validated @RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping("/update/{id}")
    public Student update(@PathVariable(value = "id") String id, @Validated @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("/delete/{id}")
    public Student delete(@PathVariable(value = "id") String id) {
        return studentService.delete(id);
    }
}
