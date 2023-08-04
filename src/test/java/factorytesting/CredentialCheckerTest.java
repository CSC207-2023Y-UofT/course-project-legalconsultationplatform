package factorytesting;

import static org.junit.jupiter.api.Assertions.*;

import entity.CredentialChecker;
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
        assertEquals(true, checker.checkPostalCode("12345"), "Postal code validation failed.");
        assertEquals(false, checker.checkPostalCode("1234a"), "Invalid postal code passed validation.");
        assertEquals(false, checker.checkPostalCode("123456"), "Invalid postal code passed validation.");
        //check postcode 空的，不是数字的，
    }

    @Test
    void testCheckEmail() {
        assertEquals(true, checker.checkEmail("test@example.com"), "Email validation failed.");
        assertEquals(false, checker.checkEmail("test@example"), "Invalid email passed validation.");
        assertEquals(false, checker.checkEmail("test.com"), "Invalid email passed validation.");
        //空的email string
    }

    @Test
    void testCheckAge() {
        assertEquals(true, checker.checkAge(50), "Age validation failed.");
        assertEquals(false, checker.checkAge(200), "Invalid age passed validation.");
        assertEquals(false, checker.checkAge(0), "Invalid age passed validation.");
    }
}
