package gateway;

import questionentities.Question;
import userentities.User;

// TODO: implement this class
public class AttorneyRepository implements AttorneyGateway{

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
    public void updateQuestionList(int userId, Question question) {

    }
    @Override
    public void addUser(User user) {

    }
}
