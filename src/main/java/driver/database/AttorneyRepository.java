package driver.database;

import businessrule.gateway.AttorneyGateway;
import entity.Question;
import entity.User;

/**
 * This class represents an Attorney Repository, responsible for managing Attorney users' data and
 * accessing attorney-related data.
 */
// TODO: implement this class
public class AttorneyRepository implements AttorneyGateway {

    @Override
    public boolean existsById(int userId) {
        return false;
    }

    @Override
    public boolean existsByName(String userName) {
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

    @Override
    public void deleteUser(int userId) {

    }
}
