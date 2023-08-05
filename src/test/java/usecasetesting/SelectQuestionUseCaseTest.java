package usecasetesting;

import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.QuestionInputBoundary;
import businessrule.inputboundary.SelectInputBoundary;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.requestmodel.SelectRequestModel;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.usecase.AskQuestionInteractor;
import businessrule.usecase.SelectQuestionInteractor;
import driver.database.AttorneyRepository;
import driver.database.ClientRepository;
import driver.database.QuestionGateway;
import driver.database.QuestionRepo;

import entity.Attorney;
import entity.Client;
import entity.Question;

import entity.QuestionFactory;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
;

public class SelectQuestionUseCaseTest {
    final static int CLIENT_ID = 11345678;
    final static int ATTORNEY_ID = 21345678;
    final static int SECOND_ATTORNEY_ID = 22222222;
    final static int QUESTION_ID = 323456789;
    final static int TAKEN_QUESTION_ID = 333333333;
    final static int CLOSED_QUESTION_ID = 322222222;
    private QuestionGateway questionGateway;
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
        theQuestionOutputBoundary = new TheQuestionOutputBoundary() {
            @Override
            public TheQuestionResponseModel prepareFail(String msg) {
                assertEquals("This question is not accessible.", msg);
                return null;
            }

            @Override
            public TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response) {
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
        attorney.setUserId(SECOND_ATTORNEY_ID);
        attorneyGateway.addUser(secondAttorney);

        Question question1 = new Question();
        question1.setQuestionId(QUESTION_ID);
        questionGateway.saveQuestion(question1);

        Question question2 = new Question();
        question2.setQuestionId(TAKEN_QUESTION_ID);
        question2.setTaken(true);
        question2.setTakenByAttorney(ATTORNEY_ID);
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
    }

    @Test
    public void TestAttorneySelectNonTakenQuestionUseCase(){
        setUpSelectUseCase();
        SelectRequestModel inputData = new SelectRequestModel(QUESTION_ID, ATTORNEY_ID);

        selectInputBoundary.selectQuestion(inputData);
    }

    @Test
    public void TestAttorneySelectQuestionTakenByHimselfUseCase(){
        setUpSelectUseCase();
        SelectRequestModel inputData = new SelectRequestModel(TAKEN_QUESTION_ID, ATTORNEY_ID);

        selectInputBoundary.selectQuestion(inputData);
    }

    @Test
    public void TestAttorneySelectQuestionFailByClosedQuestion(){
        setUpSelectUseCase();
        SelectRequestModel inputData = new SelectRequestModel(CLOSED_QUESTION_ID, ATTORNEY_ID);

        selectInputBoundary.selectQuestion(inputData);
    }

    @Test
    public void TestAttorneySelectQuestionFailByTakenByOther(){
        setUpSelectUseCase();
        SelectRequestModel inputData = new SelectRequestModel(TAKEN_QUESTION_ID, SECOND_ATTORNEY_ID);

        selectInputBoundary.selectQuestion(inputData);
    }


}