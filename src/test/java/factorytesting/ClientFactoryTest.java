package factorytesting;

import businessrule.requestmodel.RegistrationData;
import entity.Client;
import entity.factory.ClientFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClientFactoryTest {

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

        RegistrationData inputData = new RegistrationData(userName, email, password, password2, stateAbb,
                postalCode, ethnicity, age, gender, maritalStatus, numberOfHousehold, annualIncome);
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
