package businessrule.usecase;

import entity.Question;
import entity.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class QuestionMapConstructor {
    protected Map<Integer, QuestionDisplayFormatter> constructQuestionMap(List<Question> questionList) {
        Map<Integer, QuestionDisplayFormatter> questionMap = new HashMap<>();
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
