package com.vtinstitute.vtinstitute_restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vtinstitute.vtinstitute_restapi.model.dao.EnrollmentDAO;
import com.vtinstitute.vtinstitute_restapi.model.dao.StudentDAO;
import com.vtinstitute.vtinstitute_restapi.model.entity.Enrollment;
import com.vtinstitute.vtinstitute_restapi.model.entity.Student;


@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentDAO enrollmentDAO;

    @Autowired
    private StudentDAO studentDAO;

    public List<Enrollment> findAll() {
        return (List<Enrollment>) enrollmentDAO.findAll();
    }
    
    public Enrollment findById(int id) {
        Optional<Enrollment> enrollment = enrollmentDAO.findById(id);

        if (!enrollment.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment not found");
        }
        return enrollment.get();
    }

    public Enrollment save(Enrollment enrollment) {
        return enrollmentDAO.save(enrollment);
    }

    public Enrollment update(int id, Enrollment enrollment) {
        if (!enrollmentDAO.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment not found");
        }
        Enrollment currentEnrollment = enrollmentDAO.findById(id).get();
        currentEnrollment.setStudent(enrollment.getStudent());
        currentEnrollment.setCourse(enrollment.getCourse());
        return enrollmentDAO.save(currentEnrollment);
    }
    public Enrollment delete(int id) {
        if (!enrollmentDAO.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment not found");
        }
        Enrollment enrollment = enrollmentDAO.findById(id).get();
        enrollmentDAO.delete(enrollment);
        return enrollment;
    }

    public List<Enrollment> findByStudentId(String studentId) {
        if (!studentDAO.existsById(studentId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        Student student = studentDAO.findById(studentId).get();
        return enrollmentDAO.findByStudent(student);
    }
}
