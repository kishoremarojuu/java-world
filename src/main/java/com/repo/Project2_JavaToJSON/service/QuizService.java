package com.repo.Project2_JavaToJSON.service;


import com.repo.Project2_JavaToJSON.model.DownStreamResponse;
import com.repo.Project2_JavaToJSON.model.DownStreamResult;
import com.repo.Project2_JavaToJSON.model.QuestionMetadata;
import com.repo.Project2_JavaToJSON.model.QuizCategory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private WebClient webClient = WebClient.create("https://opentdb.com");

    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    public List<QuizCategory> getQuizCategories() {
        Flux<DownStreamResponse> downStreamResponse1 = getResponse(5,11);
        Flux<DownStreamResponse> downStreamResponse2 = getResponse(5,12);
        List<QuizCategory> quizCategories = new ArrayList<>();
        convertDownStreamResponseToQuizCategory(downStreamResponse2.blockFirst(),quizCategories);
        convertDownStreamResponseToQuizCategory(downStreamResponse1.blockFirst(),quizCategories);
        return quizCategories;
    }

    private Flux<DownStreamResponse> getResponse(Integer amount,Integer category) {

       return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api.php")
                .queryParam("amount",amount)
                .queryParam("category",category).build())
                .retrieve()
                .bodyToFlux(DownStreamResponse.class);

    }

    private void convertDownStreamResponseToQuizCategory(DownStreamResponse downStreamResponse, List<QuizCategory> quizCategories) {
        List<QuestionMetadata> questionMetadata = downStreamResponse.getResults().stream().map(this::mapToQuestionMetadata).collect(Collectors.toList());
        QuizCategory quizCategory = new QuizCategory();
        quizCategory.setCategory(downStreamResponse.getResults().get(0).getCategory());
        quizCategory.setResults(questionMetadata);
        quizCategories.add(quizCategory);
    }

    private QuestionMetadata mapToQuestionMetadata(DownStreamResult downStreamResult){
        List<String> allAnswers = new ArrayList<>();
        allAnswers.addAll(downStreamResult.getIncorrectAnswers());
        allAnswers.add(downStreamResult.getCorrectAnswer());
       return new QuestionMetadata(downStreamResult.getType(),downStreamResult.getDifficulty(),downStreamResult.getQuestion(),allAnswers,downStreamResult.getCorrectAnswer());
    }



}
