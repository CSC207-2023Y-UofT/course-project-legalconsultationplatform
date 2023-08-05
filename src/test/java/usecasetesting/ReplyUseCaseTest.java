package usecasetesting;

import adapter.presenter.HomePageResponseFormatter;
import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.PostInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.PostRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.usecase.ReplyInteractor;
import driver.database.*;

import entity.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
;

public class ReplyUseCaseTest {
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
    private PostInputBoundary postInputBoundary;
    public void setUpReplyUseCase(){

        questionGateway = new QuestionRepo();
        postGateway = new PostRepo();
        postFactory = new PostFactory();
        userGatewayFactory = new UserGatewayFactory();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        homePageOutputBoundary = new HomePageOutputBoundary() {
            @Override
            public HomePageResponseModel prepareFail(String msg) {
                assertEquals("You cannot reply to this question", msg);
                return null;
            }

            @Override
            public HomePageResponseModel prepareSuccess(HomePageResponseModel homePageResponseModel) {
                return null;
            }
        };
        postInputBoundary = new ReplyInteractor(questionGateway, postGateway, homePageOutputBoundary, postFactory, userGatewayFactory);

        Question question = new Question();
        question.setQuestionId(QUESTION_ID);
        questionGateway.saveQuestion(question);

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
    }

    @Test
    public void testClientReply(){
        setUpReplyUseCase();
        PostRequestModel inputData1 = new PostRequestModel(QUESTION_ID, CLIENT_ID, "Test text");
        postInputBoundary.createPost(inputData1);
        Question question = questionGateway.getQuestion(QUESTION_ID);
        Post post1 = question.getPosts().get(0);
        assertEquals(post1.getBelongsTo(), CLIENT_ID);
        assertEquals(post1.getPostText(), "Test text");
    }

    @Test
    public void testAttorneyFirstReply(){
        setUpReplyUseCase();

        PostRequestModel inputData2 = new PostRequestModel(QUESTION_ID, ATTORNEY_ID, "Test text");
        postInputBoundary.createPost(inputData2);
        Question question = questionGateway.getQuestion(QUESTION_ID);
        Post post2 = question.getPosts().get(0);
        assertEquals(post2.getBelongsTo(), ATTORNEY_ID);
        assertEquals(post2.getPostText(), "Test text");
        User user = attorneyGateway.getUser(ATTORNEY_ID);
        Question attorneyQuestion = user.getQuestionsList().get(0);
        assertEquals(attorneyQuestion.getQuestionId(), QUESTION_ID);
        assertEquals(attorneyQuestion.getTakenByAttorney(), ATTORNEY_ID);
        assertEquals(attorneyQuestion.isTaken(), true);
    }
    @Test
    public void testAttorneyFollowUp(){
        setUpReplyUseCase();
        User user = attorneyGateway.getUser(ATTORNEY_ID);
        Question attorneyquestion = user.getQuestionsList().get(0);
        assertEquals(attorneyquestion.getQuestionId(), QUESTION_ID);
        assertEquals(attorneyquestion.getTakenByAttorney(), ATTORNEY_ID);
        assertEquals(attorneyquestion.isTaken(), true);

        PostRequestModel inputData2 = new PostRequestModel(QUESTION_ID, ATTORNEY_ID, "Test text");// Attorney
        postInputBoundary.createPost(inputData2);
        Question question = questionGateway.getQuestion(QUESTION_ID);
        Post post2 = question.getPosts().get(1);
        assertEquals(post2.getBelongsTo(), ATTORNEY_ID);
        assertEquals(post2.getPostText(), "Test text");
        assertEquals(attorneyquestion.getQuestionId(), QUESTION_ID);
        assertEquals(attorneyquestion.getTakenByAttorney(), ATTORNEY_ID);
        assertEquals(attorneyquestion.isTaken(), true);

    }
    @Test
    public void testFailToReplyQuestionClosed(){
        setUpReplyUseCase();
        PostRequestModel inputData = new PostRequestModel(CLOSED_QUESTION_ID, ATTORNEY_ID, "Test text");
        postInputBoundary.createPost(inputData);
    }

    @Test
    public void testAttorneyFailToReplyQuestionTakenByOther(){
        setUpReplyUseCase();
        PostRequestModel inputData = new PostRequestModel(QUESTION_ID, SECOND_ATTORNEY_ID, "Test text");
        postInputBoundary.createPost(inputData);

        User user = attorneyGateway.getUser(ATTORNEY_ID);
        Question attorneyquestion = user.getQuestionsList().get(0);
        assertEquals(attorneyquestion.getQuestionId(), QUESTION_ID);
        assertEquals(attorneyquestion.getTakenByAttorney(), ATTORNEY_ID);
        assertEquals(attorneyquestion.isTaken(), true);
    }

    //@AfterClass
}