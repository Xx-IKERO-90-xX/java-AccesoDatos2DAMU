package com.vtinstitute.vtinstitute_restapi.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.vtinstitute.vtinstitute_restapi.model.dao.StudentDAO;
import com.vtinstitute.vtinstitute_restapi.model.entity.Student;

@Service
public class StudentService {
    @Autowired
    private StudentDAO studentDAO;

    public List<Student> findAll() {
        return (List<Student>) studentDAO.findAll();
    }

    public Page<Student> findStudentsPaged(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return studentDAO.findAll(pageable);
    }

    public Student findById(String code) {
        Optional<Student> student = studentDAO.findById(code);

        if (!student.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        return student.get();
    }

    public Student save(Student student) {
        return studentDAO.save(student);
    }

    public Student update(String code, Student student) {
        if (!studentDAO.existsById(code)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        Student currentStudent = studentDAO.findById(code).get();
        currentStudent.setFirstname(student.getFirstname());
        currentStudent.setLastname(student.getLastname());
        currentStudent.setPhone(student.getPhone());
        currentStudent.setEmail(student.getEmail());
        
        return studentDAO.save(currentStudent);
    }

    public Student delete(String code) {
        if (!studentDAO.existsById(code)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        Student student = studentDAO.findById(code).get();
        studentDAO.delete(student);
        return student;
    }
}
