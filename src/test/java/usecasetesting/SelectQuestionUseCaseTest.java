package usecasetesting;

import adapter.controller.ControlContainer;
import businessrule.gateway.*;
import businessrule.inputboundary.QuestionInputBoundary;
import businessrule.inputboundary.SelectInputBoundary;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.requestmodel.SelectRequestModel;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.usecase.AskQuestionInteractor;
import businessrule.usecase.PostDisplayFormatter;
import businessrule.usecase.SelectQuestionInteractor;
import driver.database.*;

import entity.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
;

public class SelectQuestionUseCaseTest {
    final static int CLIENT_ID = 21345678;
    final static int ATTORNEY_ID = 11345678;
    final static int SECOND_ATTORNEY_ID = 12222222;
    final static int QUESTION_ID = 323456789;
    final static int TAKEN_QUESTION_ID = 333333333;
    final static int CLOSED_QUESTION_ID = 322222222;
    final static int CLIENT_POST_ID = 455555555;
    final static int ATTORNEY_POST_ID = 433333333;
    private QuestionGateway questionGateway;
    private PostGateway postGateway;
    private ClientGateway clientGateway;
    private UserGatewayFactory userGatewayFactory;
    private AttorneyGateway attorneyGateway;
    private TheQuestionOutputBoundary theQuestionOutputBoundary;
    private SelectInputBoundary selectInputBoundary;

    public void setUpSelectUseCase(){
        questionGateway = new QuestionRepo();
        userGatewayFactory = new UserGatewayFactory();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        postGateway = new PostRepo();
        theQuestionOutputBoundary = new TheQuestionOutputBoundary() {
            @Override
            public void setControlContainer(ControlContainer controlContainer) {
            }

            @Override
            public TheQuestionResponseModel prepareFail(String msg) {
                assertEquals("This question is not accessible.", msg);
                return null;
            }

            @Override
            public TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response) {
                assertEquals(2, response.getPostMap().size(), "The post map is not correct.");
                List<PostDisplayFormatter> arrayList;
                arrayList = new ArrayList<>(response.getPostMap().values());
                assertEquals("test text", arrayList.get(0).getPostText());
                return null;
            }
        };
        selectInputBoundary = new SelectQuestionInteractor(questionGateway, theQuestionOutputBoundary, userGatewayFactory);

        Client client = new Client();
        client.setUserId(CLIENT_ID);
        clientGateway.addUser(client);

        Attorney attorney = new Attorney();
        attorney.setUserId(ATTORNEY_ID);
        attorneyGateway.addUser(attorney);

        Attorney secondAttorney = new Attorney();
        secondAttorney.setUserId(SECOND_ATTORNEY_ID);
        attorneyGateway.addUser(secondAttorney);

        Post postBelongsToClient = new Post();
        postBelongsToClient.setPostText("test text");
        postBelongsToClient.setPostId(CLIENT_POST_ID);
        postBelongsToClient.setBelongsTo(CLIENT_ID);
        postGateway.savePost(postBelongsToClient);

        Post postBelongsToAttorney = new Post();
        postBelongsToAttorney.setPostText("test text");
        postBelongsToAttorney.setPostId(ATTORNEY_POST_ID);
        postBelongsToAttorney.setBelongsTo(ATTORNEY_ID);
        postGateway.savePost(postBelongsToAttorney);

        Question question1 = new Question();
        question1.setQuestionId(QUESTION_ID);
        question1.addPosts(postBelongsToClient);
        question1.addPosts(postBelongsToAttorney);
        questionGateway.saveQuestion(question1);

        Question question2 = new Question();
        question2.setQuestionId(TAKEN_QUESTION_ID);
        question2.setTaken(true);
        question2.setTakenByAttorney(ATTORNEY_ID);
        question2.addPosts(postBelongsToClient);
        question2.addPosts(postBelongsToAttorney);
        questionGateway.saveQuestion(question2);

        Question question3 = new Question();
        question3.setQuestionId(CLOSED_QUESTION_ID);
        question3.setClose(true);
        questionGateway.saveQuestion(question3);
    }

    @Test
    public void TestClientSelectQuestionUseCase(){
        setUpSelectUseCase();

        SelectRequestModel inputData = new SelectRequestModel(QUESTION_ID, CLIENT_ID);

        selectInputBoundary.selectQuestion(inputData);
        ClearAllRepository();
    }

    @Test
    public void TestAttorneySelectNonTakenQuestionUseCase(){
        setUpSelectUseCase();
        SelectRequestModel inputData = new SelectRequestModel(QUESTION_ID, ATTORNEY_ID);

        selectInputBoundary.selectQuestion(inputData);
        ClearAllRepository();
    }

    @Test
    public void TestAttorneySelectQuestionTakenByHimselfUseCase(){
        setUpSelectUseCase();
        SelectRequestModel inputData = new SelectRequestModel(TAKEN_QUESTION_ID, ATTORNEY_ID);

        selectInputBoundary.selectQuestion(inputData);
        ClearAllRepository();
    }

    @Test
    public void TestAttorneySelectQuestionFailByClosedQuestion(){
        setUpSelectUseCase();
        SelectRequestModel inputData = new SelectRequestModel(CLOSED_QUESTION_ID, ATTORNEY_ID);

        selectInputBoundary.selectQuestion(inputData);
        ClearAllRepository();
    }

    @Test
    public void TestAttorneySelectQuestionFailByTakenByOther(){
        setUpSelectUseCase();
        SelectRequestModel inputData = new SelectRequestModel(TAKEN_QUESTION_ID, SECOND_ATTORNEY_ID);

        selectInputBoundary.selectQuestion(inputData);
        ClearAllRepository();
    }

    public void ClearAllRepository(){
        questionGateway = new QuestionRepo();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        postGateway = new PostRepo();
        clientGateway.deleteAllUser();
        questionGateway.deleteAllQuestion();
        attorneyGateway.deleteAllUser();
        postGateway.deleteAllPost();
    }
}