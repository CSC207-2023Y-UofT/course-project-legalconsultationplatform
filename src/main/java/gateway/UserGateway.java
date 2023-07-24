package gateway;

import userentities.User;
import questionentities.Question;

public interface UserGateway {
    boolean existsById(int userId);
    boolean isClient(int userId);
    User getUser(int userId);
    String getPassword(int userId);
    void updateQuestionList(int userId, Question question);
    void addUser(User user);
}
