package com.vtinstitute.vtinstitute_restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.vtinstitute.vtinstitute_restapi.model.dao.EnrollmentDAO;
import com.vtinstitute.vtinstitute_restapi.model.dao.ScoreDAO;
import com.vtinstitute.vtinstitute_restapi.model.dao.StudentDAO;
import com.vtinstitute.vtinstitute_restapi.model.entity.Score;


@Service
public class ScoresService {

    @Autowired
    private ScoreDAO scoreDAO;

    @Autowired
    private EnrollmentDAO enrollmentDAO;

    @Autowired
    private StudentDAO studentDAO;

    @GetMapping
    public List<Score> findAll() {
        return (List<Score>) scoreDAO.findAll();
    }

    @GetMapping("/{id}")
    public Score findById(int id) {
        Optional<Score> score = scoreDAO.findById(id);
        if (!score.isPresent()) {
            throw new RuntimeException("Score not found");
        }
        return score.get();
    }

    public Score save(Score score) {
        return scoreDAO.save(score);
    }

    public Score update(int id, int score) {
        if (!scoreDAO.existsById(id)) {
            throw new RuntimeException("Score not found");
        }
        Score currentScore = scoreDAO.findById(id).get();
        currentScore.setScore(score);
        return scoreDAO.save(currentScore);
    }

    public Score delete(int id) {
        if (!scoreDAO.existsById(id)) {
            throw new RuntimeException("Score not found");
        }
        Score score = scoreDAO.findById(id).get();
        scoreDAO.delete(score);
        return score;
    }

    public List<Score> findByEnrollment(int enrollmentId) {
        if (!enrollmentDAO.existsById(enrollmentId)) {
            throw new RuntimeException("Enrollment not found");
        }
        return scoreDAO.findByEnrollmentId(enrollmentId);
    }

    public List<Score> findPassingScoresByEnrollment(int enrollmentId) {
        if (!enrollmentDAO.existsById(enrollmentId)) {
            throw new RuntimeException("Enrollment not found");
        }
        return scoreDAO.findPassingScoresByEnrollment(enrollmentId);
    }

    public List<Score> findFiledScoresByEnrollment(int enrollmentId) {
        if (!enrollmentDAO.existsById(enrollmentId)) {
            throw new RuntimeException("Enrollment not found");
        }
        return scoreDAO.findFiledScoresByEnrollment(enrollmentId);
    }

    public List<Score> findScoresByStudentId(String studentId) {
        if (!studentDAO.existsById(studentId)) {
            throw new RuntimeException("Student not found");
        }
        return scoreDAO.findScoresByStudent(studentId);
    }

    public List<Score> findPassingScoresByStudentId(String studentId) {
        if (!studentDAO.existsById(studentId)) {
            throw new RuntimeException("Student not found");
        }
        return scoreDAO.findPassingScoresByStudent(studentId);
    }

    public List<Score> findFailedScoresByStudentId(String studentId) {
        if (!studentDAO.existsById(studentId)) {
            throw new RuntimeException("Student not found");
        }
        return scoreDAO.findFailedScoreByStudent(studentId);
    }

    public float getFinalScore(List<Score> scores) {
        float finalScore = 0;
        int sum = 0;
        int total = scores.size();

        for (Score score : scores) {
            Integer value = score.getScore();

            if (value != null) {
                sum = sum + value;
            }
        }

        finalScore = sum / total;
        return finalScore;
    }

    public List<Score> getFailedScoresByEnrollment(int enrollmentId) {
        List<Score> scores = scoreDAO.getFailedScoresByEnrollment(enrollmentId);
        return scores;
    }
}
