package com.repo.Project2_JavaToJSON.controller;


import com.repo.Project2_JavaToJSON.model.QuizCategory;
import com.repo.Project2_JavaToJSON.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Calling 2 Downstream API's and merging the result into one

@RestController
@RequestMapping("/")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/coding/exercise/quiz")
    public Map<String, List<QuizCategory>> resultAPI(){
        List<QuizCategory> quizCategories = quizService.getQuizCategories();
        Map<String,List<QuizCategory>> response = new HashMap<>();
        response.put("quiz",quizCategories);
        return response;
    }




}
