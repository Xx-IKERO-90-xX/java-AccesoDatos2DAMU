package com.vtinstitute.vtinstitute_restapi.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainViewController {
    @GetMapping("/")
    public String index() {
        return "/main/index";
    }
}
