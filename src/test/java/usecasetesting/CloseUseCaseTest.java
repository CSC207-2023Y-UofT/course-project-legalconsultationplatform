package usecasetesting;


import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.CloseInputBoundary;
import businessrule.inputboundary.PostInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.CloseRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.usecase.CloseQuestionInteractor;
import businessrule.usecase.ReplyInteractor;
import driver.database.*;
import entity.Attorney;
import entity.Client;
import entity.PostFactory;
import entity.Question;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
;

public class CloseUseCaseTest {
    final static int CLIENT_ID = 11345678;
    final static int ATTORNEY_ID = 21345678;
    final static int SECOND_ATTORNEY_ID = 22222222;
    final static int QUESTION_ID = 323456789;
    final static int CLOSED_QUESTION_ID = 333333333;
    private QuestionGateway questionGateway;
    private PostGateway postGateway;
    private PostFactory postFactory;
    private UserGatewayFactory userGatewayFactory;
    private ClientGateway clientGateway;
    private AttorneyGateway attorneyGateway;
    private HomePageOutputBoundary homePageOutputBoundary;
    private CloseInputBoundary closeInputBoundary;

    @BeforeClass
    public void setUpCloseUseCase(){

        questionGateway = new QuestionRepo();
        postGateway = new PostRepo();
        postFactory = new PostFactory();
        userGatewayFactory = new UserGatewayFactory();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        homePageOutputBoundary = new HomePageOutputBoundary() {
            @Override
            public HomePageResponseModel prepareFail(String msg) {
                assertEquals("You cannot close this question!", msg);
                return null;
            }

            @Override
            public HomePageResponseModel prepareSuccess(HomePageResponseModel homePageResponseModel) {
                return null;
            }
        };
        closeInputBoundary = new CloseQuestionInteractor(questionGateway, homePageOutputBoundary, userGatewayFactory);

        Question question = new Question();
        question.setQuestionId(QUESTION_ID);


        Question closedQuestion = new Question();
        closedQuestion.setQuestionId(CLOSED_QUESTION_ID);
        questionGateway.saveQuestion(closedQuestion);

        Client client = new Client();
        client.setUserId(CLIENT_ID);
        client.addQuestion(question);
        clientGateway.addUser(client);

        Attorney attorney = new Attorney();
        attorney.setUserId(ATTORNEY_ID);
        attorneyGateway.addUser(attorney);

        Attorney secondAttorney = new Attorney();
        attorney.setUserId(SECOND_ATTORNEY_ID);
        attorneyGateway.addUser(secondAttorney);

        question.setTakenByAttorney(ATTORNEY_ID);
        question.setTaken(true);
        questionGateway.saveQuestion(question);
    }
    @Test
    public void TestClientCloseableQuestion(){
        setUpCloseUseCase();


        CloseRequestModel inputData = new CloseRequestModel(QUESTION_ID, CLIENT_ID);

        closeInputBoundary.closeQuestion(inputData);
        Question question = questionGateway.getQuestion(QUESTION_ID);
        assertEquals(question.isClose(), true);
    }
    @Test
    public void TestAttorneyCloseableQuestion(){
        setUpCloseUseCase();
        CloseRequestModel inputData = new CloseRequestModel(QUESTION_ID, ATTORNEY_ID);

        closeInputBoundary.closeQuestion(inputData);
        Question question = questionGateway.getQuestion(QUESTION_ID);
        assertEquals(question.isClose(), true);
    }
    @Test
    public void TestClientUnclosableQuestion(){
        setUpCloseUseCase();
        CloseRequestModel inputData = new CloseRequestModel(CLOSED_QUESTION_ID, CLIENT_ID);

        closeInputBoundary.closeQuestion(inputData);
    }

    @Test
    public void TestAttorneyClosedQuestion(){
        setUpCloseUseCase();
        CloseRequestModel inputData = new CloseRequestModel(CLOSED_QUESTION_ID, CLIENT_ID);

        closeInputBoundary.closeQuestion(inputData);
    }

    @Test
    public void TestAttorneyNonTakenQuestion(){
        setUpCloseUseCase();
        CloseRequestModel inputData = new CloseRequestModel(CLOSED_QUESTION_ID, CLIENT_ID);

        closeInputBoundary.closeQuestion(inputData);
    }

    //@AfterClass

}