package usecasetesting;

import usecases.session.SessionManager;
import usecases.session.UserSession;
import usecases.gateway.AttorneyGateway;
import usecases.gateway.ClientGateway;
import usecases.gateway.QuestionGateway;
import infrastructure.database.UserGatewayFactory;
import usecases.inputboundary.ViewInputBoundary;
import usecases.outputboundary.ViewOutputBoundary;
import usecases.responses.UserResponseModel;
import usecases.responses.ViewResponseModel;
import usecases.dto.QuestionDisplay;
import usecases.interactors.ViewQuestionInteractor;
import infrastructure.database.AttorneyRepository;
import infrastructure.database.ClientRepository;
import infrastructure.database.QuestionRepo;
import entities.user.Attorney;
import entities.user.Client;
import entities.Question;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains test cases for the ViewHistoryUseCase.
 */
public class UserViewHistoryUseCaseTest {
    final static int CLIENT_ID = 21345678;
    final static String CLIENT_USERNAME = "test client";
    final static String CLIENT_TYPE = "Client";
    final static int ATTORNEY_ID = 11345678;
    final static String ATTORNEY_USERNAME = "test attorney";
    final static String ATTORNEY_TYPE = "Attorney";
    final static int QUESTION_ID = 323456789;
    private QuestionGateway questionGateway;
    private ClientGateway clientGateway;
    private AttorneyGateway attorneyGateway;
    private ViewInputBoundary viewInputBoundary;

    /**
     * Set up the test environment by initializing the ViewQuestionUseCase instance.
     */
    public void setUpViewQuestionUseCase(){
        UserGatewayFactory userGatewayFactory = new UserGatewayFactory();
        questionGateway = new QuestionRepo();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();

        ViewOutputBoundary viewOutputBoundary = new ViewOutputBoundary() {

            @Override
            public ViewResponseModel prepareFail(String msg) {
                return null;
            }

            @Override
            public ViewResponseModel prepareSuccess(ViewResponseModel response) {
                assertEquals(1, response.getQuestionMap().size(), "The Question Map is not correct.");
                List<QuestionDisplay> arrayList;
                arrayList = new ArrayList<>(response.getQuestionMap().values());
                assertEquals("test title", arrayList.get(0).getTitle());
                return null;
            }
        };

        viewInputBoundary = new ViewQuestionInteractor(viewOutputBoundary, questionGateway, userGatewayFactory);

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
        ViewResponseModel userResponseModel = new ViewResponseModel(CLIENT_ID, CLIENT_USERNAME, CLIENT_TYPE, null);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);

        viewInputBoundary.viewQuestion();
        ClearAllRepository();
    }

    /**
     * Test the attorney's view question use case.
     */
    @Test
    public void TestAttorneyViewUseCase(){
        setUpViewQuestionUseCase();
        ViewResponseModel userResponseModel = new ViewResponseModel(ATTORNEY_ID, ATTORNEY_USERNAME, ATTORNEY_TYPE, null);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);

        viewInputBoundary.viewQuestion();
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