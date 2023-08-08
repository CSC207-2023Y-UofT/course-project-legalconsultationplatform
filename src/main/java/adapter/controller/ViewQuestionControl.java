package adapter.controller;

import businessrule.responsemodel.ViewResponseModel;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.requestmodel.ViewRequestModel;

public class ViewQuestionControl{

    private final ViewInputBoundary viewInputBoundary;

    public ViewQuestionControl(ViewInputBoundary viewInputBoundary) {
        this.viewInputBoundary = viewInputBoundary;
    }

    public ViewResponseModel viewQuestion(int userId){
        ViewRequestModel viewRequestModel = new ViewRequestModel(userId);
        return viewInputBoundary.viewQuestion(viewRequestModel);
    }
}
