package questiongateway;

import questionentities.Question;

public interface QuestionGateway {

    Question getQuestion(int questionId);

    void updateQuestion(Question question);
}
