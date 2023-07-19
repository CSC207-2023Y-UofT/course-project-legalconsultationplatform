package askquestion;

import java.time.LocalDate;

public class QuestionResponseModel {
    int questionId;
    LocalDate createAt;
    boolean message;

    public QuestionResponseModel(int questionId, LocalDate createAt, boolean message){
        this.questionId = questionId;
        this.createAt = createAt;
        this.message = message;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public boolean getMessage(){
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }
}
