package usergateway;

import questionentities.Question;
import userentities.User;
import userentities.Attorney;
import userentities.Client;

public interface ClientGateway extends UserGateway {
    boolean existsById(int userId);
    boolean isClient(int userId);
    User getUser(int userId);
    void updateQuestionList(Question question);
}
