package com.repo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/application")
    public String readEmployees() {
        return "I am the application-2, -->TWO<--, updated set divya testing 101";
    }

}
