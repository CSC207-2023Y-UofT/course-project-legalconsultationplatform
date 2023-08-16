package usecases.utils;

import entities.Question;
import usecases.dto.QuestionDisplay;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class constructs a map of question IDs to formatted display objects using `QuestionDisplayFormatter`.
 */
public class QuestionMapConstructor {

    /**
     * Constructs a map of question IDs to formatted display objects.
     *
     * @param questionList The list of questions to be processed.
     * @return A map where each question's ID is associated with a corresponding formatted display object.
     */
    public Map<Integer, QuestionDisplay> constructQuestionMap(List<Question> questionList) {
        Map<Integer, QuestionDisplay> questionMap = new LinkedHashMap<>();
        if (questionList.isEmpty()) {
            return questionMap;
        }
        for (Question question: questionList) {
            int questionId = question.getQuestionId();
            String title = question.getTitle();
            String type = question.getType();
            LocalDate legalDeadline = question.getLegalDeadline();
            boolean isClose = question.isClose();
            QuestionDisplay questionDisplayFormatter = new QuestionDisplay(title, type, legalDeadline, isClose);
            questionMap.put(questionId, questionDisplayFormatter);
        }
        return questionMap;
    }
}
