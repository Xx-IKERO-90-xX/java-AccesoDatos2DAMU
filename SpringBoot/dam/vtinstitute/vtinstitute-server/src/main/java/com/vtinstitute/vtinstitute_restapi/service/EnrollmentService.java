package com.vtinstitute.vtinstitute_restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vtinstitute.vtinstitute_restapi.model.dao.EnrollmentDAO;
import com.vtinstitute.vtinstitute_restapi.model.dao.ScoreDAO;
import com.vtinstitute.vtinstitute_restapi.model.dao.StudentDAO;
import com.vtinstitute.vtinstitute_restapi.model.entity.Cours;
import com.vtinstitute.vtinstitute_restapi.model.entity.Enrollment;
import com.vtinstitute.vtinstitute_restapi.model.entity.Score;
import com.vtinstitute.vtinstitute_restapi.model.entity.Student;
import com.vtinstitute.vtinstitute_restapi.model.entity.SubjectCours;

import jakarta.transaction.Transactional;

import com.vtinstitute.vtinstitute_restapi.model.entity.Subject;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentDAO enrollmentDAO;

    @Autowired
    private ScoreDAO scoreDAO;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ScoresService scoreService;

    @Autowired
    private StudentService studentService;

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

    public boolean canEnroll(Student student, Cours cours) {
        return enrollmentDAO.countByStudentAndCourse(student, cours) < 2;
    }

    public int getCountEnrollmentsStudent(String idcard) {
        int count = enrollmentDAO.getCountEnrollmentStudent(idcard);
        return count;
    }

    @Transactional
    public void enrollStudent(Student student, Cours cours, int year) throws Exception {
        List<Enrollment> enrollments =
            enrollmentDAO.findByStudentAndCourse(cours.getId(), student.getIdcard());

        if (enrollments.size() >= 2) {
            throw new Exception("No se puede matricular a un estudiante más de 2 veces en un mismo curso.");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(cours);
        enrollment.setStudent(student);
        enrollment.setYear(year);

        List<SubjectCours> subjects;
        
        if (getCountEnrollmentsStudent(student.getIdcard()) > 0) {
            subjects = subjectService.getSecondYearSubjectByCours(cours);
        } else {
            subjects = subjectService.getFirstYearSubjectByCours(cours);
        }

        enrollmentDAO.save(enrollment);

        for (SubjectCours sc : subjects) {
            Subject subject = sc.getSubject();

            if (!scoreDAO.existsByEnrollmentAndSubject(enrollment, subject)) {
                Score score = new Score();
                score.setEnrollment(enrollment);
                score.setSubject(subject);
                score.setScore(null);

                scoreService.save(score);
            }
        }
    }
}
