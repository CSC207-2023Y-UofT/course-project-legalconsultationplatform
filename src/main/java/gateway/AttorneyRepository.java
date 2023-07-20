package gateway;

import questionentities.Question;
import userentities.User;

// TODO: implement this class
public class AttorneyRepository implements UserGateway{
    final DatabaseConnection databaseConnection;

    public AttorneyRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

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
    public void updateQuestionList(int userId, Question question) {

    }
    @Override
    public void addUser(User user) {

    }
}
