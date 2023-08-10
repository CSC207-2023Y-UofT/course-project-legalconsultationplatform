package businessrule.usecase.util;

import entity.Question;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QuestionMapConstructor {
    public Map<Integer, QuestionDisplayFormatter> constructQuestionMap(List<Question> questionList) {
        Map<Integer, QuestionDisplayFormatter> questionMap = new LinkedHashMap<>();
        if (questionList.isEmpty()) {
            return questionMap;
        }
        for (Question question: questionList) {
            int questionId = question.getQuestionId();
            String title = question.getTitle();
            String type = question.getType();
            LocalDate legalDeadline = question.getLegalDeadline();
            boolean isClose = question.isClose();
            QuestionDisplayFormatter questionDisplayFormatter = new QuestionDisplayFormatter(title, type, legalDeadline, isClose);
            questionMap.put(questionId, questionDisplayFormatter);
        }
        return questionMap;
    }
}
