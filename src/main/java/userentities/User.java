package userentities;

import java.util.List;
import questionentities.Question;

public interface User {
    int getUserId();
    String getPassword();
    List<Question> getQuestionsList();
    void addQuestion(Question question);
    boolean isClient();
    boolean isQuestionSelectable(Question question);
}
