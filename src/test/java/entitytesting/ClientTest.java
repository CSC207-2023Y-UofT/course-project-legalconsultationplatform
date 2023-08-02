package entitytesting;

import questionentities.Question;
import userentities.Client;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testConstructorAndGetter() {
        // expected values
        int expectedUserId = 1000;
        String expectedUserName = "Xingfu Wu";
        String expectedEmail = "xingfu.wu@mail.utorontoca";
        String expectedPassword = "password";
        String expectedStateAbb = "CA";
        String expectedPostalCode = "12345";
        String expectedEthnicity = "Asian";
        int expectedAge = 30;
        String expectedGender = "Male";
        String expectedMaritalStatus = "Single";
        int expectedNumberOfHousehold = 1;
        float expectedAnnualIncome = 10000.0f;

        // constructor
        Client client = new Client(expectedUserId, expectedUserName, expectedEmail, expectedPassword, expectedStateAbb, expectedPostalCode,
                expectedEthnicity, expectedAge, expectedGender, expectedMaritalStatus, expectedNumberOfHousehold, expectedAnnualIncome);

        // assertions
        assertEquals(expectedUserId, client.getUserId(), "UserId is wrong.");
        assertEquals(expectedUserName, client.getUserName(), "Username is incorrect.");
        assertEquals(expectedEmail, client.getEmail(), "Email is wrong.");
        assertEquals(expectedPassword, client.getPassword(), "Password is incorrect.");
        assertEquals(expectedStateAbb, client.getStateAbb(), "State abbreviation is wrong.");
        assertEquals(expectedPostalCode, client.getPostalCode(), "Postal code is wrong.");
        assertEquals(expectedEthnicity, client.getEthnicity(), "Ethnicity is wrong.");
        assertEquals(expectedAge, client.getAge(), "Age is wrong.");
        assertEquals(expectedGender, client.getGender(), "Gender is wrong.");
        assertEquals(expectedMaritalStatus, client.getMaritalStatus(), "Marital status is wrong.");
        assertEquals(expectedNumberOfHousehold, client.getNumberOfHousehold(), "Number of household is incorrect.");
        assertEquals(expectedAnnualIncome, client.getAnnualIncome(), "Annual income is wrong.");
        assertTrue(client.getQuestionsList().isEmpty(), "Questions list should be empty initially.");
        assertTrue(client.isClient(), "isClient should be true for Client.");
    }

    @Test
    void testSetters() {
        // expected values
        int expectedUserId = 1000;
        String expectedUserName = "Xingfu Wu";
        String expectedEmail = "xingfu.wu@mail.utoronto.ca";
        String expectedPassword = "password";
        String expectedStateAbb = "CA";
        String expectedPostalCode = "12345";
        String expectedEthnicity = "Asian";
        int expectedAge = 30;
        String expectedGender = "Male";
        String expectedMaritalStatus = "Single";
        int expectedNumberOfHousehold = 1;
        float expectedAnnualIncome = 10000.0f;

        // no-arg constructor
        Client client = new Client();

        // use setters to set values
        client.setUserId(expectedUserId);
        client.setUserName(expectedUserName);
        client.setEmail(expectedEmail);
        client.setPassword(expectedPassword);
        client.setStateAbb(expectedStateAbb);
        client.setPostalCode(expectedPostalCode);
        client.setEthnicity(expectedEthnicity);
        client.setAge(expectedAge);
        client.setGender(expectedGender);
        client.setMaritalStatus(expectedMaritalStatus);
        client.setNumberOfHousehold(expectedNumberOfHousehold);
        client.setAnnualIncome(expectedAnnualIncome);

        // assertions
        assertEquals(expectedUserId, client.getUserId(), "UserId is incorrect.");
        assertEquals(expectedUserName, client.getUserName(), "Username is incorrect.");
        assertEquals(expectedEmail, client.getEmail(), "Email is wrong.");
        assertEquals(expectedPassword, client.getPassword(), "Password is incorrect.");
        assertEquals(expectedStateAbb, client.getStateAbb(), "State abbreviation is wrong.");
        assertEquals(expectedPostalCode, client.getPostalCode(), "Postal code is wrong.");
        assertEquals(expectedEthnicity, client.getEthnicity(), "Ethnicity is wrong.");
        assertEquals(expectedAge, client.getAge(), "Age is wrong.");
        assertEquals(expectedGender, client.getGender(), "Gender is wrong.");
        assertEquals(expectedMaritalStatus, client.getMaritalStatus(), "Marital status is wrong.");
        assertEquals(expectedNumberOfHousehold, client.getNumberOfHousehold(), "Number of household is wrong.");
        assertEquals(expectedAnnualIncome, client.getAnnualIncome(), "Annual income is wrong.");
    }

    @Test
    void testIsQuestionCloseableAndSelectable() {
        // create a client
        Client client = new Client(1000, "Xingfu Wu", "xingfu.wu@mail.utoronto.ca", "password", "CA", "12345",
                "Asian", 30, "Male", "Single", 1, 10000.0f);

        // create a question
        Question question = new Question(2000000, "fraud", LocalDate.now(), client.getUserId(), LocalDate.now());

        // check if the question is closeable and selectable
        assertTrue(client.isQuestionCloseable(question), "The question should be closeable by the client who asked it.");
        assertTrue(client.isQuestionSelectable(question), "The question should always be selectable by a client.");
    }
    @Test
    void testAddQuestion() {
        // create a client
        Client client = new Client(1000, "Xingfu Wu", "xingfu.wu@mail.utoronto.ca", "password", "CA", "12345",
                "Asian", 30, "Male", "Single", 1, 10000.0f);

        // create a question
        Question question = new Question(2000000, "fraud", LocalDate.now(), client.getUserId(), LocalDate.now());

        // add the question to the client's questions list
        client.addQuestion(question);

        // check if the question has been added to the list
        assertTrue(client.getQuestionsList().contains(question), "The question should be added to the questions list.");
    }
}






        }
