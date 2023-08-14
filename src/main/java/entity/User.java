package entity;

import java.util.List;

import javax.persistence.Entity;

public interface User {
    int getUserId();
    String getUserName();
    String getPassword();
    String getEmail();
    List<Question> getQuestionsList();
    void addQuestion(Question question);
    void setUserName(String name);
    void setEmail(String value);
    void setStateAbb(String value);
    void setPostalCode(String value);
    void setPassword(String value);
    void setUserId(int value);
    String getUserType();
    boolean isQuestionCloseable(Question question);
    boolean isQuestionSelectable(Question question);
    boolean isQuestionReplyable(Question question);
    boolean isQuestionRateable(Question question);

}
