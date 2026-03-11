package com.vtinstitute.vtinstitute_restapi.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtinstitute.vtinstitute_restapi.model.entity.Student;
import com.vtinstitute.vtinstitute_restapi.model.entity.Cours;
import com.vtinstitute.vtinstitute_restapi.model.entity.Enrollment;
import com.vtinstitute.vtinstitute_restapi.model.entity.Score;
import com.vtinstitute.vtinstitute_restapi.service.StudentService;
import com.vtinstitute.vtinstitute_restapi.service.EnrollmentService;
import com.vtinstitute.vtinstitute_restapi.service.ScoresService;
import com.vtinstitute.vtinstitute_restapi.service.CoursService;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ScoresService scoreService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private CoursService coursService;

    @GetMapping("/students/{idcard}")
    public Student findStudentByIdcard(
        @PathVariable String idcard
    ) {
        Student student = studentService.findById(idcard);
        return student;
    }

    @GetMapping("/courses/{idcours}")
    public Cours fincCoursById(
        @PathVariable int idcours
    ) {
        Cours cours = coursService.findById(idcours);
        return cours;
    }
    
    @PostMapping("/students/add")
    public Student add(
        @Validated 
        @RequestBody Student student
    ) throws Exception {
        return studentService.save(student);
    }

    @GetMapping("/students/expedient/")
    public List<Score> getStudentCoursExpedient(
        @RequestParam("idcard") String idcard, 
        @RequestParam("cours") int coursId
    ) {
        List<Score> scores = scoreService.getByIdcardCours(idcard, coursId);
        return scores;
    }

    @PostMapping("/enrollments/enroll")
    public ResponseEntity<Void> enrollStudent(@RequestBody Enrollment enrollment) throws Exception { 
        String idcard = enrollment.getStudent().getIdcard();
        int cours = enrollment.getCourse().getId();
        int year = enrollment.getYear();
        enrollmentService.enrollStudent(idcard, cours, year);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/scores/qualify")
    public ResponseEntity<Void> qualfyScore(@RequestBody Map<String, Object> payload) throws Exception {
        int enrollment = (int) payload.get("enrollment");
        int subject = (int) payload.get("subject");
        int numScore = (int) payload.get("score");

        Score score = scoreService.getScoreByEnrollmentSubject(enrollment, subject);
        
        if (score == null) {
            return ResponseEntity.notFound().build();
        }

        score.setScore(numScore);
        scoreService.save(score);
        
        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Void> registerStudent(@RequestBody Map<String, Object> payload) throws Exception {
        String idcard = (String) payload.get("idcard");
        String email = (String) payload.get("email");

        if (studentService.findById(idcard) == null) {
            return ResponseEntity.notFound().build();
        }

        if (studentService.emailInUse(email)) {
            return ResponseEntity.badRequest().build();
        }

        Student student = studentService.findById(idcard);
        studentService.registerUser(student, idcard, email);

        return ResponseEntity.ok().build();
    }

    @GetMapping({"/enrollments", "/enrollments/"})
    public List<Enrollment> getEnrollmentsByStudentCours(
        @RequestParam("idcard") String idcard,
        @RequestParam("idcours") int idcours
    ) throws Exception {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentCours(idcard, idcours);
        return enrollments;
    }
}
