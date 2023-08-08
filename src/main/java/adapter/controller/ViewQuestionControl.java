package adapter.controller;

import businessrule.responsemodel.ViewResponseModel;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.requestmodel.ViewRequestModel;

public class ViewQuestionControl extends ViewQuestionControllerBase{

    public ViewQuestionControl(ViewInputBoundary viewInputBoundary) {
        super(viewInputBoundary);
    }

    @Override
    public ViewResponseModel viewQuestion(int userId){
        ViewRequestModel viewRequestModel = new ViewRequestModel(userId);
        return viewInputBoundary.viewQuestion(viewRequestModel);
    }
}
