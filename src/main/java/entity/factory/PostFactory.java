package entity.factory;

import entity.Post;

import java.time.LocalDate;

/**
 * This is a factory class for creating Post objects.
 *
 * The "PostFactory" class provides a convenient way to create instances of the "Post" class by encapsulating
 * the construction logic. It serves as a central point for creating posts associated with questions in the application.
 */
public class PostFactory {
    public Post create(int postId, int questionId, LocalDate createAt, String postText, int belongsTo){
        return new Post(questionId, postId, createAt, postText, belongsTo);
    }
}
