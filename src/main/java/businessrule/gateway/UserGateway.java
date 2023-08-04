package businessrule.gateway;

import entity.User;
import entity.Question;

import java.util.List;

public interface UserGateway {
    boolean existsById(int userId);
    User getUser(int userId);
    void addUser(User user);
    void deleteUser(int userId);
    void deleteAllUser();
    boolean existsByName(String inputUserName);
    void updateQuestionList(int id, Question question);
}
