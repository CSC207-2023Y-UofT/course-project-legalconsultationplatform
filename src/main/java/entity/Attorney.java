package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

@Entity
public class Attorney implements User {
    @Id
    @JsonProperty(required = true)
    private int userId;
    private String name;
    private String email;
    private String password;
    private String stateAbb;
    @JsonProperty(required = true)
    private String postalCode;
    @OneToMany(targetEntity = Question.class, fetch = FetchType.EAGER)
    @JsonProperty(required = true)
    private List<Question> questionsList;
    @OneToMany(targetEntity = Question.class, fetch = FetchType.EAGER)
    @JsonProperty(required = true)
    private List<Question> recommendations;

    public Attorney() {
        questionsList = new ArrayList<Question>();
        recommendations = new ArrayList<Question>();
    }

    public Attorney(int userId, String name, String email, String password, String stateAbb, String postalCode) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.stateAbb = stateAbb;
        this.postalCode = postalCode;
        this.questionsList = new ArrayList<Question>();
        this.recommendations = new ArrayList<Question>();
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public String getPassword() {return password;}

    @Override
    public String getUserName() {return name;}

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

    public List<Question> getRecommendations() {return recommendations;}

    public void setUserId(int userId) {this.userId = userId;}

    public void setName(String name) {this.name = name;}

    public void setEmail(String email) {this.email = email;}

    public void setPassword(String password) {this.password = password;}

    public void setStateAbb(String stateAbb) {this.stateAbb = stateAbb;}

    public void setPostalCode(String postalCode) {this.postalCode = postalCode;}
    public void setRecommendations(ArrayList<Question> recommendations) {
        this.recommendations = recommendations;
    }

    @Override
    public void addQuestion(Question question) {
        if (! questionsList.contains(question)) {
            questionsList.add(question);
        }
    }

    public void addRecommendation(Question question){
        if (! recommendations.contains(question)) {
            recommendations.add(question);
        }
    }

    @Override
    public boolean isQuestionCloseable(Question question){
        boolean isTaken = question.isTaken();
        int takenByAttorney = question.getTakenByAttorney();
        if (! isTaken) {
            return false;
        } else {
            return takenByAttorney == userId;
        }
    }

    @Override
    public boolean isClient() {
        return false;
    }

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
        }
        return false;
    }


    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Attorney)) return false;
        Attorney otherAttorney = (Attorney) obj;
        return userId == otherAttorney.userId;
    }

    @Override
    public int hashCode() {return Objects.hashCode(userId);}

    @Override
    public String toString() {
        return String.format("[Attorney]: %s", name);
    }

    @Override
    public boolean isQuestionRateable(Question question) {
        return false;
    }
}
