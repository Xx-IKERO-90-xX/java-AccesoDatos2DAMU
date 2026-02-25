package com.vtinstitute.vtinstitute_restapi.controller.view;

import com.vtinstitute.vtinstitute_restapi.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.vtinstitute.vtinstitute_restapi.model.entity.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/login")
    public String getLogin() {
       return "/auth/index"; 
    }

    @PostMapping("/login")
    public String postLogin(
            @RequestParam String username,
            @RequestParam String passwd,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes
        ) {
        
        // 1. CORRECCIÓN: Usar .equals() para el ADMIN
        if ("admin".equals(username) && "admin".equals(passwd)) {
            List<GrantedAuthority> authorities =
                AuthorityUtils.createAuthorityList("ROLE_ADMIN");

            UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(username, null, authorities);

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(auth);

            session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                context
            );

            session.setAttribute("rol", "ADMIN");

            return "redirect:/";
        }

        Student student = studentService.getStudentByIdcardEmail(username, passwd);

        if (student != null) {
            var authorities = AuthorityUtils.createAuthorityList("ROLE_STUDENT");

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    student.getEmail(),
                    null,
                    authorities
            );

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(auth);

            SecurityContextHolder.setContext(context);
            session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                context
            );

            session.setAttribute("user", student);
            session.setAttribute("idcard", student.getIdcard());
            session.setAttribute("rol", "STUDENT");

            return "redirect:/";
        }

        redirectAttributes.addFlashAttribute("error", "No se ha encontrado el usuario o los datos son incorrectos!");
        return "redirect:/auth/login";
    }

    @PostMapping("/auth/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        session.invalidate();
        return "redirect:/auth/login";
    }
}
