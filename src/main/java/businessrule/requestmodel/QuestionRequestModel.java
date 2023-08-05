package businessrule.requestmodel;

import java.time.LocalDate;
/**
 * This class represents a post request model for creating a new question.
 */
public class QuestionRequestModel {
    private final String questionCategory;
    private final String title;
    private final LocalDate createAt;
    private final int askedByClient;
    private final LocalDate legalDeadline;

    public QuestionRequestModel(String questionCategory, String title, LocalDate createAt, int askedByClient, LocalDate legalDeadline) {
        this.questionCategory = questionCategory;
        this.title = title;
        this.createAt = createAt;
        this.askedByClient = askedByClient;
        this.legalDeadline = legalDeadline;
    }

    public String getQuestionCategory() {
        return questionCategory;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public int getAskedByClient() {
        return askedByClient;
    }

    public LocalDate getLegalDeadline() {
        return legalDeadline;
    }
}
