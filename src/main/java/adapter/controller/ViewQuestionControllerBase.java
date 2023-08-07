package adapter.controller;

import businessrule.inputboundary.ViewInputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import businessrule.responsemodel.ViewResponseModel;

public abstract class ViewQuestionControllerBase {
    protected final ViewInputBoundary viewInputBoundary;

    public ViewQuestionControllerBase(ViewInputBoundary viewInputBoundary) {this.viewInputBoundary = viewInputBoundary;}

    public abstract ViewResponseModel viewQuestion(int UserId);
}
