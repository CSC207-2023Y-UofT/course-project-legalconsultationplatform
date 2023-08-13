package adapter.controller;

import businessrule.responsemodel.UserResponseModel;
import businessrule.inputboundary.ViewInputBoundary;

public class ViewQuestionControl{

    private final ViewInputBoundary viewInputBoundary;

    public ViewQuestionControl(ViewInputBoundary viewInputBoundary) {
        this.viewInputBoundary = viewInputBoundary;
    }

    public UserResponseModel viewQuestion(){
        return viewInputBoundary.viewQuestion();
    }
}
