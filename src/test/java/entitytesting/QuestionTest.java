package entitytesting;

import org.junit.jupiter.api.Test;
import entity.Question;
import java.time.LocalDate;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the Question class.
 */
class QuestionTest {

    /**
     * Test the constructor and getter methods of the Question class.
     */
    @Test
    void testConstructorAndGetter() {
        int expectedQuestionId = 1000000;
        String expectedType = "fraud";
        String expectedTitle = "Question Title";
        LocalDate expectedCreateAt = LocalDate.now();
        int expectedAskedByClient = 2000000;
        LocalDate expectedLegalDeadline = LocalDate.now();

        Question question = new Question(expectedQuestionId, expectedType, expectedTitle, expectedCreateAt, expectedAskedByClient, expectedLegalDeadline);

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

    /**
     * Test the setter methods of the Question class.
     */
    @Test
    void testSetters() {
        int expectedQuestionId = 1000000;
        String expectedType = "fraud";
        String expectedTitle = "Question Title";
        LocalDate expectedCreateAt = LocalDate.now();
        int expectedAskedByClient = 2000000;
        LocalDate expectedLegalDeadline = LocalDate.now();


        Question question = new Question();

        question.setQuestionId(expectedQuestionId);
        question.setType(expectedType);
        question.setTitle(expectedTitle);
        question.setCreateAt(expectedCreateAt);
        question.setAskedByClient(expectedAskedByClient);
        question.setLegalDeadline(expectedLegalDeadline);

        assertEquals(expectedQuestionId, question.getQuestionId(), "QuestionId is wrong.");
        assertEquals(expectedType, question.getType(), "Question type is wrong.");
        assertEquals(expectedTitle, question.getTitle(), "Title is wrong.");
        assertEquals(expectedCreateAt, question.getCreateAt(), "CreateAt is wrong.");
        assertEquals(expectedAskedByClient, question.getAskedByClient(), "AskedByClient is wrong.");
        assertEquals(expectedLegalDeadline, question.getLegalDeadline(), "LegalDeadline is wrong.");
    }

    /**
     * Test the equals method of the Question class with objects that are not equal.
     */
    @Test
    void testEqualsFailByNotEqual() {
        Question question1 = new Question(1, "fraud", "Question Title", LocalDate.now(), 2000000, LocalDate.now());
        Question question2 = new Question(2, "fraud", "Question Title", LocalDate.now(), 2000000, LocalDate.now());
        assertNotEquals(question1, question2, "The equal method is wrong");
    }

    /**
     * Test the hashCode method of the Question class when it fails.
     */
    @Test
    void testHashCodeFail() {
        Question question1 = new Question(1, "fraud", "Question Title", LocalDate.now(), 2000000, LocalDate.now());
        assertNotEquals(question1.hashCode(), Objects.hashCode(2), "The hashCode is wrong");
    }

    /**
     * Test the hashCode method of the Question class when it succeeds.
     */
    @Test
    void testHashCodeSucceed() {
        Question question1 = new Question(1, "fraud", "Question Title", LocalDate.now(), 2000000, LocalDate.now());
        assertEquals(question1.hashCode(), Objects.hashCode(1), "The hashCode is wrong");
    }

    /**
     * Test the toString method of the Question class when it succeeds.
     */
    @Test
    void testToStringSucceed() {
        String expectedType = "fraud";
        int expectedAskedByClient = 2000000;
        Question question1 = new Question(1, expectedType, "Question Title", LocalDate.now(), expectedAskedByClient, LocalDate.now());
        String expectedToString = String.format("This is a %s type question asked by %d", expectedType, expectedAskedByClient);
        assertEquals(expectedToString, question1.toString(), "The toString method is wrong");
    }

    /**
     * Test the toString method of the Question class when it fails.
     */
    @Test
    void testToStringFail() {
        String unexpectedType = "criminal";
        int unexpectedAskedByClient = 3000000;
        Question question1 = new Question(1, "fraud", "Question Title", LocalDate.now(), 2000000, LocalDate.now());
        String unexpectedToString = String.format("This is a %s type question asked by %d", unexpectedType, unexpectedAskedByClient);
        assertNotEquals(unexpectedToString, question1.toString(), "The toString method is wrong");
    }
}


