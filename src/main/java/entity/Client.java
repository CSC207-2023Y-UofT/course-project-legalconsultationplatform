package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a client user who seeks legal advice and assistance by posting questions
 * to be answered by attorneys.
 */
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

    /**
     * Default constructor initializes fields and the list of questions.
     */
    public Client() {
        questionsList = new ArrayList<Question>();
    }

    /**
     * Constructs a Client instance with provided details.
     *
     * @param userId            The unique identifier of the client.
     * @param userName          The name of the client.
     * @param email             The email of the client.
     * @param password          The password of the client.
     * @param stateAbb          The state abbreviation of the client.
     * @param postalCode        The postal code of the client.
     * @param ethnicity         The ethnicity of the client.
     * @param age               The age of the client.
     * @param gender            The gender of the client.
     * @param maritalStatus     The marital status of the client.
     * @param numberOfHousehold The number of people in the household of the client.
     * @param annualIncome      The annual income of the client.
     */
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
    /**
     * Gets the user's id.
     * @return The user's id.
     */
    @Override
    public int getUserId() {
        return userId;
    }
    /**
     * Gets the user's password.
     * @return The user's password.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Gets the user's name.
     * @return The user's name.
     */
    public String getUserName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Gets the state abbreviation.
     * @return The state abbreviation.
     */
    public String getStateAbb() {
        return stateAbb;
    }

    /**
     * Gets the postal code.
     * @return The postal code.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Gets the ethnicity.
     * @return The ethnicity.
     */
    public String getEthnicity() {
        return ethnicity;
    }

    /**
     * Gets the age.
     * @return The age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the gender.
     * @return The gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets the marital status.
     * @return The marital status.
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Gets the number of household members.
     * @return The number of household members.
     */
    public int getNumberOfHousehold() {
        return numberOfHousehold;
    }

    /**
     * Gets the annual income.
     * @return The annual income.
     */
    public float getAnnualIncome() {
        return annualIncome;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Question> getQuestionsList() {
        return questionsList;
    }

    /**
     * Sets the user ID.
     * @param userId The user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Sets the user name.
     * @param userName The user name to set.
     */
    public void setUserName(String userName) {
        this.name = userName;
    }

    /**
     * Sets the email.
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the password.
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the state abbreviation.
     * @param stateAbb The state abbreviation to set.
     */
    public void setStateAbb(String stateAbb) {
        this.stateAbb = stateAbb;
    }

    /**
     * Sets the postal code.
     * @param postalCode The postal code to set.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Sets the ethnicity.
     * @param ethnicity The ethnicity to set.
     */
    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    /**
     * Sets the age.
     * @param age The age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets the gender.
     * @param gender The gender to set.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Sets the marital status.
     * @param maritalStatus The marital status to set.
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * Sets the number of household members.
     * @param numberOfHousehold The number of household members to set.
     */
    public void setNumberOfHousehold(int numberOfHousehold) {
        this.numberOfHousehold = numberOfHousehold;
    }

    /**
     * Sets the annual income.
     * @param annualIncome The annual income to set.
     */
    public void setAnnualIncome(float annualIncome) {
        this.annualIncome = annualIncome;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addQuestion(Question question) {
        if (!questionsList.contains(question)) {
            questionsList.add(question);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isClient() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isQuestionCloseable(Question question) {
        boolean isClose = question.isClose();
        return !isClose;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isQuestionSelectable(Question question) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isQuestionReplyable(Question question) {
        return !question.isClose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Client)) return false;
        Client otherClient = (Client) obj;
        return userId == otherClient.userId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[Client]: %s", name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isQuestionRateable(Question question) {
        return question.isClose();
    }
}

