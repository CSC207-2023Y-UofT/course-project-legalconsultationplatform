package userentities;

import questionentities.Question;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Attorney implements User{
    @Id
    private int userId;
    private String name;
    private String email;
    private String password;
    private String stateAbb;
    private String postalCode;
    @OneToMany
    private List<Question> questionsList;

    public Attorney() {
    }

    public Attorney(int userId, String name, String email, String password, String stateAbb, String postalCode) {
        this.userId = userId;
        this.name = name;
        this.email = email;
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
    public String getUserName() {
        return null;
    }

    @Override
    public String getPassword() {return password;}

    public String getName() {return name;}

    @Override
    public String getEmail() {return email;}

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

    public void setUserId(int userId) {this.userId = userId;}

    public void setName(String name) {this.name = name;}

    public void setEmail(String email) {this.email = email;}

    public void setPassword(String password) {this.password = password;}

    public void setStateAbb(String stateAbb) {this.stateAbb = stateAbb;}

    public void setPostalCode(String postalCode) {this.postalCode = postalCode;}

    @Override
    public void addQuestion(Question question) {
        questionsList.add(question);
    }

    @Override
    public boolean isClient() {
        return false;
    }

    @Override
    public boolean isQuestionReplyable(Question question) {
        return false;
    }
}
