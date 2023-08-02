package gateway;

import userentities.User;
import questionentities.Question;

public interface UserGateway {
    boolean existsById(int userId);
    User getUser(int userId);
    void updateQuestionList(int userId, Question question);
    void addUser(User user);
    void deleteUser(int userId);

    boolean existsByName(String inputUserName);
}
