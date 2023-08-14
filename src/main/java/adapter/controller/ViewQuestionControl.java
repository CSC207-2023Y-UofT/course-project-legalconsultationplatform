package adapter.controller;

import businessrule.responsemodel.UserResponseModel;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.responsemodel.ViewResponseModel;

import javax.swing.text.View;

public class ViewQuestionControl{

    private final ViewInputBoundary viewInputBoundary;

    public ViewQuestionControl(ViewInputBoundary viewInputBoundary) {
        this.viewInputBoundary = viewInputBoundary;
    }

    public ViewResponseModel viewQuestion(){
        return viewInputBoundary.viewQuestion();
    }
}
