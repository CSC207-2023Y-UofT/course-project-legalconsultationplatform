package repotesting;

import gateway.AttorneyRepository;
import gateway.ClientRepository;
import gateway.DatabaseConnection;
import org.junit.jupiter.api.Test;
import questionentities.Question;
import userentities.Attorney;
import userentities.Client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientRepositoryTest {
    @Test
    public void testMethod() {
        int expectedAttorneyId = 1000000;
        String expectedPassword = "abcd";
        String expectedStateAbb = "state1";
        String expectedPostalCode = "ABCD";

        List<Question> expectedQuestionsList = new ArrayList<Question>();

        Client client = new Client();

        DatabaseConnection database = new DatabaseConnection();

        ClientRepository clientRepository = new ClientRepository();

        clientRepository.addUser(client);
    }
}
