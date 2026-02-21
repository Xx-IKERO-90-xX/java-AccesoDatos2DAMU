package com.vtinstitute.vtinstitute_restapi.controller.view;

import java.util.List;
import java.time.Year;

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

import com.vtinstitute.vtinstitute_restapi.model.entity.Enrollment;
import com.vtinstitute.vtinstitute_restapi.model.entity.Student;
import com.vtinstitute.vtinstitute_restapi.model.entity.Score;
import com.vtinstitute.vtinstitute_restapi.model.entity.Cours;
import com.vtinstitute.vtinstitute_restapi.service.StudentService;
import com.vtinstitute.vtinstitute_restapi.service.CoursService;
import com.vtinstitute.vtinstitute_restapi.service.EnrollmentService;
import com.vtinstitute.vtinstitute_restapi.service.ScoresService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/students")
public class StudentsViewController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private ScoresService scoreService;

    @Autowired
    private CoursService coursService;

    @GetMapping
    public String index(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        Model model) {
        
        Page<Student> studentPage = studentService.findStudentsPaged(page, size);

        model.addAttribute("students", ((Slice<Student>) studentPage).getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());

        return "/students/index";
    }

    @GetMapping("/{code}")
    public String details(@PathVariable(value = "code") String code, Model model) {
        List<Enrollment> enrollments = enrollmentService.findByStudentId(code);
        Student student = studentService.findById(code);
        List<Cours> courses = coursService.findAll();
        
        model.addAttribute("courses", courses);
        model.addAttribute("enrollments", enrollments);
        model.addAttribute("student", student);
    
        return "/students/details";
    }

    @GetMapping("/expedient/{code}")
    public String getStudentScoresByEntollment(@PathVariable(value = "code") int code, Model model) {
        List<Score> scores = scoreService.findByEnrollment(code);
        float finalScore = scoreService.getFinalScore(scores);
        Enrollment enrollment = enrollmentService.findById(code);

        model.addAttribute("scores", scores);
        model.addAttribute("finalScore", finalScore);
        model.addAttribute("enrollment", enrollment);

        return "/students/expedient";
    }

    @PostMapping("/enroll/{idcard}")
    public String enrollStudent(@PathVariable("idcard") String idcard, @RequestParam("coursId") int coursId) throws Exception {
        Cours cours = coursService.findById(coursId);
        Student student = studentService.findById(idcard);
        int year = Year.now().getValue();

        enrollmentService.enrollStudent(student, cours, year);

        return "redirect:/students/" + idcard;
    }

}
