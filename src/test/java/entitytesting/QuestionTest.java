package entitytesting;

import entity.Attorney;
import org.junit.jupiter.api.Test;
import entity.Question;


import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

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
    @Test
    void testEqualsSucceed() {
        Question question1 = new Question(1, "fraud", "Question Title", LocalDate.now(), 2000000, LocalDate.now());
        assertEquals(true, question1.equals(question1), "The equal method is wrong");
    }

    @Test
    void testEqualsFailByNotQuestion() {
        Question question1 = new Question(1, "fraud", "Question Title", LocalDate.now(), 2000000, LocalDate.now());
        Attorney attorney = new Attorney();

        assertEquals(false, question1.equals(attorney), "The equal method is wrong");
    }

    @Test
    void testEqualsFailByNotEqual() {
        Question question1 = new Question(1, "fraud", "Question Title", LocalDate.now(), 2000000, LocalDate.now());
        Question question2 = new Question(2, "fraud", "Question Title", LocalDate.now(), 2000000, LocalDate.now());
        assertEquals(false, question1.equals(question2), "The equal method is wrong");
    }

    @Test
    void testHashCodeFail() {
        Question question1 = new Question(1, "fraud", "Question Title", LocalDate.now(), 2000000, LocalDate.now());
        assertEquals(false, question1.hashCode() == Objects.hashCode(2), "The hashCode is wrong");
    }

    @Test
    void testHashCodeSucceed() {
        Question question1 = new Question(1, "fraud", "Question Title", LocalDate.now(), 2000000, LocalDate.now());
        assertEquals(question1.hashCode(), Objects.hashCode(1), "The hashCode is wrong");
    }

    @Test
    void testToStringSucceed() {
        String expectedType = "fraud";
        int expectedAskedByClient = 2000000;
        Question question1 = new Question(1, expectedType, "Question Title", LocalDate.now(), expectedAskedByClient, LocalDate.now());
        String expectedToString = String.format("This is a %s type question asked by %d", expectedType, expectedAskedByClient);
        assertEquals(expectedToString, question1.toString(), "The toString method is wrong");
    }

    @Test
    void testToStringFail() {
        String unexpectedType = "criminal";
        int unexpectedAskedByClient = 3000000;
        Question question1 = new Question(1, "fraud", "Question Title", LocalDate.now(), 2000000, LocalDate.now());
        String unexpectedToString = String.format("This is a %s type question asked by %d", unexpectedType, unexpectedAskedByClient);
        assertNotEquals(unexpectedToString, question1.toString(), "The toString method is wrong");
    }
}


