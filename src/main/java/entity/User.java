package entity;

/**
 * The "User" interface represents a generic user in the system.
 * This interface defines common methods that are shared by different types of users in the application,
 */

import java.util.List;

import javax.persistence.Entity;

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
