package usecasetesting;

import adapter.controller.ControlContainer;
import businessrule.gateway.*;
import businessrule.inputboundary.RateInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.RateRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.usecase.RateInteractor;
import driver.database.*;
import entity.Attorney;
import entity.Client;
import entity.factory.PostFactory;
import entity.Question;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the RateAnswerUseCase class.
 */
public class RateAnswerUseCaseTest {

    final static int CLIENT_ID = 21345678;
    final static int ATTORNEY_ID = 11345678;
    final static int SECOND_ATTORNEY_ID = 12222222;
    final static int QUESTION_ID = 323456789;
    final static int CLOSED_QUESTION_ID = 333333333;
    private QuestionGateway questionGateway;
    private PostGateway postGateway;
    private PostFactory postFactory;
    private UserGatewayFactory userGatewayFactory;
    private ClientGateway clientGateway;
    private AttorneyGateway attorneyGateway;
    private HomePageOutputBoundary homePageOutputBoundary;
    private RateInputBoundary rateInputBoundary;

    /**
     * Set up the test environment by initializing the RateAnswerUseCase instance.
     */
    public void setUpRateAnswerUseCase() {
        // Initialize gateways and other components
        questionGateway = new QuestionRepo();
        postGateway = new PostRepo();
        postFactory = new PostFactory();
        userGatewayFactory = new UserGatewayFactory();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();

        // Clear repositories
        clientGateway.deleteAll();
        questionGateway.deleteAll();
        attorneyGateway.deleteAll();
        postGateway.deleteAll();

        // Initialize homePageOutputBoundary
        homePageOutputBoundary = new HomePageOutputBoundary() {
            @Override
            public void setControlContainer(ControlContainer controlContainer) {
            }

            @Override
            public HomePageResponseModel prepareFail(String msg) {
                assertEquals("You cannot rate this question!", msg);
                return null;
            }

            @Override
            public HomePageResponseModel prepareSuccess(HomePageResponseModel homePageResponseModel) {
                return null;
            }
        };

        // Initialize rateInputBoundary
        rateInputBoundary = new RateInteractor(questionGateway, homePageOutputBoundary, clientGateway, attorneyGateway);

        // Create and set up test data
        Question question = new Question();
        question.setQuestionId(QUESTION_ID);

        Question closedQuestion = new Question();
        closedQuestion.setQuestionId(CLOSED_QUESTION_ID);
        closedQuestion.setClose(true);
        closedQuestion.setTakenByAttorney(ATTORNEY_ID);
        closedQuestion.setTaken(true);
        questionGateway.save(closedQuestion);

        Client client = new Client();
        client.setUserId(CLIENT_ID);
        client.addQuestion(question);
        client.addQuestion(closedQuestion);
        clientGateway.save(client);

        Attorney attorney = new Attorney();
        attorney.setUserId(ATTORNEY_ID);
        attorney.setEmail("josephpc0612@gmail.com");
        attorneyGateway.save(attorney);

        question.setTakenByAttorney(ATTORNEY_ID);
        question.setTaken(true);
        questionGateway.save(question);
    }

    /**
     * Test for the scenario when a client rates a closed question.
     */
    @Test
    public void TestClientRateClosedQuestion() {
        setUpRateAnswerUseCase();
        RateRequestModel inputData = new RateRequestModel(10, CLOSED_QUESTION_ID, CLIENT_ID);
        rateInputBoundary.rateAnswer(inputData);
        assertEquals(10, questionGateway.get(CLOSED_QUESTION_ID).getRating());
        ClearAllRepository();
    }

    /**
     * Test for the scenario when a client rates an unclosed question.
     */
    @Test
    public void TestClientRateUnClosedQuestion() {
        setUpRateAnswerUseCase();
        RateRequestModel inputData = new RateRequestModel(10, QUESTION_ID, CLIENT_ID);
        rateInputBoundary.rateAnswer(inputData);
        ClearAllRepository();
    }

    /**
     * Delete all data in clientGateway, questionGateway, attorneyGateway, postGateway.
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
