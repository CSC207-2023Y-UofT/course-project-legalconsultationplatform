package userentities;

import questionentities.Question;
import java.util.List;
import java.util.ArrayList;

public class Attorney implements User{

    private final int userId;
    private final String password;
    private final String stateAbb;
    private final String postalCode;
    private List<Question> questionsList;

    public Attorney(int userId, String password, String stateAbb, String postalCode) {
        this.userId = userId;
        this.password = password;
        this.stateAbb = stateAbb;
        this.postalCode = postalCode;
        this.questionsList = new ArrayList<Question>();
    }
    @Override
    public int getUserId() {
        return userId;
    }
    @Override
    public String getPassword() {
        return password;
    }

    public String getStateAbb() {
        return stateAbb;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public List<Question> getQuestionsList() {
        return questionsList;
    }
    @Override
    public void addQuestion(Question question) {
        questionsList.add(question);
    }

    @Override
    public boolean isQuestionSelectable(Question question) {
        boolean isClose = question.isClose();
        boolean isTaken = question.isTaken();
        int takenByAttorney = question.getTakenByAttorney();

        if (isClose) {
            return false;
        } else if (! isTaken) {
            return true;
        } else {
            return takenByAttorney == userId;
        }
    }
}
