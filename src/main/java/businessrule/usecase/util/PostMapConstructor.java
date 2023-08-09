package businessrule.usecase.util;

import businessrule.gateway.UserGateway;
import businessrule.gateway.UserGatewayFactory;
import entity.Post;
import entity.Question;
import entity.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a map of post IDs to formatted post display information.
 * It uses a `UserGatewayFactory` to create user gateways for retrieving user information.
 */
public class PostMapConstructor {
    private final UserGatewayFactory userGatewayFactory;

    /**
     * Constructs a `PostMapConstructor` object with the given `UserGatewayFactory`.
     *
     * @param userGatewayFactory The factory for creating user gateways.
     */
    public PostMapConstructor(UserGatewayFactory userGatewayFactory) {
        this.userGatewayFactory = userGatewayFactory;
    }

    /**
     * Constructs a map of post IDs to formatted post display information based on the given question.
     *
     * @param question The question containing posts to be mapped.
     * @return A map of post IDs to formatted post display information.
     */
    public Map<Integer, PostDisplayFormatter> constructPostMap(Question question) {
        List<Post> postList = question.getPosts();

        // initialize post map
        Map<Integer, PostDisplayFormatter> postMap = new HashMap<>();

        // handle the empty post list
        if (postList.isEmpty()) {
            return postMap;
        }

        // for each post in the list, find the data needed
        for (Post post : postList) {
            int postId = post.getPostId();
            UserGateway userGateway = userGatewayFactory.createUserGateway(post.getBelongsTo());
            User user = userGateway.get(post.getBelongsTo());
            boolean isClient = user.isClient();
            String name = user.getUserName();
            String postText = post.getPostText();
            LocalDate createAt = post.getCreateAt();
            PostDisplayFormatter postDisplayFormatter = new PostDisplayFormatter(postText, isClient, name, createAt);
            // put every data needed into the post map
            postMap.put(postId, postDisplayFormatter);
        }
        return postMap;
    }
}