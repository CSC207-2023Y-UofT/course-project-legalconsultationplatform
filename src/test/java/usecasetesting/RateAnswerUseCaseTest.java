package usecasetesting;


import businessrule.gateway.*;

import businessrule.inputboundary.RateInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.RateRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.usecase.RateInteractor;

import driver.database.*;
import entity.Attorney;
import entity.Client;
import entity.PostFactory;
import entity.Question;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
;

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

    public void setUpRateAnswerUseCase(){

        questionGateway = new QuestionRepo();
        postGateway = new PostRepo();
        postFactory = new PostFactory();
        userGatewayFactory = new UserGatewayFactory();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        clientGateway.deleteAllUser();
        questionGateway.deleteAllQuestion();
        postGateway.deleteAllPost();
        attorneyGateway.deleteAllUser();
        homePageOutputBoundary = new HomePageOutputBoundary() {
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
        rateInputBoundary = new RateInteractor(questionGateway, homePageOutputBoundary, userGatewayFactory);

        Question question = new Question();
        question.setQuestionId(QUESTION_ID);


        Question closedQuestion = new Question();
        closedQuestion.setQuestionId(CLOSED_QUESTION_ID);
        closedQuestion.setClose(true);
        questionGateway.saveQuestion(closedQuestion);

        Client client = new Client();
        client.setUserId(CLIENT_ID);
        client.addQuestion(question);
        client.addQuestion(closedQuestion);
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
    public void TestClientRateClosedQuestion(){
        setUpRateAnswerUseCase();
        RateRequestModel inputData = new RateRequestModel(10, CLOSED_QUESTION_ID, CLIENT_ID);
        rateInputBoundary.rateAnswer(inputData);
        assertEquals(10, questionGateway.getQuestion(CLOSED_QUESTION_ID).getRating());// rating not update
    }
    @Test
    public void TestClientRateUnClosedQuestion(){
        setUpRateAnswerUseCase();
        RateRequestModel inputData = new RateRequestModel(10, QUESTION_ID, CLIENT_ID);
        rateInputBoundary.rateAnswer(inputData);
    }
    @Test
    public void TestAttorneyRate(){
        setUpRateAnswerUseCase();
        RateRequestModel inputData = new RateRequestModel(10, QUESTION_ID, ATTORNEY_ID);
        rateInputBoundary.rateAnswer(inputData);
    }
}
