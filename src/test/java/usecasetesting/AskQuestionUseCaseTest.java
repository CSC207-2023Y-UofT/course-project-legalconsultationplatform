package usecasetesting;

import businessrule.gateway.ClientGateway;
import businessrule.inputboundary.QuestionInputBoundary;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.requestmodel.QuestionRequestModel;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.usecase.AskQuestionInteractor;
import driver.database.ClientRepository;
import driver.database.QuestionGateway;
import driver.database.QuestionRepo;
import entity.QuestionFactory;
import org.junit.jupiter.api.Test;

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
                // assertEquals(true, repo.existsById(12345678));
                return null;
            }
        };

        QuestionGateway questionGateway = new QuestionRepo();
        QuestionFactory questionFactory = new QuestionFactory();
        ClientGateway clientGateway = new ClientRepository(); // It should be the same as the repo above
        QuestionInputBoundary questionInputBoundary = new AskQuestionInteractor(questionGateway, theQuestionOutputBoundary, questionFactory, clientGateway);

        QuestionRequestModel inputData = new QuestionRequestModel("fraud", "Test title", LocalDate.now(), 12345678, LocalDate.now());

        questionInputBoundary.createQuestion(inputData);
    }
}
