package businessrule.inputboundary;

import businessrule.requestmodel.PostRequestModel;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.responsemodel.UserResponseModel;

/**
 * This interface provides a method for creating a post based on the provided post request model.
 */
public interface PostInputBoundary {
    /**
     * Create a post based on the provided post request model.
     *
     * @param postRequestModel The request model containing the necessary information for post creation.
     * @return A response model indicating the result of post creation, typically for updating the question UI.
     */
    TheQuestionResponseModel createPost(PostRequestModel postRequestModel);
}
