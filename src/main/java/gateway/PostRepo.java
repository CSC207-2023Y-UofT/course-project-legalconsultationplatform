package gateway;

import questionentities.Post;

public class PostRepo implements PostGateway{
    DatabaseConnection databaseConnection;

    public PostRepo(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }
    @Override
    public void savePost(Post post) {

    }

    @Override
    public Post getPost(int postId) {
        return null;
    }

    @Override
    public boolean checkExistsById(int postId) {
        return false;
    }
}
