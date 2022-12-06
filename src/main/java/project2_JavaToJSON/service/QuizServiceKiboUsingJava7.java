package project2_JavaToJSON.service;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import project2_JavaToJSON.model.DownStreamResponse;
import project2_JavaToJSON.model.DownStreamResult;
import project2_JavaToJSON.model.QuestionMetadata;
import project2_JavaToJSON.model.QuizCategory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizServiceKiboUsingJava7 {

    WebClient client = WebClient.builder()
            .baseUrl("https://opentdb.com/")
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public List<QuizCategory> getQuizCategories() {

        //calling Downstream API's
        Flux<DownStreamResponse> downStreamRespons1 = getResponseFromAPIS(5, 11);
        Flux<DownStreamResponse> downStreamResponse2 = getResponseFromAPIS(5, 12);

        List<QuizCategory> quizCategoryList = new ArrayList<>();

        convertDownstreamResponse(downStreamRespons1.blockFirst(),quizCategoryList);
        convertDownstreamResponse(downStreamResponse2.blockFirst(),quizCategoryList);

        return quizCategoryList;

    }

    private List<QuizCategory> convertDownstreamResponse(DownStreamResponse downStreamRespons1, List<QuizCategory> quizCategoryList){

        QuizCategory quizCategory = new QuizCategory();
        quizCategory.setCategory(downStreamRespons1.getResults().get(0).getCategory());

        List<DownStreamResult> results = downStreamRespons1.getResults();
        List<QuestionMetadata> questionMetadataList = new ArrayList<>();
        for (DownStreamResult result : results) {
            QuestionMetadata questionMetadata = new QuestionMetadata();
            questionMetadata.setQuestion(result.getQuestion());
            questionMetadata.setType(result.getType());
            questionMetadata.setDifficulty(result.getDifficulty());
            questionMetadata.setCorrectAnswer(result.getCorrectAnswer());
            List<String> allans = new ArrayList<>();
            List<String> incorrectAnswers = result.getIncorrectAnswers();
            String correctAnswer = result.getCorrectAnswer();
            allans.add(correctAnswer);
            allans.addAll(incorrectAnswers);
            questionMetadata.setAllAnswers(allans);
            questionMetadataList.add(questionMetadata);
        }


        quizCategory.setResults(questionMetadataList);
        quizCategoryList.add(quizCategory);

        return quizCategoryList;
    }

    private Flux<DownStreamResponse> getResponseFromAPIS(int amount, int catergory) {

        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/api.php")
                        .queryParam("amount", amount)
                        .queryParam("category", catergory).build())
                .retrieve().bodyToFlux(DownStreamResponse.class);
    }


}
