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

    public UserImp() {questionsList = new ArrayList<Question>();}

    protected UserImp(Builder<?> builder) {
        this.userId = builder.data.userId;
        this.name = builder.data.userName;
        this.email = builder.data.email;
        this.password = builder.data.password;
        this.stateAbb = builder.data.stateAbb;
        this.postalCode = builder.data.postalCode;
    }

    public static abstract class Builder<T extends Builder<T>> {
        protected RegistrationData data;

        public Builder(RegistrationData data) {
            this.data = data;
        }
        public T userId(int userId) {
            this.data.userId = userId;
            return self();
        }

        public T userName(String userName) {
            this.data.userName = userName;
            return self();
        }

        public T password(String password) {
            this.data.password = password;
            return self();
        }

        public T email(String email) {
            this.data.email = email;
            return self();
        }

        public T stateAbb(String stateAbb) {
            this.data.stateAbb = stateAbb;
            return self();
        }

        public T postalCode(String postalCode) {
            this.data.postalCode = postalCode;
            return self();
        }

        protected abstract T self();
        public abstract UserImp build();
    }

    @Override
    public int getUserId() {return userId;}

    @Override
    public String getUserName() {return name;}

    @Override
    public String getEmail() {return email;}

    @Override
    public String getPassword() {return password;}

    public String getStateAbb() {return stateAbb;}

    public String getPostalCode() {return postalCode;}

    @Override
    public List<Question> getQuestionsList() {return questionsList;}

    @Override
    public void addQuestion(Question question) {
        if (!questionsList.contains(question)) {
            questionsList.add(question);
        }
    }
    @Override
    public void setUserId(int value) {this.userId = value;}

    @Override
    public void setUserName(String name) {this.name = name;}

    @Override
    public void setEmail(String value) {this.email = value;}

    @Override
    public void setStateAbb(String value) {this.stateAbb = value;}

    @Override
    public void setPostalCode(String value) {this.postalCode = value;}

    @Override
    public void setPassword(String value) {this.password = value;}

    @Override
    public int hashCode() {return Objects.hashCode(userId);}

    public abstract boolean isClient();

    public abstract boolean isQuestionCloseable(Question question);

    public abstract boolean isQuestionSelectable(Question question);

    public abstract boolean isQuestionReplyable(Question question);

    public abstract boolean isQuestionRateable(Question question);
}
