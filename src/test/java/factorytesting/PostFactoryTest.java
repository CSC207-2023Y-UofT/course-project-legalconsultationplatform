package factorytesting;

import static org.junit.jupiter.api.Assertions.*;

import entity.Post;
import entity.factory.PostFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class PostFactoryTest {

    private PostFactory postFactory;
    private int postId;
    private int questionId;
    private LocalDate createAt;
    private String postText;
    private int belongsTo;

    @BeforeEach
    void setUpPostFactory() {
        postFactory = new PostFactory();
        postId = 1;
        questionId = 2;
        createAt = LocalDate.now();
        postText = "test post text";
        belongsTo = 3;
    }

    @Test
    void testCreate() {
        Post post = postFactory.create(postId, questionId, createAt, postText, belongsTo);

        assertEquals(postId, post.getPostId(), "PostId is incorrect.");
        assertEquals(questionId, post.getQuestionId(), "QuestionId is incorrect.");
        assertEquals(createAt, post.getCreateAt(), "CreateAt is incorrect.");
        assertEquals(postText, post.getPostText(), "PostText is incorrect.");
        assertEquals(belongsTo, post.getBelongsTo(), "BelongsTo is incorrect.");
    }
}
