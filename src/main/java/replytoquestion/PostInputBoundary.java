package replytoquestion;

import presenter.MessageResponseModel;

public interface PostInputBoundary {
    MessageResponseModel createPost(PostRequestModel postRequestModel);
}
