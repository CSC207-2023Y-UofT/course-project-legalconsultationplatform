package ask_question_use_case;

import question.Question;

public interface QuestionGateway {
    void createQuestion(Question question);

    Question getQuestion(Integer questionId);

    void updateQuestion(Question question);
}
