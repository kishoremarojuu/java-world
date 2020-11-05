package com.techprimers.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class EmployeeController {


    @GetMapping("/application")
    public String readEmployees() {
        return "I am the application-1, -->ONE<--";
    }

}
