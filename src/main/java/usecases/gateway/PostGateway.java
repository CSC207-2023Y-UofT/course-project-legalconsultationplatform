package usecases.gateway;

import entities.Post;

/**
 * This interface provides a method for retrieving a post by its ID.
 */
public interface PostGateway extends GenericGateway<Post> {

    /**
     * Get a post by its ID.
     *
     * @param id The ID of the post to retrieve.
     * @return The post object associated with the provided ID.
     */
    @Override
    Post get(int id);

}