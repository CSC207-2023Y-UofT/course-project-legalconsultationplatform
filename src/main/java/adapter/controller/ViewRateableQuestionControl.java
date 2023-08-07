package adapter.controller;

import businessrule.inputboundary.ViewInputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import businessrule.responsemodel.ViewResponseModel;

public class ViewRateableQuestionControl extends ViewQuestionControllerBase {
    public ViewRateableQuestionControl(ViewInputBoundary viewInputBoundary) {super(viewInputBoundary);}

    @Override
    public ViewResponseModel viewQuestion(int clientId) {
        ViewRequestModel viewRequestModel = new ViewRequestModel(clientId);
        return viewInputBoundary.viewQuestion(viewRequestModel);
    }
}
