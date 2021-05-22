package com.repo.Project2_JavaToJSON.controller;


import com.repo.Project2_JavaToJSON.model.QuizCategory;
import com.repo.Project2_JavaToJSON.service.HardCodedObject;
import com.repo.Project2_JavaToJSON.service.QuizServiceKiboUsingJava7;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class QuizControllerKiboUsingJava7 {

    @Autowired
    private QuizServiceKiboUsingJava7 quizServiceKiboUsingJava7;

    @Autowired
    private HardCodedObject hardCodedObjectd;

    @GetMapping("/coding/exercise/quiz/kiboquiz")
    public Map<String,List<QuizCategory>> resultAPI(){
        List<QuizCategory> quizCategories = quizServiceKiboUsingJava7.getQuizCategories();
        Map<String,List<QuizCategory>> response = new HashMap<>();
        response.put("kishore",quizCategories);
        return response;
    }




}
