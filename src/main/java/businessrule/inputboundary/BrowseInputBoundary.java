package businessrule.inputboundary;

import businessrule.requestmodel.BrowseRequestModel;
import businessrule.responsemodel.ViewResponseModel;

public interface BrowseInputBoundary {

     ViewResponseModel browseQuestion(BrowseRequestModel browseRequestModel);
}
