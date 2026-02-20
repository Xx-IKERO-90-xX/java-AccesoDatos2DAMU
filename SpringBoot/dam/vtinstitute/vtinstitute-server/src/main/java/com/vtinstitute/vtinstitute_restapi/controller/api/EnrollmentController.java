package com.vtinstitute.vtinstitute_restapi.controller.api;

import com.vtinstitute.vtinstitute_restapi.model.entity.Enrollment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtinstitute.vtinstitute_restapi.service.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public List<Enrollment> getAll() {
        return enrollmentService.findAll();
    }

    @GetMapping("/{id}")
    public Enrollment getById(@PathVariable(value = "id") int id) {
        return enrollmentService.findById(id);
    }

    @PostMapping("/add")
    public Enrollment add(@Validated @RequestBody Enrollment enrollment) {
        return enrollmentService.save(enrollment);
    }

    @PutMapping("/update/{id}")
    public Enrollment update(@Validated @RequestBody Enrollment enrollment, @PathVariable(value = "id") int id) {
        return enrollmentService.update(id, enrollment);
    }

    @DeleteMapping("/delete/{id}")
    public Enrollment delete(@PathVariable(value = "id") int id) {
        return enrollmentService.delete(id);
    }

    @GetMapping("/student/{studentId}")
    public List<Enrollment> getByStudentId(@PathVariable(value = "studentId") String studentId) {
        return enrollmentService.findByStudentId(studentId);
    }
}
