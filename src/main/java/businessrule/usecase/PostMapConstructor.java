package businessrule.usecase;

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
 * This class represents for constructing a map of post information for a given question.
 */
class PostMapConstructor {
    private final UserGatewayFactory userGatewayFactory;

    /**
     * Constructs a PostMapConstructor instance with the provided UserGatewayFactory.
     *
     * @param userGatewayFactory The factory for creating UserGateways.
     */
    public PostMapConstructor(UserGatewayFactory userGatewayFactory) {
        this.userGatewayFactory = userGatewayFactory;
    }

    /**
     * Constructs a map of post information for a given question.
     *
     * @param question The question for which to construct the post map.
     * @return A map containing post information.
     */
    protected Map<Integer, PostDisplayFormatter> constructPostMap(Question question) {
        List<Post> postList = question.getPosts();

        // Initialize post map
        Map<Integer, PostDisplayFormatter> postMap = new HashMap<>();

        // Handle the empty post list
        if (postList.isEmpty()) {
            return postMap;
        }

        // For each post in the list, find the data needed
        for (Post post : postList) {
            int postId = post.getPostId();
            UserGateway userGateway = userGatewayFactory.createUserGateway(post.getBelongsTo());
            User user = userGateway.getUser(post.getBelongsTo());
            boolean isClient = user.isClient();
            String name = user.getUserName();
            String postText = post.getPostText();
            LocalDate createdAt = post.getCreatedAt();
            PostDisplayFormatter postDisplayFormatter = new PostDisplayFormatter(postText, isClient, name, createdAt);

            // Put the data into the post map
            postMap.put(postId, postDisplayFormatter);
        }
        return postMap;
    }
}
