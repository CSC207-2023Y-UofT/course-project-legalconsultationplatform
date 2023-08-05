package businessrule.gateway;

import entity.Post;

public interface PostGateway {
    void savePost(Post post);
    Post getPost(int postId);
    boolean existsById(int postId);
    void deletePost(int postId);
    void deleteAllPost();
}
