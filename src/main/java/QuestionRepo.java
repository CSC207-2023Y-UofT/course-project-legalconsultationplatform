import question.Question;
import ask_question_use_case.QuestionGateway;

import java.util.HashMap;
import java.util.Map;

public class QuestionRepo implements QuestionGateway{
    final private Map<Integer, Question> questions = new HashMap<>();

    /**
     * @param question the data to save
     */
    public void createQuestion(Question question) {
        System.out.println("Create question " + question.getQuestionId());
        questions.put(question.getQuestionId(), question);
    }

    /**
     * @return the question related to the questionId
     */
    public Question getQuestion(Integer questionId) {
        System.out.println("Get question " + questionId);
        return questions.get(questionId);
    }

    /**
     * @param question the data to save
     */
    public void updateQuestion(Question question) {
        System.out.println("Update question " + question.getQuestionId());
        questions.put(question.getQuestionId(), question);
    }
}


