package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import businessrule.requestmodel.RegistrationData;
import driver.database.AttorneyRepository;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * This is a class representing attorney user.
 *
 * An Attorney is a registered user who is a legal professional, capable of answering legal questions
 * posted by clients. The Attorney has attributes such as user ID, name, email, password, state abbreviation,
 * postal code, and a list of questions associated with them.
 */
@Entity
public class Attorney extends UserImp {
    @OneToMany(targetEntity = Question.class, fetch = FetchType.EAGER)
    @JsonProperty(required = true)
    private List<Question> recommendations;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> professionals;

    /**
     * Default constructor for creating an Attorney instance.
     */
    public Attorney() {
        super();
        recommendations = new ArrayList<>();
        professionals = new HashSet<>();
    }

    /**
     * Constructor for creating an Attorney instance using a builder.
     *
     * @param builder The builder instance used for constructing the Attorney.
     */
    public Attorney(Builder builder) {
        super(builder);
    }

    /**
     * Builder class for constructing Attorney instances.
     */
    public static class Builder extends UserImp.Builder<Builder> {

        /**
         * Constructor for the Attorney builder.
         *
         * @param data The registration data for creating the Attorney.
         */
        public Builder(RegistrationData data) {
            super(data);
        }

        /**
         * Set the list of professional specialties for the Attorney.
         *
         * @param professionals The set of professional specialties.
         * @return The builder instance.
         */
        public Attorney.Builder professionals(Set<String> professionals) {
            this.data.professionals = professionals;
            return this;
        }

        @Override
        protected Attorney.Builder self() {
            return this;
        }

        @Override
        public Attorney build() {
            return new Attorney(this);
        }
    }

    public List<Question> getRecommendations() {return recommendations;}

    public void setRecommendations(ArrayList<Question> recommendations) {
        this.recommendations = recommendations;
    }

    public void addRecommendation(Question question){
        if (! recommendations.contains(question)) {
            recommendations.add(question);
        }
    }

    public Set<String> getProfessionals() {return professionals;}

    public void setProfessionals(Set<String> professionals) {this.professionals = professionals;}

    @Override
    @Transient
    public String getUserType() {
        return "Attorney";
    }


    @Override
    public boolean isQuestionCloseable(Question question){return false;}

    @Override
    public boolean isQuestionSelectable(Question question) {
        boolean isClose = question.isClose();
        boolean isTaken = question.isTaken();
        int takenByAttorney = question.getTakenByAttorney();

        if (isClose) {
            return false;
        } else if (! isTaken) {
            return true;
        } else {
            return takenByAttorney == userId;
        }
    }

    @Override
    public boolean isQuestionReplyable(Question question) {
        if (!question.isClose()){
            if (question.isTaken()) {
                return question.getTakenByAttorney() == userId;
            } else {
                question.setTaken(true);
                question.setTakenByAttorney(userId);
                question.setTakenAt(LocalDate.now());
                addQuestion(question);
                return true;
            }
        }return false;
    }

    @Override
    public boolean isQuestionRateable(Question question) {
        return false;
    }


    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Attorney)) return false;
        Attorney otherAttorney = (Attorney) obj;
        return userId == otherAttorney.userId;
    }

    @Override
    public String toString() {
        return String.format("[Attorney]: %s", name);
    }
}
