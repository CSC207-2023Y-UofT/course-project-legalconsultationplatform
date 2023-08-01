package gateway;

import userentities.User;
import questionentities.Question;

public interface UserGateway {
    boolean existsById(int userId);
    boolean existsByName(String userName);
    boolean isClient(int userId);
    User getUser(int userId);
    void updateQuestionList(int userId, Question question);
    void addUser(User user);
}
