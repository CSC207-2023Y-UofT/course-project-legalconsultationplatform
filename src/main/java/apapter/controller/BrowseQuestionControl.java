package apapter.controller;

import businessrule.responsemodel.ViewResponseModel;
import businessrule.inputboundary.BrowseInputBoundary;

public class BrowseQuestionControl {
    final BrowseInputBoundary browseInputBoundary;

    public BrowseQuestionControl(BrowseInputBoundary browseInputBoundary) {
        this.browseInputBoundary = browseInputBoundary;
    }
    public ViewResponseModel browseQuestion(){
        return browseInputBoundary.browseQuestion();
    }
}
