package usergateway;

import questionentities.Question;
import userentities.User;

public interface AttorneyGateway extends UserGateway{
    @Override
    boolean existsById(int userId);
    @Override
    boolean isClient(int userId);
    @Override
    User getUser(int userId);
    @Override
    void updateQuestionList(Question question);
}
