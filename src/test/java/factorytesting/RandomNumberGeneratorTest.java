package factorytesting;

import static org.junit.jupiter.api.Assertions.*;

import businessrule.usecase.util.RandomNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RandomNumberGeneratorTest {

    private static final int PASSWORD_LENGTH = 8;

    private RandomNumberGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new RandomNumberGenerator();
    }

    @Test
    void testGenerateClientId() {
        int clientId = generator.generateClientId(PASSWORD_LENGTH);
        assertTrue(String.valueOf(clientId).startsWith("2"), "ClientId should start with 2.");
    }

    @Test
    void testGenerateAttorneyId() {
        int attorneyId = generator.generateAttorneyId(PASSWORD_LENGTH);
        assertTrue(String.valueOf(attorneyId).startsWith("1"), "AttorneyId should start with 1.");
    }

    @Test
    void testGenerateQuestionId() {
        int questionId = generator.generateQuestionId(PASSWORD_LENGTH);
        assertTrue(String.valueOf(questionId).startsWith("3"), "QuestionId should start with 3.");
    }

    @Test
    void testGeneratePostId() {
        int postId = generator.generatePostId(PASSWORD_LENGTH);
        assertTrue(String.valueOf(postId).startsWith("4"), "PostId should start with 4.");
    }
}
