package adapter.controller;

import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.inputboundary.SelectInputBoundary;
import businessrule.requestmodel.SelectRequestModel;

public class SelectQuestionControl {
    final SelectInputBoundary selectInputBoundary;

    public SelectQuestionControl(SelectInputBoundary selectInputBoundary) {
        this.selectInputBoundary = selectInputBoundary;
    }
    public TheQuestionResponseModel selectQuestion(int questionId, int userId) {
        SelectRequestModel selectRequestModel =  new SelectRequestModel(questionId, userId);
        return selectInputBoundary.selectQuestion(selectRequestModel);
    }
}
