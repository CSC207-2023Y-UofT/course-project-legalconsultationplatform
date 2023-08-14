package usecasetesting;

import adapter.controller.ControlContainer;
import businessrule.SessionManager;
import businessrule.UserSession;
import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.QuestionGateway;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.outputboundary.ViewOutputBoundary;

import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.responsemodel.UserResponseModel;
import businessrule.responsemodel.ViewResponseModel;
import businessrule.usecase.BrowseQuestionInteractor;
import driver.database.*;

import entity.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
;

public class BrowseQuestionUseCaseTest {
    final static int CLIENT_ID = 21345678;
    final static String CLIENT_USERNAME = "test client";
    final static int ATTORNEY_ID = 11345678;
    final static String ATTORNEY_USERNAME = "test attorney";
    final static String ATTORNEY_TYPE = "Attorney";
    final static int SECOND_ATTORNEY_ID = 12222222;
    final static int QUESTION_ID = 323456789;
    final static int TAKEN_QUESTION_ID = 333333333;
    final static int CLOSED_QUESTION_ID = 322222222;
    final static int POST_ID = 455555555;
    private QuestionGateway questionGateway;
    private ClientGateway clientGateway;
    private AttorneyGateway attorneyGateway;
    private ViewOutputBoundary viewOutputBoundary = new ViewOutputBoundary() {

        @Override
        public ViewResponseModel prepareFail(String msg) {
            return null;
        }

        @Override
        public ViewResponseModel prepareSuccess(ViewResponseModel response) {
            assertEquals(0, response.getQuestionMap().size(), "The Question Map is not correct.");
            return null;
        }

    };
    private ViewInputBoundary viewInputBoundary;

    public void setUpBrowseUseCase(){
        questionGateway = new QuestionRepo();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        viewInputBoundary = new BrowseQuestionInteractor(viewOutputBoundary, questionGateway, attorneyGateway);

        Attorney attorney = new Attorney();
        attorney.setUserId(ATTORNEY_ID);
        attorneyGateway.save(attorney);

        Attorney secondAttorney = new Attorney();
        attorney.setUserId(SECOND_ATTORNEY_ID);
        attorneyGateway.save(secondAttorney);

        Post post = new Post();
        post.setPostId(POST_ID);

        Question question2 = new Question();
        question2.setQuestionId(TAKEN_QUESTION_ID);
        question2.setTaken(true);
        question2.setTakenByAttorney(ATTORNEY_ID);
        question2.setAskedByClient(CLIENT_ID);
        question2.addPosts(post);
        questionGateway.save(question2);

        Client client = new Client();
        client.setUserId(CLIENT_ID);
        client.addQuestion(question2);
        clientGateway.save(client);

        UserResponseModel userResponseModel = new UserResponseModel(ATTORNEY_ID, ATTORNEY_USERNAME, ATTORNEY_TYPE);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);
    }

    @Test
    public void TestAttorneyBrowseQuestionUseCase(){
        setUpBrowseUseCase();
        viewInputBoundary.viewQuestion();
        ClearAllRepository();
    }

    public void ClearAllRepository(){
        questionGateway = new QuestionRepo();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        clientGateway.deleteAll();
        questionGateway.deleteAll();
        attorneyGateway.deleteAll();
    }
}