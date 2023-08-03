package entitytesting;

import org.junit.jupiter.api.Test;
import entity.Question;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {

    @Test
    public void testQuestionEntityMethod(){
        int expectedQuestionId = 1000000;
        LocalDate expectedCreateAt = LocalDate.now();
        String expectedType = "fraud";
        int expectedAskedByClient = 2000000;
        LocalDate expectedLegalDeadline = LocalDate.now();

        // first constructor
        Question question = new Question(expectedQuestionId, expectedType, expectedCreateAt, expectedAskedByClient, expectedLegalDeadline);
        assertEquals(expectedQuestionId, question.getQuestionId(), "QuestionId is wrong.");
        assertEquals(expectedType, question.getType(), "Question type is wrong.");
        assertEquals(expectedCreateAt, question.getCreateAt(), "CreateAt is wrong.");
        assertEquals(expectedLegalDeadline, question.getLegalDeadline(), "LegalDeadline is wrong.");

        // second constructor
        boolean expectedIsTaken = false;



    }

}