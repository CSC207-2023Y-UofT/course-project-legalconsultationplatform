package businessrule.usecase;

import entity.Post;
import entity.Question;

import java.util.List;
import java.util.Map;

class PostMapConstructor {
    private Map<Integer, List<Object>> constructPostMap(Question question) {
        List<Post> postList = question.getPosts();
        for (Post post: postList) {
            int postId = post.getPostId();

        }
    }
}
