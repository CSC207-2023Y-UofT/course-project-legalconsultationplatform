package factorytesting;

import usecases.requests.ClientRegistrationData;
import usecases.requests.RegistrationData;
import entities.user.Client;
import entities.factories.ClientFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the ClientFactory class.
 */
public class ClientFactoryTest {

    /**
     * Test the create method of ClientFactory.
     */
    @Test
    public void testCreate() {
        ClientFactory factory = new ClientFactory();

        int userId = 1;
        String userName = "Test User";
        String email = "test@example.com";
        String password = "password";
        String password2 = "password2";
        String stateAbb = "CA";
        String postalCode = "12345";
        String ethnicity = "Asian";
        int age = 30;
        String gender = "Male";
        String maritalStatus = "Single";
        int numberOfHousehold = 1;
        float annualIncome = 50000.0f;

        RegistrationData registrationData = new RegistrationData(userName, email, password, password2, stateAbb,
                postalCode);
        ClientRegistrationData inputData= new ClientRegistrationData.Builder(registrationData).age(age)
                .annualIncome(annualIncome)
                .ethnicity(ethnicity)
                .gender(gender)
                .maritalStatus(maritalStatus)
                .numberOfHousehold(numberOfHousehold)
                .build();
        Client client = factory.createUser(inputData);
        client.setUserId(userId);

        assertEquals(userId, client.getUserId());
        assertEquals(userName, client.getUserName());
        assertEquals(email, client.getEmail());
        assertEquals(password, client.getPassword());
        assertEquals(stateAbb, client.getStateAbb());
        assertEquals(postalCode, client.getPostalCode());
        assertEquals(ethnicity, client.getEthnicity());
        assertEquals(age, client.getAge());
        assertEquals(gender, client.getGender());
        assertEquals(maritalStatus, client.getMaritalStatus());
        assertEquals(numberOfHousehold, client.getNumberOfHousehold());
        assertEquals(annualIncome, client.getAnnualIncome(), 0.01f);
    }
}
