package entitytesting;

import entity.Question;
import org.junit.jupiter.api.Test;
import entity.Post;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class contains unit tests for the Post class.
 */

class PostTest {

    /**
     * Test the constructor and getter methods of the Post class.
     */
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

    /**
     * Test the setter methods of the Post class.
     */
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

    /**
     * Test the equals method of the Post class when objects are the same.
     */
    @Test
    void testEqualsSucceed() {
        Post post1 = new Post(1000000, 2000000, LocalDate.now(), "This is a post.", 3000000);
        assertEquals(true, post1.equals(post1), "The equal method is wrong");
    }

    /**
     * Test the equals method of the Post class with objects of different classes.
     */
    @Test
    void testEqualsFailByDifferentClass() {
        Post post1 = new Post(1000000, 2000000, LocalDate.now(), "This is a post.", 3000000);
        Question question = new Question();
        assertEquals(false, post1.equals(question), "The equal method is wrong");
    }

    /**
     * Test the equals method of the Post class with objects having different post IDs.
     */
    @Test
    void testEqualsFailByDifferentPostId() {
        Post post1 = new Post(1000000, 2000000, LocalDate.now(), "This is a post.", 3000000);
        Post post2 = new Post(1000000, 3000000, LocalDate.now(), "This is another post.", 3000000);
        assertEquals(false, post1.equals(post2), "The equal method is wrong");
    }

    /**
     * Test the toString method of the Post class when it succeeds.
     */
    @Test
    void testToStringSucceed() {
        int expectedQuestionId = 1000000;
        int expectedBelongsTo = 3000000;
        Post post = new Post(expectedQuestionId, 2000000, LocalDate.now(), "This is a post.", expectedBelongsTo);
        String expectedToString = String.format("This is a post in %d belongs to %d", expectedQuestionId, expectedBelongsTo);
        assertEquals(expectedToString, post.toString(), "The toString method is wrong");
    }

    /**
     * Test the toString method of the Post class when it fails.
     */
    @Test
    void testToStringFail() {
        int expectedQuestionId = 1000000;
        int expectedBelongsTo = 3000000;
        Post post = new Post(expectedQuestionId, 2000000, LocalDate.now(), "This is a post.", expectedBelongsTo);
        String unexpectedToString = String.format("This is a post in %d belongs to %d", expectedQuestionId, expectedBelongsTo+1);
        assertNotEquals(unexpectedToString, post.toString(), "The toString method is wrong");
    }

}

