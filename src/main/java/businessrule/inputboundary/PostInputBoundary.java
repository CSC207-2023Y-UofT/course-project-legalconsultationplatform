package businessrule.inputboundary;

import businessrule.requestmodel.PostRequestModel;
import businessrule.responsemodel.HomePageResponseModel;

public interface PostInputBoundary {
    HomePageResponseModel createPost(PostRequestModel postRequestModel);
}
