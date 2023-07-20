package attorneybrowsequestion;

public class BrowseQuestionControl {
    final BrowseInputBoundary browseInputBoundary;

    public BrowseQuestionControl(BrowseInputBoundary browseInputBoundary) {
        this.browseInputBoundary = browseInputBoundary;
    }
    public BrowseRespondModel browseQuestion(int attorneyid ){
        BrowseRequestModel browseRequestModel = new BrowseRequestModel(attorneyid);
        return browseInputBoundary.browseQuestion(browseRequestModel);

    }
}
