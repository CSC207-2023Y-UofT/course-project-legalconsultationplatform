package usecases.requests;

import java.time.LocalDate;

/**
 * This class represents a post request model for creating a new question.
 */
public class QuestionRequestModel {
    private final String questionCategory;
    private final String title;
    private final LocalDate createAt;
    private final LocalDate legalDeadline;

    /**
     * Constructs a new QuestionRequestModel with the specified parameters.
     *
     * @param questionCategory The category of the question.
     * @param title            The title of the question.
     * @param createAt         The creation date of the question.
     * @param legalDeadline    The legal deadline for the question.
     */
    public QuestionRequestModel(String questionCategory, String title, LocalDate createAt, LocalDate legalDeadline) {
        this.questionCategory = questionCategory;
        this.title = title;
        this.createAt = createAt;
        this.legalDeadline = legalDeadline;
    }

    /**
     * Get the category of the question.
     *
     * @return The question category.
     */
    public String getQuestionCategory() {
        return questionCategory;
    }

    /**
     * Get the title of the question.
     *
     * @return The question title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the creation date of the question.
     *
     * @return The creation date.
     */
    public LocalDate getCreateAt() {
        return createAt;
    }

    /**
     * Get the legal deadline for the question.
     *
     * @return The legal deadline.
     */
    public LocalDate getLegalDeadline() {
        return legalDeadline;
    }
}
