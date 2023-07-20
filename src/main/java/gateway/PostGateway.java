package gateway;

import questionentities.Post;

public interface PostGateway {
    void savePost(Post post);
    Post getPost(int postId);
}
