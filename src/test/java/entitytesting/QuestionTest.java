package entitytesting;

import org.junit.jupiter.api.Test;
import questionentities.Post;
import questionentities.Question;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {
    @Test
    void testConstructor() {
            // expected values
        int expectedQuestionId = 1000000;
        LocalDate expectedCreateAt = LocalDate.now();
        String expectedType = "fraud";
        int expectedAskedByClient = 2000000;
        LocalDate expectedLegalDeadline = LocalDate.now();

        // constructor
        Question question = new Question(expectedQuestionId, expectedType, expectedCreateAt, expectedAskedByClient, expectedLegalDeadline);

        // assertions
        assertEquals(expectedQuestionId, question.getQuestionId(), "QuestionId is wrong.");
        assertEquals(expectedType, question.getType(), "Question type is wrong.");
        assertEquals(expectedCreateAt, question.getCreateAt(), "CreateAt is wrong.");
        assertEquals(expectedAskedByClient, question.getAskedByClient(), "AskedByClient is wrong.");
        assertEquals(expectedLegalDeadline, question.getLegalDeadline(), "LegalDeadline is wrong.");
    }

    @Test
    void testNoArgConstructorAndSetters() {
        // expected values
        int expectedQuestionId = 1000000;
        LocalDate expectedCreateAt = LocalDate.now();
        String expectedType = "fraud";
        int expectedAskedByClient = 2000000;
        LocalDate expectedLegalDeadline = LocalDate.now();
        boolean expectedIsTaken = true;
        int expectedTakenByAttorney = 3000000;
        boolean expectedIsClose = true;
        int expectedRating = 5;

        // expected values for post
        int expectedPostId = 1;
        LocalDate expectedPostCreateAt = LocalDate.now();
        String expectedPostText = "This is a test post.";
        int expectedBelongsTo = 4000000;

        // create a post object
        Post expectedPost = new Post(expectedQuestionId, expectedPostId, expectedPostCreateAt, expectedPostText,
                expectedBelongsTo);

        // no-arg constructor
        Question question = new Question();

        // use setters to set values
        question.setQuestionId(expectedQuestionId);
        question.setType(expectedType);
        question.setCreateAt(expectedCreateAt);
        question.setAskedByClient(expectedAskedByClient);
        question.setLegalDeadline(expectedLegalDeadline);
        question.setTaken(expectedIsTaken);
        question.setTakenByAttorney(expectedTakenByAttorney);
        question.setClose(expectedIsClose);
        question.setRating(expectedRating);
        question.addPosts(expectedPost);

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
        assertEquals(1, question.getPosts().size(), "Posts size is wrong.");
        assertEquals(expectedPost, question.getPosts().get(0), "Posts are wrong.");
    }
}


