package com.vtinstitute.vtinstitute_restapi.controller.api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping
    public String test() {
        return "Hello World!";
    }   
}
