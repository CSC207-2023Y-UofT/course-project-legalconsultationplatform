package entitytesting;

import org.junit.jupiter.api.Test;
import questionentities.Post;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class PostTest {

    @Test
    void testConstructor() {
        // expected values
        int expectedQuestionId = 1000000;
        int expectedPostId = 1;
        LocalDate expectedCreateAt = LocalDate.now();
        String expectedPostText = "This is a test post.";
        int expectedBelongsTo = 2000000;

        // constructor
        Post post = new Post(expectedQuestionId, expectedPostId, expectedCreateAt, expectedPostText, expectedBelongsTo);

        // assertions
        assertEquals(expectedQuestionId, post.getQuestionId(), "QuestionId is wrong.");
        assertEquals(expectedPostId, post.getPostId(), "PostId is wrong.");
        assertEquals(expectedCreateAt, post.getCreateAt(), "CreateAt is wrong.");
        assertEquals(expectedPostText, post.getPostText(), "PostText is wrong.");
        assertEquals(expectedBelongsTo, post.getBelongsTo(), "BelongsTo is wrong.");
    }

    @Test
    void testNoArgConstructorAndSetters() {
        // expected values
        int expectedQuestionId = 1000000;
        int expectedPostId = 1;
        LocalDate expectedCreateAt = LocalDate.now();
        String expectedPostText = "This is a test post.";
        int expectedBelongsTo = 2000000;

        // no-arg constructor
        Post post = new Post();

        // use setters to set values
        post.setQuestionId(expectedQuestionId);
        post.setPostId(expectedPostId);
        post.setCreateAt(expectedCreateAt);
        post.setPostText(expectedPostText);
        post.setBelongsTo(expectedBelongsTo);

        // assertions
        assertEquals(expectedQuestionId, post.getQuestionId(), "QuestionId is wrong.");
        assertEquals(expectedPostId, post.getPostId(), "PostId is wrong.");
        assertEquals(expectedCreateAt, post.getCreateAt(), "CreateAt is wrong.");
        assertEquals(expectedPostText, post.getPostText(), "PostText is wrong.");
        assertEquals(expectedBelongsTo, post.getBelongsTo(), "BelongsTo is wrong.");
    }
}
