package askquestion;

import java.time.LocalDate;

public class QuestionRequestModel {
    private String questionCategory;
    private LocalDate createAt;
    private int askedByClient;
    private LocalDate legalDeadline = null;

    public QuestionRequestModel(String questionCategory, LocalDate createAt, int askedByClient, LocalDate legalDeadline) {
        this.questionCategory = questionCategory;
        this.createAt = createAt;
        this.askedByClient = askedByClient;
        this.legalDeadline = legalDeadline;
    }

    public QuestionRequestModel(String questionCategory, LocalDate createAt, int askedByClient) {
        this.questionCategory = questionCategory;
        this.createAt = createAt;
        this.askedByClient = askedByClient;
    }

    public String getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public int getAskedByClient() {
        return askedByClient;
    }

    public void setAskedByClient(int askedByClient) {
        this.askedByClient = askedByClient;
    }

    public LocalDate getLegalDeadline() {
        return legalDeadline;
    }

    public void setLegalDeadline(LocalDate legalDeadline) {
        this.legalDeadline = legalDeadline;
    }
}
