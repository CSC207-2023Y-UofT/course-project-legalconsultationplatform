package usecasetesting;


import entities.user.Attorney;
import entities.user.Client;
import usecases.responses.ViewResponseModel;
import usecases.session.SessionManager;
import usecases.session.UserSession;
import usecases.gateway.*;
import usecases.inputboundary.SelectInputBoundary;
import usecases.outputboundary.TheQuestionOutputBoundary;
import usecases.requests.SelectRequestModel;
import usecases.responses.TheQuestionResponseModel;
import usecases.dto.PostDisplay;
import usecases.interactors.SelectQuestionInteractor;
import infrastructure.database.*;
import entities.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains test cases for the SelectQuestionUseCase.
 */
public class SelectQuestionUseCaseTest {
    final static int CLIENT_ID = 21345678;
    final static String CLIENT_USERNAME = "test client";
    final static String CLIENT_TYPE = "Client";
    final static int ATTORNEY_ID = 11345678;
    final static String ATTORNEY_USERNAME = "test attorney";
    final static String ATTORNEY_TYPE = "Attorney";
    final static int SECOND_ATTORNEY_ID = 12222222;
    final static int QUESTION_ID = 323456789;
    final static int TAKEN_QUESTION_ID = 333333333;
    final static int CLOSED_QUESTION_ID = 322222222;
    final static int CLIENT_POST_ID = 455555555;
    final static int ATTORNEY_POST_ID = 433333333;
    private QuestionGateway questionGateway;
    private PostGateway postGateway;
    private ClientGateway clientGateway;
    private AttorneyGateway attorneyGateway;
    private SelectInputBoundary selectInputBoundary;

    /**
     * Set up the test environment by initializing the SelectQuestionUseCase instance.
     */
    public void setUpSelectUseCase(){
        questionGateway = new QuestionRepo();
        UserGatewayFactory userGatewayFactory = new UserGatewayFactory();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        postGateway = new PostRepo();
        TheQuestionOutputBoundary theQuestionOutputBoundary = new TheQuestionOutputBoundary() {

            @Override
            public TheQuestionResponseModel prepareFail(String msg) {
                assertEquals("This question is not accessible.", msg);
                return null;
            }

            @Override
            public TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response) {
                assertEquals(2, response.getPostMap().size(), "The post map is not correct.");
                List<PostDisplay> arrayList;
                arrayList = new ArrayList<>(response.getPostMap().values());
                assertEquals("test text", arrayList.get(0).getPostText());
                return null;
            }

        };
        selectInputBoundary = new SelectQuestionInteractor(questionGateway, theQuestionOutputBoundary, userGatewayFactory);

        Client client = new Client();
        client.setUserId(CLIENT_ID);
        clientGateway.save(client);

        Attorney attorney = new Attorney();
        attorney.setUserId(ATTORNEY_ID);
        attorneyGateway.save(attorney);

        Attorney secondAttorney = new Attorney();
        secondAttorney.setUserId(SECOND_ATTORNEY_ID);
        attorneyGateway.save(secondAttorney);

        Post postBelongsToClient = new Post();
        postBelongsToClient.setPostText("test text");
        postBelongsToClient.setPostId(CLIENT_POST_ID);
        postBelongsToClient.setBelongsTo(CLIENT_ID);
        postGateway.save(postBelongsToClient);

        Post postBelongsToAttorney = new Post();
        postBelongsToAttorney.setPostText("test text");
        postBelongsToAttorney.setPostId(ATTORNEY_POST_ID);
        postBelongsToAttorney.setBelongsTo(ATTORNEY_ID);
        postGateway.save(postBelongsToAttorney);

        Question question1 = new Question();
        question1.setQuestionId(QUESTION_ID);
        question1.addPosts(postBelongsToClient);
        question1.addPosts(postBelongsToAttorney);
        questionGateway.save(question1);

        Question question2 = new Question();
        question2.setQuestionId(TAKEN_QUESTION_ID);
        question2.setTaken(true);
        question2.setTakenByAttorney(ATTORNEY_ID);
        question2.addPosts(postBelongsToClient);
        question2.addPosts(postBelongsToAttorney);
        questionGateway.save(question2);

        Question question3 = new Question();
        question3.setQuestionId(CLOSED_QUESTION_ID);
        question3.setClose(true);
        questionGateway.save(question3);
    }

    /**
     * Test the scenario where a client selects a question.
     */
    @Test
    public void TestClientSelectQuestionUseCase(){
        setUpSelectUseCase();

        ViewResponseModel userResponseModel = new ViewResponseModel(CLIENT_ID, CLIENT_USERNAME, CLIENT_TYPE, null);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);
        SelectRequestModel inputData = new SelectRequestModel(QUESTION_ID);
        selectInputBoundary.selectQuestion(inputData);
        ClearAllRepository();
    }

    /**
     * Test the scenario where an attorney selects a non-taken question.
     */
    @Test
    public void TestAttorneySelectNonTakenQuestionUseCase(){
        setUpSelectUseCase();
        ViewResponseModel userResponseModel = new ViewResponseModel(ATTORNEY_ID, ATTORNEY_USERNAME, ATTORNEY_TYPE, null);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);
        SelectRequestModel inputData = new SelectRequestModel(QUESTION_ID);

        selectInputBoundary.selectQuestion(inputData);
        ClearAllRepository();
    }

    /**
     * Test the scenario where an attorney selects a question taken by himself.
     */
    @Test
    public void TestAttorneySelectQuestionTakenByHimselfUseCase(){
        setUpSelectUseCase();
        ViewResponseModel userResponseModel = new ViewResponseModel(ATTORNEY_ID, ATTORNEY_USERNAME, ATTORNEY_TYPE, null);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);
        SelectRequestModel inputData = new SelectRequestModel(TAKEN_QUESTION_ID);
        selectInputBoundary.selectQuestion(inputData);
        ClearAllRepository();
    }

    /**
     * Test the scenario where an attorney's attempt to select a closed question fails.
     */
    @Test
    public void TestAttorneySelectQuestionFailByClosedQuestion(){
        setUpSelectUseCase();
        ViewResponseModel userResponseModel = new ViewResponseModel(ATTORNEY_ID, ATTORNEY_USERNAME, ATTORNEY_TYPE, null);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);
        SelectRequestModel inputData = new SelectRequestModel(CLOSED_QUESTION_ID);
        selectInputBoundary.selectQuestion(inputData);
        ClearAllRepository();
    }

    /**
     * Test the scenario where an attorney's attempt to select a question taken by another attorney fails.
     */
    @Test
    public void TestAttorneySelectQuestionFailByTakenByOther(){
        setUpSelectUseCase();
        ViewResponseModel userResponseModel = new ViewResponseModel(SECOND_ATTORNEY_ID, ATTORNEY_USERNAME, ATTORNEY_TYPE, null);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);
        SelectRequestModel inputData = new SelectRequestModel(TAKEN_QUESTION_ID);

        selectInputBoundary.selectQuestion(inputData);
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