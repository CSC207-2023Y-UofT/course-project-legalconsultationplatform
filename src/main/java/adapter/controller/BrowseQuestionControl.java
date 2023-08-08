
package adapter.controller;

import businessrule.requestmodel.BrowseRequestModel;
import businessrule.responsemodel.ViewResponseModel;
import businessrule.inputboundary.BrowseInputBoundary;

/**
 * This class represents a controller responsible for browsing questions.
 * It interacts with the business logic through the BrowseInputBoundary interface to handle browsing of
 * questions based on the provided attorney ID.
 */

public class BrowseQuestionControl {
    private final BrowseInputBoundary browseInputBoundary;

    /**
     * Constructs a BrowseQuestionControl with the specified BrowseInputBoundary.
     *
     * @param browseInputBoundary The boundary interface for handling question browsing.
     */
    public BrowseQuestionControl(BrowseInputBoundary browseInputBoundary) {
        this.browseInputBoundary = browseInputBoundary;
    }

    /**
     * Browses questions based on the provided attorney ID.
     *
     * @param attorneyId The ID of the attorney for whom to retrieve question-related information.
     * @return A ViewResponseModel containing the response information after browsing.
     */
    public ViewResponseModel browseQuestion(int attorneyId) {
        BrowseRequestModel browseRequestModel = new BrowseRequestModel(attorneyId);
        return browseInputBoundary.browseQuestion(browseRequestModel);
    }
}