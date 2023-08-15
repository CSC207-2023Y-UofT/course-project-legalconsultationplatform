package adapter.controller;

import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.inputboundary.QuestionInputBoundary;
import businessrule.requestmodel.QuestionRequestModel;
import businessrule.responsemodel.UserResponseModel;

import java.time.LocalDate;

/**
 * This class represents a controller for handling questions.
 * It interacts with the business logic to create questions and retrieve responses.
 */

public class QuestionControl {
    private final QuestionInputBoundary questionInput;

    /**
     * Constructor for QuestionControl.
     *
     * @param questionInput The input boundary for handling questions.
     */
    public QuestionControl(QuestionInputBoundary questionInput){
        this.questionInput = questionInput;
    }

    /**
     * Creates a new question based on the provided information.
     *
     * @param questionCategory The category of the question.
     * @param title The title of the question.
     * @param createAt The creation date of the question.
     * @param legalDeadline The legal deadline for the question.
     * @return The response model containing the result of creating the question.
     */
    public TheQuestionResponseModel createQuestion(String questionCategory, String title, LocalDate createAt, LocalDate legalDeadline){
        QuestionRequestModel requestModel = new QuestionRequestModel(questionCategory, title, createAt, legalDeadline);
        return questionInput.createQuestion(requestModel);
    }
}
