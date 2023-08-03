package entity;

import java.util.List;

public interface User {
    int getUserId();
    String getUserName();
    String getPassword();
    String getEmail();
    List<Question> getQuestionsList();
    void addQuestion(Question question);
    boolean isClient();
    boolean isQuestionCloseable(Question question);
    boolean isQuestionSelectable(Question question);
    boolean isQuestionReplyable(Question question);
    boolean isQuestionRateable(Question question);
}
