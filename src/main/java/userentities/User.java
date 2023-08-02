package userentities;

import java.util.List;
import questionentities.Question;

import javax.persistence.Entity;

public interface User {
    int getUserId();
    String getPassword();
    String getEmail();
    List<Question> getQuestionsList();
    void addQuestion(Question question);
    boolean isClient();
    boolean isQuestionCloseable(Question question);
    boolean isQuestionSelectable(Question question);
    boolean isQuestionReplyable(Question question);
}
