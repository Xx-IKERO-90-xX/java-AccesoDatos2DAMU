package com.vtinstitute.vtinstitute_restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.vtinstitute.vtinstitute_restapi.model.dao.EnrollmentDAO;
import com.vtinstitute.vtinstitute_restapi.model.dao.ScoreDAO;
import com.vtinstitute.vtinstitute_restapi.model.entity.Score;


@Service
public class ScoresService {

    @Autowired
    private ScoreDAO scoreDAO;

    @Autowired
    private EnrollmentDAO enrollmentDAO;

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
}
