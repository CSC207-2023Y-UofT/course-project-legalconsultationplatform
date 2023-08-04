package factorytesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RandomNumberGeneratorTest {

    private RandomNumberGenerator randomNumberGenerator;

    @BeforeEach
    void setUp() {
        randomNumberGenerator = new RandomNumberGenerator();
    }

    @Test
    void testGenerate() {
        int digit = 3;
        int number = randomNumberGenerator.generate(digit);
        assertTrue(number >= 100 && number <= 999, "Generated number is not in correct range");
    }

    @Test
    void testAddDigitToFront() {
        int number = 123;
        int digit = 4;
        int newNumber = randomNumberGenerator.addDigitToFront(number, digit);
        assertEquals(4123, newNumber, "New number is incorrect");
    }

    @Test
    void testGenerateAttorneyId() {
        int digit = 5;
        int id = randomNumberGenerator.generateAttorneyId(digit);
        assertTrue(id >= 10000 && id <= 19999, "Generated AttorneyId is not in correct range");
    }

    @Test
    void testGenerateClientId() {
        int digit = 5;
        int id = randomNumberGenerator.generateClientId(digit);
        assertTrue(id >= 20000 && id <= 29999, "Generated ClientId is not in correct range");
    }

    @Test
    void testGenerateQuestionId() {
        int digit = 5;
        int id = randomNumberGenerator.generateQuestionId(digit);
        assertTrue(id >= 30000 && id <= 39999, "Generated QuestionId is not in correct range");
    }

    @Test
    void testGeneratePostId() {
        int digit = 5;
        int id = randomNumberGenerator.generatePostId(digit);
        assertTrue(id >= 40000 && id <= 49999, "Generated PostId is not in correct range");
    }
}
