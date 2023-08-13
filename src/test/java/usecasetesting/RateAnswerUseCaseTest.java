package usecasetesting;


import adapter.controller.ControlContainer;
import businessrule.SessionManager;
import businessrule.UserSession;
import businessrule.gateway.*;

import businessrule.inputboundary.RateInputBoundary;

import businessrule.outputboundary.UserOutputBoundary;
import businessrule.requestmodel.RateRequestModel;

import businessrule.responsemodel.UserResponseModel;
import businessrule.usecase.RateInteractor;

import driver.database.*;
import entity.Attorney;
import entity.Client;
import entity.factory.PostFactory;
import entity.Question;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class RateAnswerUseCaseTest {

    final static int CLIENT_ID = 21345678;
    final static String CLIENT_USERNAME = "test client";
    final static String CLIENT_TYPE = "Client";
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
    private UserOutputBoundary userOutputBoundary;
    private RateInputBoundary rateInputBoundary;

    public void setUpRateAnswerUseCase(){

        questionGateway = new QuestionRepo();
        postGateway = new PostRepo();
        postFactory = new PostFactory();
        userGatewayFactory = new UserGatewayFactory();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        clientGateway.deleteAll();
        questionGateway.deleteAll();
        attorneyGateway.deleteAll();
        postGateway.deleteAll();
        userOutputBoundary = new UserOutputBoundary() {
            @Override
            public void setControlContainer(ControlContainer controlContainer) {

            }

            @Override
            public UserResponseModel prepareFail(String msg) {
                assertEquals("You cannot rate this question!", msg);
                return null;
            }

            @Override
            public UserResponseModel prepareSuccess(UserResponseModel userResponseModel) {
                return null;
            }
        };
        rateInputBoundary = new RateInteractor(questionGateway, userOutputBoundary,  clientGateway, attorneyGateway);

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
        attorney.setEmail("josephpc061@gmail.com");
        attorneyGateway.save(attorney);

        question.setTakenByAttorney(ATTORNEY_ID);
        question.setTaken(true);
        questionGateway.save(question);

        UserResponseModel userResponseModel = new UserResponseModel(CLIENT_ID, CLIENT_USERNAME, CLIENT_TYPE);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);
    }
    @Test
    public void TestClientRateClosedQuestion(){
        setUpRateAnswerUseCase();

        RateRequestModel inputData = new RateRequestModel(10, CLOSED_QUESTION_ID);
        rateInputBoundary.rateAnswer(inputData);
        assertEquals(10, questionGateway.get(CLOSED_QUESTION_ID).getRating());
        ClearAllRepository();
    }
    @Test
    public void TestClientRateUnClosedQuestion(){
        setUpRateAnswerUseCase();

        RateRequestModel inputData = new RateRequestModel(10, QUESTION_ID);
        rateInputBoundary.rateAnswer(inputData);
        ClearAllRepository();
    }
    public void ClearAllRepository(){
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
