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

    public Client() {super();}

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

    public static class Builder extends UserImp.Builder<Builder> {
        public Builder(RegistrationData data) {
            super(data);
        }

        public Builder ethnicity(String ethnicity) {
            this.data.ethnicity = ethnicity;
            return this;
        }

        public Builder age(int age) {
            this.data.age = age;
            return this;
        }

        public Builder gender(String gender) {
            this.data.gender = gender;
            return this;
        }

        public Builder maritalStatus(String maritalStatus) {
            this.data.maritalStatus = maritalStatus;
            return this;
        }

        public Builder numberOfHousehold(int numberOfHousehold) {
            this.data.numberOfHousehold = numberOfHousehold;
            return this;
        }

        public Builder annualIncome(float annualIncome) {
            this.data.annualIncome = annualIncome;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Client build() {
            return new Client(this);
        }
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

    public void setEthnicity(String ethnicity) {this.ethnicity = ethnicity;}

    public void setAge(int age) {this.age = age;}

    public void setGender(String gender) {this.gender = gender;}

    public void setMaritalStatus(String maritalStatus) {this.maritalStatus = maritalStatus;}

    public void setNumberOfHousehold(int numberOfHousehold) {this.numberOfHousehold = numberOfHousehold;}

    public void setAnnualIncome(float annualIncome) {this.annualIncome = annualIncome;}

    @Override
    @Transient
    public String getUserType() {
        return "Client";
    }

    @Override
    public boolean isQuestionCloseable(Question question) {
        boolean isClose = question.isClose();
        boolean isTaken = question.isTaken();
        return (isTaken && !isClose);
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


