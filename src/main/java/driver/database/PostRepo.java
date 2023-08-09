package driver.database;

import businessrule.gateway.PostGateway;
import entity.Post;
import entity.User;

/**
 * This class representsmanaging Post entities in the database.
 */
public class PostRepo extends GenericRepository<Post> implements PostGateway {

    /**
     * Constructs a PostRepo instance.
     * Initializes the superclass with the Post class.
     */
    public PostRepo() {
        super(Post.class);
    }

    /**
     * Retrieves a Post entity with the specified ID from the database.
     *
     * @param id The ID of the Post entity to retrieve.
     * @return The Post entity associated with the specified ID, or null if not found.
     */
    @Override
    public Post get(int id) {
        return (Post) super.get(id);
    }
}