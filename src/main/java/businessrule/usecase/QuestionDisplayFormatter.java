package businessrule.usecase;

import java.time.LocalDate;

/**
 * This class represents a formatted display of a legal question.
 * It is used to format and display essential information about a legal question in a user-friendly manner.
 */
public class QuestionDisplayFormatter {
    private final String title;
    private final String type;
    private final LocalDate legalDeadline;

    /**
     * Constructs a QuestionDisplayFormatter instance with the specified parameters.
     *
     * @param title         The title of the legal question.
     * @param type          The type or category of the legal question.
     * @param legalDeadline The legal deadline associated with the question.
     */
    public QuestionDisplayFormatter(String title, String type, LocalDate legalDeadline) {
        this.title = title;
        this.type = type;
        this.legalDeadline = legalDeadline;
    }

    /**
     * Gets the title of the legal question.
     *
     * @return The title of the legal question.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the type or category of the legal question.
     *
     * @return The type or category of the legal question.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the legal deadline associated with the question.
     *
     * @return The legal deadline associated with the question.
     */
    public LocalDate getLegalDeadline() {
        return legalDeadline;
    }
}