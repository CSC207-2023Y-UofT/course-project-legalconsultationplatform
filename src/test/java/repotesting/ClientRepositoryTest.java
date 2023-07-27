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
    public void testAddUserAndGetUser() {
        int expectedClientId = 1000000;

        Client client = new Client();
        client.setUserId(expectedClientId);

        ClientRepository clientRepository = new ClientRepository();

        clientRepository.addUser(client);
        assertEquals(clientRepository.getUser(expectedClientId), client, "The two clients are not the same");

    }

    @Test
    public void testUpdateQuestionList(){

        Client client = new Client();

        ClientRepository clientRepository = new ClientRepository();

        clientRepository.addUser(client);

        int expectedQuestionId = 1000000;
        String expectedType = "test type";
        LocalDate expectedCreateAt = LocalDate.now();
        int expectedAskedByClient = 1111111;
        LocalDate expectedLegalDeadline = LocalDate.now();

        Question question = new Question(expectedQuestionId, expectedType, expectedCreateAt, expectedAskedByClient, expectedLegalDeadline);

        List<Question> expectedQuestionsList = new ArrayList<Question>();






    }
}
