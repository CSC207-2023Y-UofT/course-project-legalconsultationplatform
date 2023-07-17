package questiongateway;

import questionentities.Post;
import questionentities.Question;
import java.util.List;

public interface QuestionGateway {
    void saveQuestion(Question question);
    Question getQuestion(int questionId);
    List<Question> getAllQuestion();
    List<Question> getNotTakenQuestion();
    List<Question> getNotClosedQuestion();
    void updateIsTaken(int questionId, boolean iaTaken);
    void updateTakenByAttorney(int questionId, boolean attorneyId);
    void updateIsClose(int questionId, boolean isClose);
    void updateRating(int questionId, int rating);
    void updatePosts(int questionId, Post post);
}
