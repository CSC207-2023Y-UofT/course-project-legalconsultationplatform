package apapter.controller;

import businessrule.responsemodel.ViewResponseModel;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.requestmodel.ViewRequestModel;

public class ViewQuestionControl {
    final ViewInputBoundary viewInputBoundary;

    public ViewQuestionControl(ViewInputBoundary viewInputBoundary) {
        this.viewInputBoundary = viewInputBoundary;
    }
    public ViewResponseModel viewQuestion(int questioId, int userId){
        ViewRequestModel viewRequestModel = new ViewRequestModel(questioId,userId);
        return viewInputBoundary.viewQuestion(viewRequestModel);

    }
}
