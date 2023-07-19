package usergateway;

import questionentities.Question;
import userentities.User;

public class ClientRepository implements UserGateway{
    @Override
    public boolean existsById(int userId) {
        return false;
    }

    @Override
    public boolean isClient(int userId) {
        return false;
    }

    @Override
    public User getUser(int userId) {
        return null;
    }

    @Override
    public String getPassword(int userId) {
        return null;
    }

    @Override
    public void updateQuestionList(Question question) {

    }
}
