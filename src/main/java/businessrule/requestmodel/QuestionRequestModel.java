package businessrule.requestmodel;

import java.time.LocalDate;

public class QuestionRequestModel {
    private final String questionCategory;
    private final String title;
    private final LocalDate createAt;
    private final LocalDate legalDeadline;

    public QuestionRequestModel(String questionCategory, String title, LocalDate createAt, LocalDate legalDeadline) {
        this.questionCategory = questionCategory;
        this.title = title;
        this.createAt = createAt;
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

    public LocalDate getLegalDeadline() {
        return legalDeadline;
    }
}
