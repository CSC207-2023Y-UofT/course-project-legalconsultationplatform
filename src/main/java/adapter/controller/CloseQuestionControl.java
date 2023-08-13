package adapter.controller;

import businessrule.inputboundary.CloseInputBoundary;
import businessrule.requestmodel.CloseRequestModel;
import businessrule.responsemodel.UserResponseModel;

public class CloseQuestionControl {
    private final CloseInputBoundary closeInputBoundary;

    public CloseQuestionControl(CloseInputBoundary closeInputBoundary) {
        this.closeInputBoundary = closeInputBoundary;
    }
    public UserResponseModel closeQuestion(int questionId, int userId){
        CloseRequestModel closeRequestModel = new CloseRequestModel(questionId, userId);
        return closeInputBoundary.closeQuestion(closeRequestModel);
    }
}

