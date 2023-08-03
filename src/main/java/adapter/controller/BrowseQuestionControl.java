package adapter.controller;

import businessrule.requestmodel.BrowseRequestModel;
import businessrule.responsemodel.ViewResponseModel;
import businessrule.inputboundary.BrowseInputBoundary;

public class BrowseQuestionControl {
    final BrowseInputBoundary browseInputBoundary;

    public BrowseQuestionControl(BrowseInputBoundary browseInputBoundary) {
        this.browseInputBoundary = browseInputBoundary;
    }
    public ViewResponseModel browseQuestion(int attorneyId){
        BrowseRequestModel browseRequestModel = new BrowseRequestModel(attorneyId);
        return browseInputBoundary.browseQuestion(browseRequestModel);
    }
}
