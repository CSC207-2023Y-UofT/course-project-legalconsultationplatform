package repotesting;

import gateway.AttorneyRepository;
import gateway.ClientRepository;
import gateway.DatabaseConnection;
import org.junit.jupiter.api.Test;
import questionentities.Question;
import userentities.Attorney;
import userentities.Client;
import userentities.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientRepositoryTest {
    @Test
    public void testAddUserAndGetUser() {
        int expectedClientId = 1000001;

        Client client = new Client();
        client.setUserId(expectedClientId);

        ClientRepository clientRepository = new ClientRepository();

        clientRepository.addUser(client);

        assert client.equals(clientRepository.getUser(expectedClientId));

    }

    @Test
    public void testUpdateQuestionList(){

        Client client = new Client();

        client.setUserId(1111113);

        ClientRepository clientRepository = new ClientRepository();

        clientRepository.addUser(client);

        int expectedQuestionId = 1000000;
        String expectedType = "test type";
        LocalDate expectedCreateAt = LocalDate.now();
        int expectedAskedByClient = 1111113;
        LocalDate expectedLegalDeadline = LocalDate.now();

        Question question = new Question(expectedQuestionId, expectedType, expectedCreateAt, expectedAskedByClient, expectedLegalDeadline);

        clientRepository.updateQuestionList(1111111, question);

        assertEquals(clientRepository.getUser(1111111).getQuestionsList().get(0).getQuestionId(), expectedQuestionId, "The update question list failed");









    }
}
