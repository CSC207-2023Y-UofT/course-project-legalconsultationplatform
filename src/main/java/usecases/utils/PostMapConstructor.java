package usecases.utils;

import usecases.dto.PostDisplay;
import usecases.gateway.UserGateway;
import infrastructure.database.UserGatewayFactory;
import entities.Post;
import entities.Question;
import entities.user.User;

import java.time.LocalDate;
import java.util.LinkedHashMap;
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
    public PostMapConstructor(UserGatewayFactory userGatewayFactory) {this.userGatewayFactory = userGatewayFactory;}

    /**
     * Constructs a map of post IDs to formatted post display information based on the given question.
     *
     * @param question The question containing posts to be mapped.
     * @return A map of post IDs to formatted post display information.
     */
    public Map<Integer, PostDisplay> constructPostMap(Question question) {
        List<Post> postList = question.getPosts();

        // initialize post map
        Map<Integer, PostDisplay> postMap = new LinkedHashMap<>();

        // handle the empty post list
        if (postList.isEmpty()) {return postMap;}

        // for each post list, find the data needed
        for (Post post: postList) {
            int postId = post.getPostId();
            UserGateway<? extends User> userGateway = userGatewayFactory.createUserGateway(post.getBelongsTo());
            User user = userGateway.get(post.getBelongsTo());
            String userType = user.getUserType();
            String name = user.getUserName();
            String postText = post.getPostText();
            LocalDate createAt = post.getCreateAt();
            PostDisplay postDisplayFormatter = new PostDisplay(postText, userType, name, createAt);
            // put every data needed to the post map
            postMap.put(postId, postDisplayFormatter);
        }
        return postMap;
    }
}
