package usecasetesting;


import entities.user.Attorney;
import entities.user.Client;
import entities.user.User;
import usecases.session.SessionManager;
import usecases.session.UserSession;
import usecases.gateway.*;
import usecases.inputboundary.PostInputBoundary;
import usecases.outputboundary.TheQuestionOutputBoundary;
import usecases.requests.PostRequestModel;
import usecases.responses.TheQuestionResponseModel;
import usecases.responses.UserResponseModel;
import usecases.interactors.ReplyInteractor;
import infrastructure.database.*;
import entities.*;
import entities.factories.PostFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains test cases for the ReplyUseCase.
 */
public class ReplyUseCaseTest {
    final static int CLIENT_ID = 21345678;
    final static String CLIENT_USERNAME = "test client";
    final static String CLIENT_TYPE = "Client";
    final static int ATTORNEY_ID = 11345678;
    final static String ATTORNEY_USERNAME = "test attorney";
    final static String ATTORNEY_TYPE = "Attorney";
    final static int SECOND_ATTORNEY_ID = 12222222;
    final static int QUESTION_ID = 323456789;
    final static int CLOSED_QUESTION_ID = 333333333;
    private QuestionGateway questionGateway;
    private PostGateway postGateway;
    private ClientGateway clientGateway;
    private AttorneyGateway attorneyGateway;
    private PostInputBoundary postInputBoundary;

    /**
     * Set up the test environment by initializing the ReplyUseCase instance.
     */
    public void setUpReplyUseCase(){

        questionGateway = new QuestionRepo();
        postGateway = new PostRepo();
        PostFactory postFactory = new PostFactory();
        UserGatewayFactory userGatewayFactory = new UserGatewayFactory();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        TheQuestionOutputBoundary theQuestionOutputBoundary = new TheQuestionOutputBoundary() {

            @Override
            public TheQuestionResponseModel prepareFail(String msg) {
                assertEquals("You cannot reply to this question", msg);
                return null;
            }

            @Override
            public TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response) {
                return null;
            }

        };
        postInputBoundary = new ReplyInteractor(questionGateway, postGateway, theQuestionOutputBoundary, postFactory, userGatewayFactory);

        Question question = new Question();
        question.setQuestionId(QUESTION_ID);
        question.setAskedByClient(CLIENT_ID);
        questionGateway.save(question);

        Question closedQuestion = new Question();
        closedQuestion.setQuestionId(CLOSED_QUESTION_ID);
        closedQuestion.setAskedByClient(CLIENT_ID);
        questionGateway.save(closedQuestion);

        Client client = new Client();
        client.setUserId(CLIENT_ID);
        client.setEmail("josephpc061@gmail.com");
        client.addQuestion(question);
        clientGateway.save(client);

        Attorney attorney = new Attorney();
        attorney.setUserId(ATTORNEY_ID);
        attorney.setEmail("josephpc061@gmail.com");
        attorneyGateway.save(attorney);

        Attorney secondAttorney = new Attorney();
        secondAttorney.setUserId(SECOND_ATTORNEY_ID);
        attorneyGateway.save(secondAttorney);
    }

    /**
     * Test client's reply to a question.
     */
    @Test
    public void testClientReply(){
        setUpReplyUseCase();
        UserResponseModel userResponseModel = new UserResponseModel(CLIENT_ID, CLIENT_USERNAME, CLIENT_TYPE);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);
        PostRequestModel inputData1 = new PostRequestModel(QUESTION_ID, "Test text");
        postInputBoundary.createPost(inputData1);
        Question question = questionGateway.get(QUESTION_ID);
        Post post1 = question.getPosts().get(0);
        assertEquals(post1.getBelongsTo(), CLIENT_ID);
        assertEquals(post1.getPostText(), "Test text");
        ClearAllRepository();
    }

    /**
     * Test attorney's first reply to a question.
     */
    @Test
    public void testAttorneyFirstReply(){
        setUpReplyUseCase();

        UserResponseModel userResponseModel = new UserResponseModel(ATTORNEY_ID, ATTORNEY_USERNAME, ATTORNEY_TYPE);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);

        PostRequestModel inputData2 = new PostRequestModel(QUESTION_ID, "Test text");
        postInputBoundary.createPost(inputData2);
        Question question = questionGateway.get(QUESTION_ID);
        Post post2 = question.getPosts().get(0);
        assertEquals(post2.getBelongsTo(), ATTORNEY_ID);
        assertEquals(post2.getPostText(), "Test text");
        User user = attorneyGateway.get(ATTORNEY_ID);
        Question attorneyQuestion = user.getQuestionsList().get(0);
        assertEquals(attorneyQuestion.getQuestionId(), QUESTION_ID);
        assertEquals(attorneyQuestion.getTakenByAttorney(), ATTORNEY_ID);
        assertTrue(attorneyQuestion.isTaken());
        ClearAllRepository();
    }
    /**
     * Test attorney's follow-up reply to a question.
     */
    @Test
    public void testAttorneyFollowUp(){
        setUpReplyUseCase();
        UserResponseModel userResponseModel = new UserResponseModel(ATTORNEY_ID, ATTORNEY_USERNAME, ATTORNEY_TYPE);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);
        PostRequestModel inputData = new PostRequestModel(QUESTION_ID, "Test text");
        postInputBoundary.createPost(inputData);

        User user = attorneyGateway.get(ATTORNEY_ID);
        Question attorneyquestion = user.getQuestionsList().get(0);
        assertEquals(attorneyquestion.getQuestionId(), QUESTION_ID);
        assertEquals(attorneyquestion.getTakenByAttorney(), ATTORNEY_ID);
        assertTrue(attorneyquestion.isTaken());

        PostRequestModel inputData2 = new PostRequestModel(QUESTION_ID, "Test text2");
        postInputBoundary.createPost(inputData2);
        Question question = questionGateway.get(QUESTION_ID);
        Post post2 = question.getPosts().get(1);
        assertEquals(post2.getBelongsTo(), ATTORNEY_ID);
        assertEquals(post2.getPostText(), "Test text2");
        assertEquals(attorneyquestion.getQuestionId(), QUESTION_ID);
        assertEquals(attorneyquestion.getTakenByAttorney(), ATTORNEY_ID);
        assertTrue(attorneyquestion.isTaken());
        ClearAllRepository();

    }

    /**
     * Test failure to reply to a closed question.
     */
    @Test
    public void testFailToReplyQuestionClosed(){
        setUpReplyUseCase();
        UserResponseModel userResponseModel = new UserResponseModel(ATTORNEY_ID, ATTORNEY_USERNAME, ATTORNEY_TYPE);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);
        PostRequestModel inputData = new PostRequestModel(CLOSED_QUESTION_ID, "Test text");
        postInputBoundary.createPost(inputData);
        ClearAllRepository();
    }

    /**
     * Test failure to reply to a question that was taken by other attorney.
     */
    @Test
    public void testAttorneyFailToReplyQuestionTakenByOther(){
        setUpReplyUseCase();
        UserResponseModel userResponseModel = new UserResponseModel(ATTORNEY_ID, ATTORNEY_USERNAME, ATTORNEY_TYPE);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);
        PostRequestModel inputData = new PostRequestModel(QUESTION_ID, "Test text");
        postInputBoundary.createPost(inputData);

        UserResponseModel userResponseModel2 = new UserResponseModel(SECOND_ATTORNEY_ID, ATTORNEY_USERNAME, "attorney");
        UserSession session2 = new UserSession(userResponseModel2);
        SessionManager.setSession(session2);
        PostRequestModel inputData2 = new PostRequestModel(QUESTION_ID, "Test text");
        postInputBoundary.createPost(inputData2);

        User user = attorneyGateway.get(ATTORNEY_ID);
        Question attorneyquestion = user.getQuestionsList().get(0);
        assertEquals(attorneyquestion.getQuestionId(), QUESTION_ID);
        assertEquals(attorneyquestion.getTakenByAttorney(), ATTORNEY_ID);
        assertTrue(attorneyquestion.isTaken());
        ClearAllRepository();
    }

    /**
     * Delete all data in clientGateway, questionGateway, attorneyGateway, postGateway.
     */
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
