package businessrule.gateway;

import entity.Post;
import entity.Question;
import entity.User;

import java.time.LocalDate;
import java.util.List;

public interface QuestionGateway extends GenericGateway<Question>{
    List<Question> getNotTakenQuestion();
    List<Question> getNotClosedQuestion();
    List<Post> getAllPostOfQuestion(int questionId);
    void updateIsTaken(int questionId, boolean iaTaken);
    void updateTakenByAttorney(int questionId, int attorneyId);
    void updateIsClose(int questionId, boolean isClose);
    void updateRating(int questionId, int rating);
    void updateTakenAt(int questionId, LocalDate time);
    void updatePosts(int id, Post post);
    @Override
    Question get(int id);
}
