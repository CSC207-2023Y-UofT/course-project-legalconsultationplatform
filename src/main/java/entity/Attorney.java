package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import businessrule.requestmodel.RegistrationData;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Attorney extends UserImp {
    @OneToMany(targetEntity = Question.class, fetch = FetchType.EAGER)
    @JsonProperty(required = true)
    private List<Question> recommendations;

    public Attorney() {
        super();
        recommendations = new ArrayList<>();
    }

    public Attorney(Builder builder) {
        super(builder);
    }
    public static class Builder extends UserImp.Builder<Builder> {
        public Builder(RegistrationData data) {
            super(data);
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

    @Override
    public boolean isClient() {
        return false;
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
