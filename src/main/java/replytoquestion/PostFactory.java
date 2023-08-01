package replytoquestion;

import questionentities.Post;

import java.time.LocalDate;

public class PostFactory {
    public Post create(int postId, int questionId, LocalDate createAt, String postText, int belongsTo){
        return new Post(postId, questionId, createAt, postText, belongsTo);
    }
}
