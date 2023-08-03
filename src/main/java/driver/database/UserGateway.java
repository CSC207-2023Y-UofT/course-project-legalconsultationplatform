package driver.database;

import entity.User;
import entity.Question;

public interface UserGateway {
    boolean existsById(int userId);
    User getUser(int userId);
    void updateQuestionList(int userId, Question question);
    void addUser(User user);
    void deleteUser(int userId);

    boolean existsByName(String inputUserName);
}
