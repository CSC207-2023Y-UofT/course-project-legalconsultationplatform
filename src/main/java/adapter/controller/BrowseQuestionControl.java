package adapter.controller;

import businessrule.inputboundary.ViewInputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import businessrule.responsemodel.ViewResponseModel;

public class BrowseQuestionControl extends ViewQuestionControllerBase{

    public BrowseQuestionControl(ViewInputBoundary viewInputBoundary) {
        super(viewInputBoundary);
    }

    public ViewResponseModel viewQuestion(int attorneyId){
        ViewRequestModel viewRequestModel = new ViewRequestModel(attorneyId);
        return viewInputBoundary.viewQuestion(viewRequestModel);
    }
}
