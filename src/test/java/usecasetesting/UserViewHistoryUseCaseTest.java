package usecasetesting;

import adapter.controller.ControlContainer;
import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.QuestionGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import businessrule.responsemodel.ViewResponseModel;
import businessrule.usecase.util.QuestionDisplayFormatter;
import businessrule.usecase.ViewQuestionInteractor;
import driver.database.AttorneyRepository;
import driver.database.ClientRepository;
import driver.database.QuestionRepo;
import entity.Attorney;
import entity.Client;
import entity.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains test cases for the ViewHistoryUseCase.
 */
public class UserViewHistoryUseCaseTest {
    final static int CLIENT_ID = 21345678;
    final static int ATTORNEY_ID = 11345678;
    final static int QUESTION_ID = 323456789;
    private QuestionGateway questionGateway;
    private ClientGateway clientGateway;
    private AttorneyGateway attorneyGateway;
    private UserGatewayFactory userGatewayFactory;
    private ViewOutputBoundary viewOutputBoundary;
    private ViewInputBoundary viewInputBoundary;

    /**
     * Set up the test environment by initializing the ViewQuestionUseCase instance.
     */
    public void setUpViewQuestionUseCase(){
        userGatewayFactory = new UserGatewayFactory();
        questionGateway = new QuestionRepo();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();

        viewOutputBoundary = new ViewOutputBoundary() {
            @Override
            public void setControlContainer(ControlContainer controlContainer) {

            }

            @Override
            public ViewResponseModel prepareFail(String msg) {
                return null;
            }

            @Override
            public ViewResponseModel prepareSuccess(ViewResponseModel response) {
                assertEquals(1, response.getQuestionMap().size(), "The Question Map is not correct.");
                List<QuestionDisplayFormatter> arrayList;
                arrayList = new ArrayList<>(response.getQuestionMap().values());
                assertEquals("test title", arrayList.get(0).getTitle());
                return null;
            }
        };

        viewInputBoundary = new ViewQuestionInteractor(questionGateway, viewOutputBoundary, userGatewayFactory);

        Question question = new Question();
        question.setQuestionId(QUESTION_ID);
        question.setTitle("test title");
        questionGateway.save(question);

        Client client = new Client();
        client.setUserId(CLIENT_ID);
        client.addQuestion(question);
        clientGateway.save(client);

        Attorney attorney = new Attorney();
        attorney.setUserId(ATTORNEY_ID);
        attorney.addQuestion(question);
        attorneyGateway.save(attorney);
    }

    /**
     * Test the client's view question use case.
     */
    @Test
    public void TestClientViewUseCase(){
        setUpViewQuestionUseCase();
        ViewRequestModel inputData = new ViewRequestModel(CLIENT_ID);

        viewInputBoundary.viewQuestion(inputData);
        ClearAllRepository();
    }

    /**
     * Test the attorney's view question use case.
     */
    @Test
    public void TestAttorneyViewUseCase(){
        setUpViewQuestionUseCase();
        ViewRequestModel inputData = new ViewRequestModel(ATTORNEY_ID);

        viewInputBoundary.viewQuestion(inputData);
        ClearAllRepository();
    }

    /**
     * Delete all data in clientGateway, questionGateway, attorneyGateway.
     */
    public void ClearAllRepository(){
        questionGateway = new QuestionRepo();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        clientGateway.deleteAll();
        questionGateway.deleteAll();
        attorneyGateway.deleteAll();
    }
}