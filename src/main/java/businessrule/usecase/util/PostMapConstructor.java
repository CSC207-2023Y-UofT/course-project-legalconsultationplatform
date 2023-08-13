package businessrule.usecase.util;

import businessrule.gateway.UserGateway;
import businessrule.gateway.UserGatewayFactory;
import entity.Post;
import entity.Question;
import entity.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PostMapConstructor {
    private final UserGatewayFactory userGatewayFactory;

    public PostMapConstructor(UserGatewayFactory userGatewayFactory) {this.userGatewayFactory = userGatewayFactory;}

    public Map<Integer, PostDisplayFormatter> constructPostMap(Question question) {
        List<Post> postList = question.getPosts();

        // initialize post map
        Map<Integer, PostDisplayFormatter> postMap = new LinkedHashMap<>();

        // handle the empty post list
        if (postList.isEmpty()) {return postMap;}

        // for each post list, find the data needed
        for (Post post: postList) {
            int postId = post.getPostId();
            UserGateway userGateway = userGatewayFactory.createUserGateway(post.getBelongsTo());
            User user = userGateway.get(post.getBelongsTo());
            String userType = user.getUserType();
            String name = user.getUserName();
            String postText = post.getPostText();
            LocalDate createAt = post.getCreateAt();
            PostDisplayFormatter postDisplayFormatter = new PostDisplayFormatter(postText, userType, name, createAt);
            // put every data needed to the post map
            postMap.put(postId, postDisplayFormatter);
        }
        return postMap;
    }
}
