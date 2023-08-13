package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import businessrule.requestmodel.RegistrationData;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class respresents a user entity with common properties and behaviors.
 */
@Entity
public abstract class UserImp implements User{
    @Id
    @JsonProperty(required = true)
    protected int userId;
    protected String name;
    protected String email;
    protected String password;
    protected String stateAbb;
    @JsonProperty(required = true)
    protected String postalCode;
    @OneToMany(targetEntity = Question.class, fetch = FetchType.EAGER)
    @JsonProperty(required = true)
    protected List<Question> questionsList;

    /**
     * Default constructor for the UserImp class. Initializes the questions list.
     */
    public UserImp() {questionsList = new ArrayList<Question>();}


    protected UserImp(Builder<?> builder) {
        this.userId = builder.data.userId;
        this.name = builder.data.userName;
        this.email = builder.data.email;
        this.password = builder.data.password;
        this.stateAbb = builder.data.stateAbb;
        this.postalCode = builder.data.postalCode;
        this.questionsList = new ArrayList<>();
    }

    /**
     * Abstract builder class for creating instances of the UserImp class.
     */
    public static abstract class Builder<T extends Builder<T>> {
        protected RegistrationData data;

        /**
         * Constructor for the Builder class.
         *
         * @param data The registration data for the user.
         */
        public Builder(RegistrationData data) {
            this.data = data;
        }
        /**
         * Set the user ID for the user being built.
         *
         * @param userId The user ID.
         * @return The Builder instance.
         */
        public T userId(int userId) {
            this.data.userId = userId;
            return self();
        }

        /**
         * Set the user name for the user being built.
         *
         * @param userName The user name.
         * @return The Builder instance.
         */
        public T userName(String userName) {
            this.data.userName = userName;
            return self();
        }
        /**
         * Set the password for the user being built.
         *
         * @param password The password.
         * @return The Builder instance.
         */
        public T password(String password) {
            this.data.password = password;
            return self();
        }
        /**
         * Set the email for the user being built.
         *
         * @param email The email.
         * @return The Builder instance.
         */
        public T email(String email) {
            this.data.email = email;
            return self();
        }
        /**
         * Set the state abbreviation for the user being built.
         *
         * @param stateAbb The state abbreviation.
         * @return The Builder instance.
         */
        public T stateAbb(String stateAbb) {
            this.data.stateAbb = stateAbb;
            return self();
        }
        /**
         * Set the postal code for the user being built.
         *
         * @param postalCode The postal code.
         * @return The Builder instance.
         */
        public T postalCode(String postalCode) {
            this.data.postalCode = postalCode;
            return self();
        }

        protected abstract T self();
        /**
         * Build and return a UserImp instance based on the Builder configuration.
         *
         * @return A new UserImp instance.
         */
        public abstract UserImp build();
    }

    /**
     * Retrieves the user ID of this user.
     *
     * @return The user ID of this user.
     */
    @Override
    public int getUserId() {
        return userId;
    }

    /**
     * Retrieves the user name of this user.
     *
     * @return The user name of this user.
     */
    @Override
    public String getUserName() {
        return name;
    }

    /**
     * Retrieves the email address of this user.
     *
     * @return The email address of this user.
     */
    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {return password;}
    /**
     * Get the state abbreviation of the user.
     *
     * @return The state abbreviation.
     */
    public String getStateAbb() {return stateAbb;}
    /**
     * Get the postal code of the user.
     *
     * @return The postal code.
     */
    public String getPostalCode() {return postalCode;}
    /**
     * Retrieves the list of questions associated with this user.
     *
     * @return The list of questions associated with this user.
     */
    @Override
    public List<Question> getQuestionsList() {return questionsList;}
    /**
     * Add a question to the user's questions list.
     *
     * @param question The question to be added.
     */
    @Override
    public void addQuestion(Question question) {
        if (!questionsList.contains(question)) {
            questionsList.add(question);
        }
    }
    /**
     * Set the user ID for the user.
     *
     * @param value The user ID value to set.
     */
    @Override
    public void setUserId(int value) {this.userId = value;}
    /**
     * Set the user name for the user.
     *
     * @param name The user name to set.
     */
    @Override
    public void setUserName(String name) {this.name = name;}
    /**
     * Set the email for the user.
     *
     * @param value The email value to set.
     */
    @Override
    public void setEmail(String value) {this.email = value;}
    /**
     * Set the state abbreviation for the user.
     *
     * @param value The state abbreviation to set.
     */
    @Override
    public void setStateAbb(String value) {this.stateAbb = value;}
    /**
     * Set the postal code for the user.
     *
     * @param value The postal code to set.
     */
    @Override
    public void setPostalCode(String value) {this.postalCode = value;}
    /**
     * Set the password for the user.
     *
     * @param value The password value to set.
     */
    @Override
    public void setPassword(String value) {this.password = value;}
    /**
     * Returns the hash code value for this UserImp object. The hash code is generated based on the user ID.
     *
     * @return The hash code value for this object.
     */
    @Override
    public int hashCode() {return Objects.hashCode(userId);}
    /**
     * Check if the user is a client.
     *
     * @return True if the user is a client, otherwise false.
     */
    public abstract boolean isClient();
    /**
     * Check if a question can be closed for the user.
     *
     * @param question The question to be checked.
     * @return True if the question can be closed, otherwise false.
     */
    public abstract boolean isQuestionCloseable(Question question);
    /**
     * Check if a question is selectable for the user.
     *
     * @param question The question to be checked.
     * @return True if the question is selectable, otherwise false.
     */
    public abstract boolean isQuestionSelectable(Question question);
    /**
     * Check if a question can be replied to by the user.
     *
     * @param question The question to be checked.
     * @return True if the question can be replied to, otherwise false.
     */
    public abstract boolean isQuestionReplyable(Question question);
    /**
     * Check if a question is rateable by the user.
     *
     * @param question The question to be checked.
     * @return True if the question is rateable, otherwise false.
     */
    public abstract boolean isQuestionRateable(Question question);
}
