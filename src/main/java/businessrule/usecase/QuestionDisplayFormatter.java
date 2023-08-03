package businessrule.usecase;

import java.time.LocalDate;

public class QuestionDisplayFormatter {
    private String title;
    private String type;
    private LocalDate legalDeadline;

    public QuestionDisplayFormatter(String title, String type, LocalDate legalDeadline) {
        this.title = title;
        this.type = type;
        this.legalDeadline = legalDeadline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getLegalDeadline() {
        return legalDeadline;
    }

    public void setLegalDeadline(LocalDate legalDeadline) {
        this.legalDeadline = legalDeadline;
    }
}
