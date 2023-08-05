package businessrule.gateway;

import entity.Post;
import entity.Question;

import java.time.LocalDate;
import java.util.List;

public interface QuestionGateway {
    void saveQuestion(Question question);
    boolean existsById(int questionId);
    Question getQuestion(int questionId);
    List<Question> getAllQuestion();
    List<Question> getNotTakenQuestion();
    List<Question> getNotClosedQuestion();
    List<Post> getAllPostOfQuestion(int questionId);
    void updateIsTaken(int questionId, boolean iaTaken);
    void updateTakenByAttorney(int questionId, int attorneyId);
    void updateIsClose(int questionId, boolean isClose);
    void updateRating(int questionId, int rating);
    void updateTakenAt(int questionId, LocalDate time);
    void deleteQuestion(int QuestionId);
    void deleteAllQuestion();
    void updatePosts(int id, Post post);
}
