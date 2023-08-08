package driver.database;

import businessrule.gateway.PostGateway;
import entity.Post;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PostRepo extends GenericRepository<Post> implements PostGateway {

    public PostRepo() {
        super(Post.class);
    }

    @Override
    public Post get(int id) {
        return (Post) super.get(id);
    }

}
