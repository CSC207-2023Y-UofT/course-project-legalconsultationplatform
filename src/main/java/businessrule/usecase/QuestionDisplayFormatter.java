package businessrule.usecase;

import java.time.LocalDate;

/**
 * The "QuestionDisplayFormatter" class represents a formatted display of a legal question.
 * This class is used to format and display essential information about a legal question in a user-friendly manner.
 */
public class QuestionDisplayFormatter {
    private final String title;
    private final String type;
    private final LocalDate legalDeadline;

    public QuestionDisplayFormatter(String title, String type, LocalDate legalDeadline) {
        this.title = title;
        this.type = type;
        this.legalDeadline = legalDeadline;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public LocalDate getLegalDeadline() {
        return legalDeadline;
    }
}
