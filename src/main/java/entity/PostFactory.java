package entity;

import entity.Post;

import java.time.LocalDate;

public class PostFactory {
    public Post create(int postId, int questionId, LocalDate createAt, String postText, int belongsTo){
        return new Post(questionId, postId, createAt, postText, belongsTo);
    }
}
