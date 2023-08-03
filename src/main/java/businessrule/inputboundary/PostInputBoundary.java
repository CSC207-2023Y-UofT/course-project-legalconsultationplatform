package businessrule.inputboundary;

import businessrule.requestmodel.PostRequestModel;

public interface PostInputBoundary {
    MessageResponseModel createPost(PostRequestModel postRequestModel);
}
