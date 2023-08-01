package gateway;

import questionentities.Post;
import questionentities.Question;

import java.util.List;

public class QuestionRepo implements QuestionGateway{
    DatabaseConnection databaseConnection;

    public QuestionRepo(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void saveQuestion(Question question) {

    }

    @Override
    public boolean checkExistsByName(int questionId){
        return false;
    }

    @Override
    public Question getQuestion(int questionId) {
        return null;
    }

    @Override
    public List<Question> getAllQuestion() {
        return null;
    }

    @Override
    public List<Question> getNotTakenQuestion() {
        return null;
    }

    @Override
    public List<Question> getNotClosedQuestion() {
        return null;
    }

    @Override
    public void updateIsTaken(int questionId, boolean iaTaken) {

    }

    @Override
    public void updateTakenByAttorney(int questionId, boolean attorneyId) {

    }

    @Override
    public void updateIsClose(int questionId, boolean isClose) {

    }

    @Override
    public void updateRating(int questionId, int rating) {

    }

    @Override
    public void updatePosts(int questionId, Post post) {

    }
}
