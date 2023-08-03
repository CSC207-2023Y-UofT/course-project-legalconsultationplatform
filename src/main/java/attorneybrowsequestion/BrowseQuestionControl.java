package attorneybrowsequestion;

import apapter.responsemodel.ViewResponseModel;

public class BrowseQuestionControl {
    final BrowseInputBoundary browseInputBoundary;

    public BrowseQuestionControl(BrowseInputBoundary browseInputBoundary) {
        this.browseInputBoundary = browseInputBoundary;
    }
    public ViewResponseModel browseQuestion(){
        return browseInputBoundary.browseQuestion();
    }
}
