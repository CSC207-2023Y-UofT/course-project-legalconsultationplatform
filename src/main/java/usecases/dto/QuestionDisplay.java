package usecases.dto;

import java.time.LocalDate;

/**
 * This class represents the formatted display information for a question.
 * It encapsulates attributes such as the question title, type, legal deadline, and whether the question is closed.
 */
public class QuestionDisplay {
    private final String title;
    private final String type;
    private final LocalDate legalDeadline;
    private final boolean isClose;

    /**
     * Constructs a `QuestionDisplayFormatter` object with the specified attributes.
     *
     * @param title The title of the question.
     * @param type The type of the question.
     * @param legalDeadline The legal deadline for the question.
     * @param isClose Indicates if the question is closed.
     */
    public QuestionDisplay(String title, String type, LocalDate legalDeadline, boolean isClose) {
        this.title = title;
        this.type = type;
        this.legalDeadline = legalDeadline;
        this.isClose = isClose;
    }

    /**
     * Gets the title of the question.
     *
     * @return The question title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the type of the question.
     *
     * @return The question type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the legal deadline for the question.
     *
     * @return The legal deadline.
     */
    public LocalDate getLegalDeadline() {
        return legalDeadline;
    }

    /**
     * Checks if the question is closed.
     *
     * @return `true` if the question is closed, otherwise `false`.
     */
    public boolean isClose() {
        return isClose;
    }
}