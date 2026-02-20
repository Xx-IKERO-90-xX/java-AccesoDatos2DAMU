package com.vtinstitute.vtinstitute_restapi.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.vtinstitute.vtinstitute_restapi.model.entity.Cours;
import com.vtinstitute.vtinstitute_restapi.service.CoursService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/courses")
public class CoursController {
    @Autowired
    private CoursService coursService;

    @GetMapping
    public List<Cours> getAll() {
        return coursService.findAll();
    }

    @GetMapping("/{id}")
    public Cours getById(@PathVariable(value = "id") int id) {
        return coursService.findById(id);
    } 

    @PostMapping("/add")
    public Cours add(@Validated @RequestBody Cours cours) {
        return coursService.save(cours);
    }

    @PutMapping("/update/{id}")
    public Cours update(@PathVariable(value = "id") int id, @Validated @RequestBody Cours cours) {
        return coursService.update(id, cours);
    }

    @DeleteMapping("/delete/{id}")
    public Cours delete(@PathVariable(value = "id") int id) {
        return coursService.delete(id);
    }
}
