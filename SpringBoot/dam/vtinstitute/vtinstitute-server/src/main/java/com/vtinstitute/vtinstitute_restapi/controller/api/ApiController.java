package com.vtinstitute.vtinstitute_restapi.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtinstitute.vtinstitute_restapi.model.entity.Student;
import com.vtinstitute.vtinstitute_restapi.model.entity.Score;
import com.vtinstitute.vtinstitute_restapi.service.StudentService;
import com.vtinstitute.vtinstitute_restapi.service.EnrollmentService;
import com.vtinstitute.vtinstitute_restapi.service.ScoresService;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ScoresService scoreService;

    @Autowired
    private EnrollmentService enrollmentService;
    
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
    public ResponseEntity<Void> enrollStudent(
        @RequestParam("idcard") String idCard, 
        @RequestParam("idcours") int idCours, 
        @RequestParam("year") int year
    ) throws Exception { 
        enrollmentService.enrollStudent(idCard, idCours, year);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/scores/qualify")
    public Score qualfyScore(
        @RequestParam("enrollment") int enrollmentId,
        @RequestParam("subject") int subjectId,
        @RequestParam("score") int scoreNum
    ) {
        Score score = scoreService.getScoreByEnrollmentSubject(enrollmentId, subjectId);
        score.setScore(scoreNum);

        Score score1 = scoreService.save(score);
        return score1;
    }
}
