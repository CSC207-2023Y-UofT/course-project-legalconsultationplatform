package userentities;

import java.util.List;
import questionentities.Question;

public interface User {
    int getUserId();
    String getUserName();
    String getPassword();
    List<Question> getQuestionsList();
    void addQuestion(Question question);
}
