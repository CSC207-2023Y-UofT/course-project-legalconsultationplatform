package apapter.controller;

import businessrule.inputboundary.CloseInputBoundary;
import businessrule.requestmodel.CloseRequestModel;

public class CloseQuestionControl {
    final CloseInputBoundary closeInputBoundary;

    public CloseQuestionControl(CloseInputBoundary closeInputBoundary) {
        this.closeInputBoundary = closeInputBoundary;
    }
    public MessageResponseModel closeQuestion(int questionId, int userId){
        CloseRequestModel closeRequestModel = new CloseRequestModel(questionId, userId);
        return closeInputBoundary.closeQuestion(closeRequestModel);
    }
}

