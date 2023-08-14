package businessrule.inputboundary;

import businessrule.requestmodel.PostRequestModel;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.responsemodel.UserResponseModel;

public interface PostInputBoundary {
    TheQuestionResponseModel createPost(PostRequestModel postRequestModel);
}
