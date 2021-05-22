package com.repo.Project2_JavaToJSON.service;


import com.repo.Project2_JavaToJSON.model.QuestionMetadata;
import com.repo.Project2_JavaToJSON.model.QuizCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class HardCodedObject {


    public List<QuizCategory> getQuizCategories() {

        List<QuizCategory> quizCategoryList = new ArrayList<>();
        //music category
        QuizCategory musicCatergory = new QuizCategory();

        List<QuestionMetadata> questionMetadataList = new ArrayList<>();

        QuestionMetadata questionMetadata = new QuestionMetadata();
        questionMetadata.setType("multiple");
        questionMetadata.setDifficulty("medium");
        questionMetadata.setQuestion("question");
        questionMetadata.setCorrectAnswer("correctAnser");
        questionMetadata.setAllAnswers(Arrays.asList("some", "some1"));

        questionMetadataList.add(questionMetadata);

        musicCatergory.setCategory("Entertainment: Music");
        musicCatergory.setResults(questionMetadataList);


        //film category
        QuizCategory filmCategory = new QuizCategory();

        List<QuestionMetadata> filmquestionMetadataList = new ArrayList<>();

        QuestionMetadata filmquestionMetadata = new QuestionMetadata();
        filmquestionMetadata.setType("multiple");
        filmquestionMetadata.setDifficulty("hard");
        filmquestionMetadata.setQuestion("question");
        filmquestionMetadata.setCorrectAnswer("correctAnser");
        filmquestionMetadata.setAllAnswers(Arrays.asList("some", "some1"));

        filmquestionMetadataList.add(questionMetadata);

        filmCategory.setCategory("Entertainment: Film");
        filmCategory.setResults(filmquestionMetadataList);

        //adding categories
        quizCategoryList.add(musicCatergory);
        quizCategoryList.add(filmCategory);


        return quizCategoryList;
    }

}
