package businessrule.inputboundary;

import businessrule.requestmodel.PostRequestModel;
import businessrule.responsemodel.UserResponseModel;

public interface PostInputBoundary {
    UserResponseModel createPost(PostRequestModel postRequestModel);
}
