package com.vtinstitute.vtinstitute_restapi.controller;

import com.vtinstitute.vtinstitute_restapi.model.entity.Score;

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

import com.vtinstitute.vtinstitute_restapi.service.ScoresService;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {
    @Autowired
    private ScoresService scoreService;

    @GetMapping
    public List<Score> getAll() {
        return scoreService.findAll();
    }

    @GetMapping("/{id}")
    public Score getById(int id) {
        return scoreService.findById(id);
    }

    @PostMapping("/add")
    public Score add(@Validated @RequestBody Score score) {
        return scoreService.save(score);
    }

    @PutMapping("/update/{id}")
    public Score update(@PathVariable(value = "id") int id, @Validated @RequestBody Score score) {
        return scoreService.update(id, score.getScore());
    }

    @DeleteMapping("/delete/{id}")
    public Score delete(@PathVariable(value = "id") int id) {
        return scoreService.delete(id);
    }
}
