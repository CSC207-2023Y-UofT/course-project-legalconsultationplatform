package entitytesting;

import org.junit.jupiter.api.Test;
import entity.Question;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    void testConstructorAndGetter() {
        // expected values
        int expectedQuestionId = 1000000;
        String expectedType = "fraud";
        String expectedTitle = "Question Title";
        LocalDate expectedCreateAt = LocalDate.now();
        int expectedAskedByClient = 2000000;
        LocalDate expectedLegalDeadline = LocalDate.now();

        // constructor
        Question question = new Question(expectedQuestionId, expectedType, expectedTitle, expectedCreateAt, expectedAskedByClient, expectedLegalDeadline);

        // assertions
        assertEquals(expectedQuestionId, question.getQuestionId(), "QuestionId is incorrect.");
        assertEquals(expectedType, question.getType(), "Question type is incorrect.");
        assertEquals(expectedTitle, question.getTitle(), "Title is wrong.");
        assertEquals(expectedCreateAt, question.getCreateAt(), "CreateAt is wrong.");
        assertEquals(expectedAskedByClient, question.getAskedByClient(), "AskedByClient is wrong.");
        assertEquals(expectedLegalDeadline, question.getLegalDeadline(), "LegalDeadline is wrong.");
        assertFalse(question.isTaken(), "isTaken should be false initially.");
        assertEquals(0, question.getTakenByAttorney(), "takenByAttorney should be 0 initially.");
        assertNull(question.getTakenAt(), "takenAt should be null initially.");
        assertFalse(question.isClose(), "isClose should be false initially.");
        assertEquals(Question.MISSING_RATING, question.getRating(), "Rating should be MISSING_RATING initially.");
        assertTrue(question.getPosts().isEmpty(), "Posts should be empty initially.");
    }

    @Test
    void testSetters() {
        // expected values
        int expectedQuestionId = 1000000;
        String expectedType = "fraud";
        String expectedTitle = "Question Title";
        LocalDate expectedCreateAt = LocalDate.now();
        int expectedAskedByClient = 2000000;
        LocalDate expectedLegalDeadline = LocalDate.now();

        // no-arg constructor
        Question question = new Question();

        // use setters to set values
        question.setQuestionId(expectedQuestionId);
        question.setType(expectedType);
        question.setTitle(expectedTitle);
        question.setCreateAt(expectedCreateAt);
        question.setAskedByClient(expectedAskedByClient);
        question.setLegalDeadline(expectedLegalDeadline);

        // assertions
        assertEquals(expectedQuestionId, question.getQuestionId(), "QuestionId is wrong.");
        assertEquals(expectedType, question.getType(), "Question type is wrong.");
        assertEquals(expectedTitle, question.getTitle(), "Title is wrong.");
        assertEquals(expectedCreateAt, question.getCreateAt(), "CreateAt is wrong.");
        assertEquals(expectedAskedByClient, question.getAskedByClient(), "AskedByClient is wrong.");
        assertEquals(expectedLegalDeadline, question.getLegalDeadline(), "LegalDeadline is wrong.");
    }
}
