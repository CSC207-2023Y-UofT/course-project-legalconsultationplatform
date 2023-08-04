package usecasetesting;

import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.ClientGateway;
import businessrule.inputboundary.QuestionInputBoundary;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.requestmodel.QuestionRequestModel;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.usecase.AskQuestionInteractor;

import driver.database.*;
import entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
;

public class AskQuestionUseCaseTest {
    final static int CLIENT_ID = 11345678;
    final static int ATTORNEY_ID = 21345678;
    final static int SECOND_ATTORNEY_ID = 22222222;
    private QuestionGateway questionGateway;
    private QuestionFactory questionFactory;
    private ClientGateway clientGateway;
    private AttorneyGateway attorneyGateway;
    private TheQuestionOutputBoundary theQuestionOutputBoundary;
    private QuestionInputBoundary questionInputBoundary;

    public void setUpAskQuestionUseCase(){
        questionGateway = new QuestionRepo();
        questionFactory = new QuestionFactory();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        clientGateway.deleteAllUser();
        questionGateway.deleteAllQuestion();
        attorneyGateway.deleteAllUser();
        theQuestionOutputBoundary = new TheQuestionOutputBoundary() {
            @Override
            public TheQuestionResponseModel prepareFail(String msg) {
                assertEquals("Please specify your question type.", msg);
                return null;
            }

            @Override
            public TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response) {
                System.out.println(response.getPostMap());
                return null;
            }
        };

        questionInputBoundary = new AskQuestionInteractor(questionGateway, theQuestionOutputBoundary, questionFactory, clientGateway);

        Client client = new Client();
        client.setUserId(CLIENT_ID);
        clientGateway.addUser(client);

        Attorney attorney = new Attorney();
        attorney.setUserId(ATTORNEY_ID);
        attorneyGateway.addUser(attorney);

        Attorney secondAttorney = new Attorney();
        attorney.setUserId(SECOND_ATTORNEY_ID);
        attorneyGateway.addUser(secondAttorney);
    }

    @Test
    public void TestAskQuestionPassed(){
        setUpAskQuestionUseCase();

        QuestionRequestModel inputData = new QuestionRequestModel("fraud", "Test title", LocalDate.now(), CLIENT_ID, LocalDate.now());

        questionInputBoundary.createQuestion(inputData);

        User user = clientGateway.getUser(CLIENT_ID);
        assertEquals(user.getQuestionsList().size(), 1, "The ask question use case failed.");

    }

    @Test
    public void TestAskQuestionFailByEmptyCategory(){
        setUpAskQuestionUseCase();

        QuestionRequestModel inputData = new QuestionRequestModel(null, "Test title", LocalDate.now(), CLIENT_ID, LocalDate.now());

        questionInputBoundary.createQuestion(inputData);
    }
}
