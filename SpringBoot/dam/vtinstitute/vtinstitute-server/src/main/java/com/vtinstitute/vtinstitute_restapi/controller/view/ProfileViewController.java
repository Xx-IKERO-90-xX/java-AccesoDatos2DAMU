package com.vtinstitute.vtinstitute_restapi.controller.view;

import java.util.List;
import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vtinstitute.vtinstitute_restapi.model.entity.Enrollment;
import com.vtinstitute.vtinstitute_restapi.model.entity.Student;
import com.vtinstitute.vtinstitute_restapi.model.entity.Score;
import com.vtinstitute.vtinstitute_restapi.model.entity.Cours;
import com.vtinstitute.vtinstitute_restapi.service.StudentService;
import com.vtinstitute.vtinstitute_restapi.service.CoursService;
import com.vtinstitute.vtinstitute_restapi.service.EnrollmentService;
import com.vtinstitute.vtinstitute_restapi.service.ScoresService;
import jakarta.servlet.http.HttpSession;


import org.springframework.ui.Model;

@Controller
@RequestMapping("/profile")
public class ProfileViewController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CoursService coursService;

    @Autowired
    private ScoresService scoreService;

    @GetMapping("/{code}")
    public String index(@PathVariable(value = "code") String code, Model model) {
        List<Enrollment> enrollments = enrollmentService.findByStudentId(code);
        Student student = studentService.findById(code);
        List<Cours> courses = coursService.findAll();
        
        model.addAttribute("courses", courses);
        model.addAttribute("enrollments", enrollments);
        model.addAttribute("student", student);
    
        return "/profile/index";
    }

    @GetMapping("/expedient/{code}")
    public String getStudentScoresByEntollment(@PathVariable(value = "code") int code, Model model) {
        List<Score> scores = scoreService.findByEnrollment(code);
        float finalScore = scoreService.getFinalScore(scores);
        Enrollment enrollment = enrollmentService.findById(code);

        model.addAttribute("scores", scores);
        model.addAttribute("finalScore", finalScore);
        model.addAttribute("enrollment", enrollment);

        return "/profile/expedient";
    }

    @PostMapping("/edit/{idcard}")
    public String editStudent(
        @PathVariable("idcard") String idcard,
        @RequestParam("firstname") String firstname,
        @RequestParam("lastname") String lastname,
        @RequestParam("email") String email,
        @RequestParam("phone") String phone,
        RedirectAttributes redirectAttributes
    ) {
        try {
            Student student = studentService.findById(idcard);
            student.setFirstname(firstname);
            student.setLastname(lastname);
            student.setEmail(email);
            student.setPhone(phone);

            studentService.save(student);
            redirectAttributes.addFlashAttribute("success", "Se ha editado el estudiante " + student.getIdcard() + " correctamente!");

        } catch(Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ha habido un fallo en la edicion del estudiante, revise los datos.");
        }

        return "redirect:/profile/" + idcard;
    }
}
