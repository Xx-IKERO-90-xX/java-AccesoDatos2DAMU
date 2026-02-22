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

    @Autowired
    private CoursService coursService;

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

    @Transactional
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

    public Enrollment getLastEnrollmentStudent(String idcard) {
        Enrollment enrollment = enrollmentDAO.getLastStudentEnrollment(idcard)
            .stream()
            .findFirst()
            .orElse(null);
        
        return enrollment;
    }

    @Transactional
    public void enrollStudent(String idcard, int idcours, int year) throws Exception {

        Student student = studentService.findById(idcard);
        Cours cours = coursService.findById(idcours);

        if (student == null || cours == null) {
            throw new Exception("Hubo un fallo al encontrar el estudiante o el curso");
        }

        List<Enrollment> enrollments =
            enrollmentDAO.findByStudentAndCourse(cours.getId(), student.getIdcard());

        if (enrollments.size() > 1) {
            throw new Exception("No se puede matricular a un estudiante más de 2 veces en un mismo curso.");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(cours);
        enrollment.setStudent(student);
        enrollment.setYear(year);

        List<SubjectCours> subjects;

        if (enrollments.isEmpty()) {

            subjects = subjectService.getFirstYearSubjectByCours(cours);
            enrollmentDAO.save(enrollment);

        } else {
            subjects = subjectService.getSecondYearSubjectByCours(cours);

            Enrollment lastEnrollment = enrollments.get(0); // luego mejoramos esto
            enrollmentDAO.save(enrollment);

            List<Score> failScores =
                scoreService.getFailedScoresByEnrollment(lastEnrollment.getId());

            for (Score score : failScores) {
                Score sc = new Score();
                sc.setEnrollment(enrollment);
                sc.setSubject(score.getSubject());
                sc.setScore(score.getScore());

                scoreService.save(sc);
            }
        }

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
