package com.vtinstitute.vtinstitute_restapi.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static java.lang.Integer.parseInt;
import com.vtinstitute.vtinstitute_restapi.service.ScoresService;

@Controller
@RequestMapping("/scores")
public class ScoreViewController {
    @Autowired
    private ScoresService scoreService;
    
    @PostMapping("/update/{id}")
    public String updateScore(
        @PathVariable("id") int id, 
        @RequestParam("score") int score, 
        @RequestParam("enrollmentId") String enrollmentId, 
        RedirectAttributes redirectAttributes
    ) {
        int idEnrollment = parseInt(enrollmentId);
        try {
            scoreService.update(id, score);
            redirectAttributes.addFlashAttribute("success", "Calificación actualizada!!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "La nota no puede ser menor que 0 o mayor que 10!!");
        }
        return "redirect:/students/expedient/" + idEnrollment;
    }
}
