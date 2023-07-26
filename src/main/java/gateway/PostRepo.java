package gateway;

import questionentities.Post;

public class PostRepo implements PostGateway{
    DatabaseConnection databaseConnection;

    @Override
    public void savePost(Post post) {

    }

    @Override
    public Post getPost(int postId) {
        return null;
    }
}
