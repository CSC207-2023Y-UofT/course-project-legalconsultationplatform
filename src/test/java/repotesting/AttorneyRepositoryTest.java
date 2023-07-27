package repotesting;

import gateway.AttorneyRepository;
import gateway.DatabaseConnection;
import org.junit.jupiter.api.Test;
import questionentities.Question;
import userentities.Attorney;
import userentities.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class AttorneyRepositoryTest {

    int expectedAttorneyId = 1000000;
    String expectedPassword = "abcd";
    String expectedStateAbb = "state1";
    String expectedPostalCode = "ABCD";

    List<Question> expectedQuestionsList = new ArrayList<Question>();

    Attorney attorney = new Attorney(expectedAttorneyId, expectedPassword, expectedStateAbb, expectedPostalCode);

    DatabaseConnection database = new DatabaseConnection();
    AttorneyRepository attorneyRepository = new AttorneyRepository(database);

    attorneyRepository.


}
