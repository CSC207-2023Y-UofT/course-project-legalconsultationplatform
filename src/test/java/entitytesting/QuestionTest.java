package entitytesting;

import org.junit.jupiter.api.Test;
import questionentities.Post;
import questionentities.Question;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {

    void testConstructor() {
        // expected values
        int expectedQuestionId = 1000000;
        LocalDate expectedCreateAt = LocalDate.now();
        String expectedType = "fraud";
        int expectedAskedByClient = 2000000;
        LocalDate expectedLegalDeadline = LocalDate.now();

        // constructor
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
}