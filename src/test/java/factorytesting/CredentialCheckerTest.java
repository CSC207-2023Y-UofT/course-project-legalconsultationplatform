package factorytesting;

import static org.junit.jupiter.api.Assertions.*;

import businessrule.usecase.util.CredentialChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CredentialCheckerTest {

    private CredentialChecker checker;

    @BeforeEach
    void setUpCredentialChecker() {
        checker = new CredentialChecker();
    }

    @Test
    void testCheckPostalCode() {
        assertTrue(checker.checkPostalCode("12345"), "Postal code validation failed.");
        assertFalse(checker.checkPostalCode("1234a"), "Invalid postal code passed validation.");
        assertFalse(checker.checkPostalCode("123456"), "Invalid postal code passed validation.");
        assertFalse(checker.checkPostalCode(""), "Empty postal code passed validation.");
    }


    @Test
    void testCheckEmail() {
        assertTrue(checker.checkEmail("test@example.com"), "Expected valid email to pass validation.");
        assertFalse(checker.checkEmail("test@example"), "Expected email without domain to fail validation.");
        assertFalse(checker.checkEmail("test.com"), "Expected email without '@' to fail validation.");
        assertFalse(checker.checkEmail(""), "Expected empty email to fail validation.");
        assertFalse(checker.checkEmail("   "), "Expected email with only spaces to fail validation.");
    }


    @Test
    void testCheckAge() {
        assertTrue(checker.checkAge(50), "Age validation failed.");
        assertFalse(checker.checkAge(200), "Invalid age passed validation.");
        assertFalse(checker.checkAge(0), "Invalid age passed validation.");
    }
}
