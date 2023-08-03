package entitytesting;

import org.junit.jupiter.api.Test;
import questionentities.Post;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test
    void testConstructorAndGetter() {
        // expected values
        int expectedQuestionId = 1000000;
        int expectedPostId = 2000000;
        LocalDate expectedCreateAt = LocalDate.now();
        String expectedPostText = "This is a post.";
        int expectedBelongsTo = 3000000;

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
    void testSetters() {
        // expected values
        int expectedQuestionId = 1000000;
        int expectedPostId = 2000000;
        LocalDate expectedCreateAt = LocalDate.now();
        String expectedPostText = "This is a post.";
        int expectedBelongsTo = 3000000;

        // no-arg constructor
        Post post = new Post();

        // use setters to set values
        post.setQuestionId(expectedQuestionId);
        post.setPostId(expectedPostId);
        post.setCreateAt(expectedCreateAt);
        post.setPostText(expectedPostText);
        post.setBelongsTo(expectedBelongsTo);

        // assertions
        assertEquals(expectedQuestionId, post.getQuestionId(), "QuestionId is incorrect.");
        assertEquals(expectedPostId, post.getPostId(), "PostId is incorrect.");
        assertEquals(expectedCreateAt, post.getCreateAt(), "CreateAt is wrong.");
        assertEquals(expectedPostText, post.getPostText(), "PostText is wrong.");
        assertEquals(expectedBelongsTo, post.getBelongsTo(), "BelongsTo is wrong.");
    }

    @Test
    void testEquals() {
        Post post1 = new Post(1000000, 2000000, LocalDate.now(), "This is a post.", 3000000);
        Post post2 = new Post(1000000, 2000000, LocalDate.now(), "This is another post.", 3000000);

        // Two posts are equal if they have the same postId
        assertEquals(post1, post2, "Two posts with the same postId should be equal.");

        post2.setPostId(3000000);
        assertNotEquals(post1, post2, "Two posts with different postId should not be equal.");
    }

    @Test
    void testToString() {
        Post post = new Post(1000000, 2000000, LocalDate.now(), "This is a post.", 3000000);
        String expectedString = "This is a post in 1000000 belongs to 3000000";
        assertEquals(expectedString, post.toString(), "toString is wrong.");
    }
}

