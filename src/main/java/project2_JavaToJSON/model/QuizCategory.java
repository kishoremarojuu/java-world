package project2_JavaToJSON.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizCategory {

    private String category;

    private List<QuestionMetadata> results;


}
