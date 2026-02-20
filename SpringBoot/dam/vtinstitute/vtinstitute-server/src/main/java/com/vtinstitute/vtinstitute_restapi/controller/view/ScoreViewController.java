package com.vtinstitute.vtinstitute_restapi.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static java.lang.Integer.parseInt;

import com.vtinstitute.vtinstitute_restapi.model.entity.Enrollment;
import com.vtinstitute.vtinstitute_restapi.model.entity.Student;
import com.vtinstitute.vtinstitute_restapi.model.entity.Score;
import com.vtinstitute.vtinstitute_restapi.service.StudentService;
import com.vtinstitute.vtinstitute_restapi.service.EnrollmentService;
import com.vtinstitute.vtinstitute_restapi.service.ScoresService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/scores")
public class ScoreViewController {
    @Autowired
    private ScoresService scoreService;
    
    @PostMapping("/update/{id}")
    public String updateScore(@PathVariable("id") int id, @RequestParam("score") int score, @RequestParam("enrollmentId") String enrollmentId) {
        int idEnrollment = parseInt(enrollmentId);
        scoreService.update(id, score);
        return "redirect:/students/expedient/" + idEnrollment;
    }
}
