package businessrule.gateway;

import entity.Question;
import entity.User;

public interface UserGateway<T extends User> extends GenericGateway<T>{
    boolean existsByName(String inputUserName);
    void updateQuestionList(int id, Question question);
    @Override
    User get(int id);
}
