package adapter.controller;

import businessrule.inputboundary.SelectInputBoundary;
import businessrule.requestmodel.SelectRequestModel;
import businessrule.responsemodel.UserResponseModel;

public class SelectQuestionControl {
    private final SelectInputBoundary selectInputBoundary;

    public SelectQuestionControl(SelectInputBoundary selectInputBoundary) {
        this.selectInputBoundary = selectInputBoundary;
    }
    public UserResponseModel selectQuestion(int questionId) {
        SelectRequestModel selectRequestModel =  new SelectRequestModel(questionId);
        return selectInputBoundary.selectQuestion(selectRequestModel);
    }
}
