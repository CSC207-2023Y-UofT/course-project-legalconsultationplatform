package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import businessrule.requestmodel.RegistrationData;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Client extends UserImp {
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

    /**
     * Default constructor for the Client class.
     */
    public Client() {super();}

    /**
     * Constructor to create a Client instance using the provided Builder.
     *
     * @param builder The Builder instance containing client data.
     */
    private Client(Builder builder) {
        super(builder);
        this.ethnicity = builder.data.ethnicity;
        this.age = builder.data.age;
        this.gender = builder.data.gender;
        this.maritalStatus = builder.data.maritalStatus;
        this.numberOfHousehold = builder.data.numberOfHousehold;
        this.annualIncome = builder.data.annualIncome;
        this.questionsList = new ArrayList<Question>();
    }

    /**
     * Builder class for creating instances of the Client class.
     */
    public static class Builder extends UserImp.Builder<Builder> {
        /**
         * Constructor for the Builder class.
         *
         * @param data The registration data for the client.
         */
        public Builder(RegistrationData data) {
            super(data);
        }

        /**
         * Set the ethnicity for the client.
         *
         * @param ethnicity The ethnicity of the client.
         * @return The Builder instance.
         */
        public Builder ethnicity(String ethnicity) {
            this.data.ethnicity = ethnicity;
            return this;
        }

        /**
         * Set the age for the client.
         *
         * @param age The age of the client.
         * @return The Builder instance.
         */
        public Builder age(int age) {
            this.data.age = age;
            return this;
        }

        /**
         * Set the gender for the client.
         *
         * @param gender The gender of the client.
         * @return The Builder instance.
         */
        public Builder gender(String gender) {
            this.data.gender = gender;
            return this;
        }

        /**
         * Set the marital status for the client.
         *
         * @param maritalStatus The marital status of the client.
         * @return The Builder instance.
         */
        public Builder maritalStatus(String maritalStatus) {
            this.data.maritalStatus = maritalStatus;
            return this;
        }

        /**
         * Set the number of household members for the client.
         *
         * @param numberOfHousehold The number of household members of the client.
         * @return The Builder instance.
         */
        public Builder numberOfHousehold(int numberOfHousehold) {
            this.data.numberOfHousehold = numberOfHousehold;
            return this;
        }

        /**
         * Set the annual income for the client.
         *
         * @param annualIncome The annual income of the client.
         * @return The Builder instance.
         */
        public Builder annualIncome(float annualIncome) {
            this.data.annualIncome = annualIncome;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Builder self() {
            return this;
        }

        /**
         * Build and return a Client instance based on the Builder configuration.
         *
         * @return A new Client instance.
         */
        @Override
        public Client build() {
            return new Client(this);
        }
    }

    /**
     * Get the ethnicity of the client.
     *
     * @return The ethnicity of the client.
     */
    public String getEthnicity() {
        return ethnicity;
    }

    /**
     * Get the age of the client.
     *
     * @return The age of the client.
     */
    public int getAge() {
        return age;
    }

    /**
     * Get the gender of the client.
     *
     * @return The gender of the client.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Get the marital status of the client.
     *
     * @return The marital status of the client.
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Get the number of household members of the client.
     *
     * @return The number of household members of the client.
     */
    public int getNumberOfHousehold() {
        return numberOfHousehold;
    }

    /**
     * Get the annual income of the client.
     *
     * @return The annual income of the client.
     */
    public float getAnnualIncome() {
        return annualIncome;
    }

    public void setEthnicity(String ethnicity) {this.ethnicity = ethnicity;}

    public void setAge(int age) {this.age = age;}

    public void setGender(String gender) {this.gender = gender;}

    public void setMaritalStatus(String maritalStatus) {this.maritalStatus = maritalStatus;}

    public void setNumberOfHousehold(int numberOfHousehold) {this.numberOfHousehold = numberOfHousehold;}

    public void setAnnualIncome(float annualIncome) {this.annualIncome = annualIncome;}

    /**
     * Provide the string representation of the user type.
     *
     * @return Client
     */
    @Override
    @Transient
    public String getUserType() {
        return "Client";
    }

    /**
     * Check if a question can be closed for the client.
     *
     * @param question The question to be checked.
     * @return True if the question can be closed, otherwise false.
     */
    @Override
    public boolean isQuestionCloseable(Question question) {
        boolean isClose = question.isClose();
        boolean isTaken = question.isTaken();
        return (isTaken && !isClose);
    }

    /**
     * Check if a question is selectable for the client.
     *
     * @param question The question to be checked.
     * @return Always returns true, indicating that the question is selectable.
     */
    @Override
    public boolean isQuestionSelectable(Question question) {
        return true;
    }

    /**
     * Check if a question can be replied to by the client.
     *
     * @param question The question to be checked.
     * @return True if the question can be replied to, otherwise false.
     */
    @Override
    public boolean isQuestionReplyable(Question question) {
        return !question.isClose();
    }

    /**
     * Check if this client instance is equal to another object.
     *
     * @param obj The object to compare with this client instance.
     * @return True if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Client)) return false;
        Client otherClient = (Client) obj;
        return userId == otherClient.userId;
    }

    /**
     * Get a string representation of the client.
     *
     * @return A formatted string representing the client.
     */
    @Override
    public String toString() {
        return String.format("[Client]: %s", name);
    }

    /**
     * Check if a question is rateable for the client.
     *
     * @param question The question to be checked.
     * @return True if the question is rateable, otherwise false.
     */
    @Override
    public boolean isQuestionRateable(Question question) {
        return question.isClose();
    }
}


