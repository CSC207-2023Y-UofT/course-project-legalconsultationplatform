package usecasetesting;

import adapter.controller.ControlContainer;
import businessrule.gateway.*;
import businessrule.inputboundary.CloseInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.CloseRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.usecase.CloseQuestionInteractor;
import driver.database.*;
import entity.Attorney;
import entity.Client;
import entity.factory.PostFactory;
import entity.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains test cases for the CloseUseCase class.
 */
public class CloseUseCaseTest {

    // IDs for testing purposes
    final static int CLIENT_ID = 21345678;
    final static int ATTORNEY_ID = 11345678;
    final static int SECOND_ATTORNEY_ID = 12222222;
    final static int QUESTION_ID = 323456789;
    final static int CLOSED_QUESTION_ID = 333333333;

    // Gateways and use case components
    private QuestionGateway questionGateway;
    private PostGateway postGateway;
    private PostFactory postFactory;
    private UserGatewayFactory userGatewayFactory;
    private ClientGateway clientGateway;
    private AttorneyGateway attorneyGateway;
    private HomePageOutputBoundary homePageOutputBoundary;
    private CloseInputBoundary closeInputBoundary;

    /**
     * Set up the test environment by initializing the CloseUseUseCase instance.
     */
    public void setUpCloseUseCase() {
        // Initialize gateways and use case components
        questionGateway = new QuestionRepo();
        postGateway = new PostRepo();
        postFactory = new PostFactory();
        userGatewayFactory = new UserGatewayFactory();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();

        // Mocking homePageOutputBoundary
        homePageOutputBoundary = new HomePageOutputBoundary() {
            @Override
            public void setControlContainer(ControlContainer controlContainer) {
                // No implementation needed for testing
            }

            @Override
            public HomePageResponseModel prepareFail(String msg) {
                assertEquals("You cannot close this question!", msg);
                return null;
            }

            @Override
            public HomePageResponseModel prepareSuccess(HomePageResponseModel homePageResponseModel) {
                // No implementation needed for testing
                return null;
            }
        };

        // Initialize closeInputBoundary
        closeInputBoundary = new CloseQuestionInteractor(questionGateway, homePageOutputBoundary, userGatewayFactory);

        // Setting up test data
        Question question = new Question();
        question.setQuestionId(QUESTION_ID);

        Question closedQuestion = new Question();
        closedQuestion.setQuestionId(CLOSED_QUESTION_ID);
        questionGateway.save(closedQuestion);

        Client client = new Client();
        client.setUserId(CLIENT_ID);
        client.addQuestion(question);
        clientGateway.save(client);

        Attorney attorney = new Attorney();
        attorney.setUserId(ATTORNEY_ID);
        attorneyGateway.save(attorney);

        Attorney secondAttorney = new Attorney();
        attorney.setUserId(SECOND_ATTORNEY_ID);
        attorneyGateway.save(secondAttorney);

        question.setTakenByAttorney(ATTORNEY_ID);
        question.setTaken(true);
        questionGateway.save(question);
    }

    /**
     * Test case for closing a question by a client.
     */
    @Test
    public void TestClientCloseableQuestion() {
        setUpCloseUseCase();

        CloseRequestModel inputData = new CloseRequestModel(QUESTION_ID, CLIENT_ID);

        closeInputBoundary.closeQuestion(inputData);
        Question question = questionGateway.get(QUESTION_ID);
        assertEquals(question.isClose(), true);
        ClearAllRepository();
    }

    /**
     * Test case for attempting to close an already closed question by a client.
     */
    @Test
    public void TestClientUnclosableQuestion() {
        setUpCloseUseCase();
        CloseRequestModel inputData = new CloseRequestModel(CLOSED_QUESTION_ID, CLIENT_ID);

        closeInputBoundary.closeQuestion(inputData);
        ClearAllRepository();
    }

    /**
     * Test case for attempting to close a question by an attorney when it's already closed.
     */
    @Test
    public void TestAttorneyClosedQuestion() {
        setUpCloseUseCase();
        CloseRequestModel inputData = new CloseRequestModel(CLOSED_QUESTION_ID, CLIENT_ID);

        closeInputBoundary.closeQuestion(inputData);
        ClearAllRepository();
    }

    /**
     * Test case for attempting to close a question by an attorney when the question is not taken by them.
     */
    @Test
    public void TestAttorneyNonTakenQuestion() {
        setUpCloseUseCase();
        CloseRequestModel inputData = new CloseRequestModel(CLOSED_QUESTION_ID, CLIENT_ID);

        closeInputBoundary.closeQuestion(inputData);
        ClearAllRepository();
    }

    /**
     * Delete all data in clientGateway.
     */
    public void ClearAllRepository() {
        questionGateway = new QuestionRepo();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        postGateway = new PostRepo();
        clientGateway.deleteAll();
        questionGateway.deleteAll();
        attorneyGateway.deleteAll();
        postGateway.deleteAll();
    }
}