package businessrule.usecase;

import driver.database.UserGateway;
import entity.Post;
import entity.Question;
import entity.User;
import org.apache.commons.collections.map.HashedMap;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The "PostMapConstructor" class is responsible for constructing a map of post information for a given question.
 */
class PostMapConstructor {
    private final UserGateway userGateway;

    public PostMapConstructor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    protected Map<Integer, PostDisplayFormatter> constructPostMap(Question question) {
        List<Post> postList = question.getPosts();
        Map<Integer, PostDisplayFormatter> postMap = new HashMap<>();

        if (postList.isEmpty()) {return postMap;}

        for (Post post: postList) {

            int postId = post.getPostId();
            User user = userGateway.getUser(post.getBelongsTo());
            boolean isClient = user.isClient();
            String name = user.getUserName();
            String postText = post.getPostText();
            LocalDate createAt = post.getCreateAt();
            PostDisplayFormatter postDisplayFormatter = new PostDisplayFormatter(postText, isClient, name, createAt);

            postMap.put(postId, postDisplayFormatter);
        }
        return postMap;
    }
}
