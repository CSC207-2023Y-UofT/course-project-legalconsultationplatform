package usecasetesting;

import businessrule.SessionManager;
import businessrule.UserSession;
import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.QuestionGateway;
import businessrule.inputboundary.QuestionInputBoundary;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.requestmodel.QuestionRequestModel;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.responsemodel.UserResponseModel;
import businessrule.usecase.AskQuestionInteractor;
import driver.database.*;
import entity.*;
import entity.factory.QuestionFactory;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;


/**
 * This class contains unit tests for the AskQuestionUseCase class.
 */
public class AskQuestionUseCaseTest {
    final static int CLIENT_ID = 21345678;
    final static String CLIENT_USERNAME = "test client";
    final static String CLIENT_TYPE = "Client";
    final static int ATTORNEY_ID = 11345678;
    final static int SECOND_ATTORNEY_ID = 12222222;
    private QuestionGateway questionGateway;
    private ClientGateway clientGateway;
    private AttorneyGateway attorneyGateway;
    private QuestionInputBoundary questionInputBoundary;

    /**
     * Set up the test environment by initializing the AskQuestionUseCase instance.
     */
    public void setUpAskQuestionUseCase(){
        questionGateway = new QuestionRepo();
        QuestionFactory questionFactory = new QuestionFactory();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        TheQuestionOutputBoundary theQuestionOutputBoundary = new TheQuestionOutputBoundary() {

            @Override
            public TheQuestionResponseModel prepareFail(String msg) {
                assertEquals("Please specify your question type.", msg);
                return null;
            }

            @Override
            public TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response) {
                assertEquals(0, response.getPostMap().size(), "PostMap is not correct");
                return null;
            }

        };

        questionInputBoundary = new AskQuestionInteractor(questionGateway, theQuestionOutputBoundary, questionFactory, clientGateway);

        Client client = new Client();
        client.setUserId(CLIENT_ID);
        clientGateway.save(client);

        Attorney attorney = new Attorney();
        attorney.setUserId(ATTORNEY_ID);
        attorneyGateway.save(attorney);

        Attorney secondAttorney = new Attorney();
        attorney.setUserId(SECOND_ATTORNEY_ID);
        attorneyGateway.save(secondAttorney);

        UserResponseModel userResponseModel = new UserResponseModel(CLIENT_ID, CLIENT_USERNAME, CLIENT_TYPE);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);
    }

    /**
     * Test the AskQuestionUseCase when the question is created successfully.
     */
    @Test
    public void TestAskQuestionPassed(){
        setUpAskQuestionUseCase();

        QuestionRequestModel inputData = new QuestionRequestModel("fraud", "Test title", LocalDate.now(), LocalDate.now());

        questionInputBoundary.createQuestion(inputData);

        User user = clientGateway.get(CLIENT_ID);
        assertEquals(1, user.getQuestionsList().size(), "The ask question use case failed.");
        ClearAllRepository();
    }

    /**
     * Test the AskQuestionUseCase when the question creation fails due to an empty category.
     */
    @Test
    public void TestAskQuestionFailByEmptyCategory(){
        setUpAskQuestionUseCase();

        QuestionRequestModel inputData = new QuestionRequestModel(null, "Test title", LocalDate.now(), LocalDate.now());

        questionInputBoundary.createQuestion(inputData);

        User user = clientGateway.get(CLIENT_ID);
        assertEquals(0, user.getQuestionsList().size(), "The ask question use case failed.");
        ClearAllRepository();
    }

    /**
     * Delete all data in questionGateway, clientGateway and attorneyGateway.
     */
    public void clearAllRepository(){
        questionGateway = new QuestionRepo();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        clientGateway.deleteAll();
        questionGateway.deleteAll();
        attorneyGateway.deleteAll();
    }
}
