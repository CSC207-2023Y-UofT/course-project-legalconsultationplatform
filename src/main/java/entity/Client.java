package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Client implements User {
    @Id
    @JsonProperty(required = true)
    private int userId;
    private String name;
    private String email;
    private String password;
    private String stateAbb;
    @JsonProperty(required = true)
    private String postalCode;
    @JsonProperty(required = true)
    private String ethnicity;
    @JsonProperty(required = true)
    private int age;
    @JsonProperty(required = true)
    private String gender;
    @JsonProperty(required = true)
    private String maritalStatus;
    @JsonProperty(required = true)
    private int numberOfHousehold;
    @JsonProperty(required = true)
    private float annualIncome;
    @OneToMany(targetEntity = Question.class, fetch = FetchType.EAGER)
    @JsonProperty(required = true)
    private List<Question> questionsList;

    public Client() {
        questionsList = new ArrayList<Question>();
    }

    public Client(int userId, String userName, String email, String password, String stateAbb, String postalCode,
                  String ethnicity, int age, String gender, String maritalStatus, int numberOfHousehold,
                  float annualIncome) {
        this.userId = userId;
        this.name = userName;
        this.email = email;
        this.password = password;
        this.stateAbb = stateAbb;
        this.postalCode = postalCode;
        this.ethnicity = ethnicity;
        this.age = age;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.numberOfHousehold = numberOfHousehold;
        this.annualIncome = annualIncome;
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

    public String getUserName() {return name;}

    @Override
    public String getEmail() {return email;}

    public String getStateAbb() {
        return stateAbb;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public int getNumberOfHousehold() {
        return numberOfHousehold;
    }

    public float getAnnualIncome() {
        return annualIncome;
    }

    @Override
    public List<Question> getQuestionsList() {return questionsList;}

    public void setUserId(int userId) {this.userId = userId;}

    public void setUserName(String userName) {this.name = userName;}

    public void setEmail(String email) {this.email = email;}

    public void setPassword(String password) {this.password = password;}

    public void setStateAbb(String stateAbb) {this.stateAbb = stateAbb;}

    public void setPostalCode(String postalCode) {this.postalCode = postalCode;}

    public void setEthnicity(String ethnicity) {this.ethnicity = ethnicity;}

    public void setAge(int age) {this.age = age;}

    public void setGender(String gender) {this.gender = gender;}

    public void setMaritalStatus(String maritalStatus) {this.maritalStatus = maritalStatus;}

    public void setNumberOfHousehold(int numberOfHousehold) {this.numberOfHousehold = numberOfHousehold;}

    public void setAnnualIncome(float annualIncome) {this.annualIncome = annualIncome;}

    @Override
    public void addQuestion(Question question) {
        if (! questionsList.contains(question)) {
            questionsList.add(question);
        }
    }

    @Override
    public boolean isClient() {
        return true;
    }

    @Override
    public boolean isQuestionCloseable(Question question) {
        boolean isClose = question.isClose();
        return !isClose;
    }

    @Override
    public boolean isQuestionSelectable(Question question) {
        return true;
    }

    @Override
    public boolean isQuestionReplyable(Question question) {
        return !question.isClose();
    }

    @Override
    public int hashCode() {return Objects.hashCode(userId);}

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Client)) return false;
        Client otherClient = (Client) obj;
        return userId == otherClient.userId;
    }

    @Override
    public String toString() {
        return String.format("[Client]: %s", name);
    }

    @Override
    public boolean isQuestionRateable(Question question) {
        return question.isClose();
    }
}


