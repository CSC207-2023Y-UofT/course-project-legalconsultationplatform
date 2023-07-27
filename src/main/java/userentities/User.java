package userentities;

import java.util.List;
import questionentities.Question;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public interface User {
    int getUserId();
    String getPassword();
    String getEmail();
    @OneToMany
    List<Question> getQuestionsList();
    void addQuestion(Question question);
    boolean isClient();
    boolean isQuestionCloseable(Question question);
    boolean isQuestionSelectable(Question question);
}
