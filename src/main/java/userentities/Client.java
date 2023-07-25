package userentities;

import questionentities.Question;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client implements User{
    @Id
    private int userId;
    private String password;
    private String stateAbb;
    private String
            postalCode;
    private String ethnicity;
    private int age;
    private String gender;
    private String maritalStatus;
    private int numberOfHousehold;
    private float annualIncome;
    @OneToMany
    private List<Question> questionsList;

    public Client() {

    }

    public Client(int userId, String password, String stateAbb, String postalCode,
                  String ethnicity, int age, String gender, String maritalStatus,
                  int numberOfHousehold, float annualIncome){
        this.userId = userId;
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
    public List<Question> getQuestionsList() {
        return questionsList;
    }
    @Override
    public void addQuestion(Question question) {
        questionsList.add(question);
    }

}
