package usecasetesting;

import adapter.controller.ControlContainer;
import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.QuestionGateway;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.outputboundary.ViewOutputBoundary;

import businessrule.responsemodel.UserResponseModel;
import businessrule.responsemodel.ViewResponseModel;
import businessrule.usecase.BrowseQuestionInteractor;

import businessrule.usecase.ViewQuestionInteractorBase;
import driver.database.*;

import entity.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
;

public class BrowseQuestionUseCaseTest {
    final static int CLIENT_ID = 21345678;
    final static int ATTORNEY_ID = 11345678;
    final static int SECOND_ATTORNEY_ID = 12222222;
    final static int QUESTION_ID = 323456789;
    final static int TAKEN_QUESTION_ID = 333333333;
    final static int CLOSED_QUESTION_ID = 322222222;
    final static int POST_ID = 455555555;
    private QuestionGateway questionGateway;
    private ClientGateway clientGateway;
    private AttorneyGateway attorneyGateway;
    private TheQuestionOutputBoundary theQuestionOutputBoundary = new TheQuestionOutputBoundary() {
        @Override
        public void setControlContainer(ControlContainer controlContainer) {

        }

        @Override
        public UserResponseModel prepareFail(String msg) {
            return null;
        }

        @Override
        public UserResponseModel prepareSuccess(UserResponseModel response) {
            return null;
        }

    };
    private ViewInputBoundary browseInputBoundary;

    public void setUpBrowseUseCase(){
        questionGateway = new QuestionRepo();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        browseInputBoundary = new BrowseQuestionInteractor(theQuestionOutputBoundary, questionGateway, attorneyGateway);

        Client client = new Client();
        client.setUserId(CLIENT_ID);
        clientGateway.save(client);

        Attorney attorney = new Attorney();
        attorney.setUserId(ATTORNEY_ID);
        attorneyGateway.save(attorney);

        Attorney secondAttorney = new Attorney();
        attorney.setUserId(SECOND_ATTORNEY_ID);
        attorneyGateway.save(secondAttorney);

        Question question1 = new Question();
        question1.setQuestionId(QUESTION_ID);
        question1.setTitle("test title");
        Post post = new Post();
        post.setPostId(POST_ID);
        question1.addPosts(post);
        questionGateway.save(question1);

        Question question2 = new Question();
        question2.setQuestionId(TAKEN_QUESTION_ID);
        question2.setTaken(true);
        question2.setTakenByAttorney(ATTORNEY_ID);
        questionGateway.save(question2);

        Question question3 = new Question();
        question3.setQuestionId(CLOSED_QUESTION_ID);
        question3.setClose(true);
        questionGateway.save(question3);
    }

    @Test
    public void TestAttorneyBrowseQuestionUseCase(){
        setUpBrowseUseCase();
        browseInputBoundary.viewQuestion();

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