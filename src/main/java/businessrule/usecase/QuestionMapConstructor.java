package businessrule.usecase;

import entity.Question;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a utility class responsible for constructing a map of question display formatters.
 */
class QuestionMapConstructor {
    /**
     * Constructs a map of question display formatters based on the provided list of questions.
     *
     * @param questionList The list of questions for which to construct the map.
     * @return A map containing question display formatters.
     */
    protected Map<Integer, QuestionDisplayFormatter> constructQuestionMap(List<Question> questionList) {
        Map<Integer, QuestionDisplayFormatter> questionMap = new HashMap<>();
        if (questionList.isEmpty()) {
            return questionMap;
        }
        for (Question question : questionList) {
            int questionId = question.getQuestionId();
            String title = question.getTitle();
            String type = question.getType();
            LocalDate legalDeadline = question.getLegalDeadline();
            QuestionDisplayFormatter questionDisplayFormatter = new QuestionDisplayFormatter(title, type, legalDeadline);
            questionMap.put(questionId, questionDisplayFormatter);
        }
        return questionMap;
    }
}