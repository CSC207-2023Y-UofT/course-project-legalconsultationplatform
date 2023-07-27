package entitytesting;

import org.junit.jupiter.api.Test;
import questionentities.Post;
import questionentities.Question;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {

    @Test
    public void testFirstConstructor() {
        // expected values
        int expectedQuestionId = 1000000;
        LocalDate expectedCreateAt = LocalDate.now();
        String expectedType = "fraud";
        int expectedAskedByClient = 2000000;
        LocalDate expectedLegalDeadline = LocalDate.now();

        // first constructor
        Question question = new Question(expectedQuestionId, expectedType, expectedCreateAt, expectedAskedByClient,
                expectedLegalDeadline);

        // assertions
        assertEquals(expectedQuestionId, question.getQuestionId(), "QuestionId is wrong.");
        assertEquals(expectedType, question.getType(), "Question type is wrong.");
        assertEquals(expectedCreateAt, question.getCreateAt(), "CreateAt is wrong.");
        assertEquals(expectedAskedByClient, question.getAskedByClient(), "AskedByClient is wrong.");
        assertEquals(expectedLegalDeadline, question.getLegalDeadline(), "LegalDeadline is wrong.");

        // Check default values
        assertFalse(question.isTaken(), "isTaken should be false initially.");
        assertEquals(0, question.getTakenByAttorney(), "takenByAttorney should be 0 initially.");
        assertFalse(question.isClose(), "isClose should be false initially.");
        assertEquals(Question.MISSING_RATING, question.getRating(), "Rating should be MISSING_RATING initially.");
        assertTrue(question.getPosts().isEmpty(), "Posts should be empty initially.");
    }

    @Test
    void testSecondConstructor() {
        // expected values
        int expectedQuestionId = 1000000;
        LocalDate expectedCreateAt = LocalDate.now();
        String expectedType = "fraud";
        int expectedAskedByClient = 2000000;
        boolean expectedIsTaken = true;
        int expectedTakenByAttorney = 3000000;
        boolean expectedIsClose = true;
        int expectedRating = 5;
        LocalDate expectedLegalDeadline = LocalDate.now();

        // expected values for post
        int expectedPostId = 1;
        LocalDate expectedPostCreateAt = LocalDate.now();
        String expectedPostText = "This is a test post.";

        // create a post object
        Post expectedPost = new Post(expectedQuestionId, expectedPostId, expectedPostCreateAt, expectedPostText);

        // create a list and add the post object to it
        List<Post> expectedPosts = new ArrayList<>();
        expectedPosts.add(expectedPost);

        // second constructor
        Question question = new Question(expectedQuestionId, expectedType, expectedCreateAt, expectedAskedByClient,
                expectedIsTaken, expectedTakenByAttorney, expectedIsClose, expectedRating, expectedPosts, expectedLegalDeadline
        );

        // assertions
        assertEquals(expectedQuestionId, question.getQuestionId(), "QuestionId is wrong.");
        assertEquals(expectedType, question.getType(), "Question type is wrong.");
        assertEquals(expectedCreateAt, question.getCreateAt(), "CreateAt is wrong.");
        assertEquals(expectedAskedByClient, question.getAskedByClient(), "AskedByClient is wrong.");
        assertEquals(expectedLegalDeadline, question.getLegalDeadline(), "LegalDeadline is wrong.");
        assertEquals(expectedIsTaken, question.isTaken(), "isTaken is wrong.");
        assertEquals(expectedTakenByAttorney, question.getTakenByAttorney(), "takenByAttorney is wrong.");
        assertEquals(expectedIsClose, question.isClose(), "isClose is wrong.");
        assertEquals(expectedRating, question.getRating(), "Rating is wrong.");
        assertEquals(expectedPosts, question.getPosts(), "Posts are wrong.");
    }
}
