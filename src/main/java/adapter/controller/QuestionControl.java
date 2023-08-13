package adapter.controller;

import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.inputboundary.QuestionInputBoundary;
import businessrule.requestmodel.QuestionRequestModel;
import businessrule.responsemodel.UserResponseModel;

import java.time.LocalDate;

public class QuestionControl {
    private final QuestionInputBoundary questionInput;

    public QuestionControl(QuestionInputBoundary questionInput){
        this.questionInput = questionInput;
    }

    public UserResponseModel createQuestion(String questionCategory, String title, LocalDate createAt, LocalDate legalDeadline){
        QuestionRequestModel requestModel = new QuestionRequestModel(questionCategory, title, createAt, legalDeadline);
        return questionInput.createQuestion(requestModel);
    }
}
