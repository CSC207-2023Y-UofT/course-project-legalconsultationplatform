package driver.database;

import entity.Post;
import entity.Question;

import java.time.LocalDate;
import java.util.List;

public interface QuestionGateway {
    void saveQuestion(Question question);
    boolean checkExistsByName(int questionId);
    Question getQuestion(int questionId);
    List<Question> getAllQuestion();
    List<Question> getNotTakenQuestion();
    List<Question> getNotClosedQuestion();
    void updateIsTaken(int questionId, boolean iaTaken);
    void updateTakenByAttorney(int questionId, int attorneyId);
    void updateIsClose(int questionId, boolean isClose);
    void updateRating(int questionId, int rating);
    void updatePosts(int questionId, Post post);
    void updateTakenAt(int questionId, LocalDate time);
    public void deleteQuestion(int postId);
}
