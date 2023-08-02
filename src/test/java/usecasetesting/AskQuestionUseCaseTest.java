package usecasetesting;

import askquestion.*;
import org.junit.jupiter.api.Test;
import screen.*;
import userentities.Client;
import userlogin.*;
import gateway.*;
import presenter.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
;

public class AskQuestionUseCaseTest {
    @Test
    public void UseCaseTest(){
        ClientRepository repo = new ClientRepository();
        TheQuestionOutputBoundary theQuestionOutputBoundary = new TheQuestionOutputBoundary() {
            @Override
            public TheQuestionResponseModel prepareFail(String msg) {
                fail("Unexpected use case failure.");
                return null;
            }

            @Override
            public TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response) {
                assertEquals(12345678, response.getUserId());
                assertNotNull(response.getCreationTime());
                // assertEquals(true, repo.existsById(12345678));
                return null;
            }
        };

        QuestionGateway questionGateway = new QuestionRepo();
        QuestionFactory questionFactory = new QuestionFactory();
        ClientGateway clientGateway = new ClientRepository(); // It should be the same as the repo above
        QuestionInputBoundary questionInputBoundary = new AskQuestionInteractor(questionGateway, theQuestionOutputBoundary, questionFactory, clientGateway);

        QuestionRequestModel inputData1 = new QuestionRequestModel("fraud", "Test title", LocalDate.now(), 12345678);
        QuestionRequestModel inputData2 = new QuestionRequestModel("fraud", "Test title", LocalDate.now(), 12345678, LocalDate.now());

        questionInputBoundary.createQuestion(inputData1);
        questionInputBoundary.createQuestion(inputData2);
    }
}
