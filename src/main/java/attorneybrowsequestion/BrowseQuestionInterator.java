package attorneybrowsequestion;

import  screenpresenter.ScreenOutputBoundary;
import usergateway.UserGateway;
public class BrowseQuestionInterator {
    final ScreenOutputBoundary screenOutputBoundary;

    final UserGateway userGateway;
    public BrowseQuestionInterator(ScreenOutputBoundary screenOutputBoundary, UserGateway userGateway) {
        this.screenOutputBoundary = screenOutputBoundary;
        this.userGateway = userGateway;
    }

    public BrowseRespondModel browseQuestion(BrowseRequestModel browseRequestModel){
        return null;
    }
}
