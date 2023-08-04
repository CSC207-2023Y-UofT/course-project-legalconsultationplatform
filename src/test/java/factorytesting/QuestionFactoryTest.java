package factorytesting;

import static org.junit.jupiter.api.Assertions.*;

import entity.Question;
import entity.QuestionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class QuestionFactoryTest {

    private QuestionFactory questionFactory;
    private int questionId;
    private String type;
    private String title;
    private LocalDate createAt;
    private int askedByClient;
    private LocalDate legalDeadline;

    @BeforeEach
    void setUp() {
        questionFactory = new QuestionFactory();
        questionId = 1;
        type = "test type";
        title = "test title";
        createAt = LocalDate.now();
        askedByClient = 2;
        legalDeadline = LocalDate.now();
    }

    @Test
    void testCreate() {
        Question question = questionFactory.create(questionId, type, title, createAt, askedByClient, legalDeadline);

        assertEquals(questionId, question.getQuestionId(), "QuestionId is incorrect.");
        assertEquals(type, question.getType(), "Type is incorrect.");
        assertEquals(title, question.getTitle(), "Title is incorrect.");
        assertEquals(createAt, question.getCreateAt(), "CreateAt is incorrect.");
        assertEquals(askedByClient, question.getAskedByClient(), "AskedByClient is incorrect.");
        assertEquals(legalDeadline, question.getLegalDeadline(), "LegalDeadline is incorrect.");
    }
}
