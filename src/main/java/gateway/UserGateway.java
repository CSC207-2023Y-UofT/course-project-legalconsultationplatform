package gateway;

import userentities.User;
import questionentities.Question;

public interface UserGateway {
    boolean existsById(int userId);
    boolean isClient(int userId);
    User getUser(int userId);
    String getPassword(int userId);
    void addUser(User user);
    void updateQuestionList(int userId, Question question);
    void updateAnswerList(int userId, Question question);
}
