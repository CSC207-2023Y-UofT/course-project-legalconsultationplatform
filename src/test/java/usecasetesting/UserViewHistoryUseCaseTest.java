package usecasetesting;

import adapter.controller.ControlContainer;
import businessrule.SessionManager;
import businessrule.UserSession;
import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.QuestionGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.responsemodel.UserResponseModel;
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
    private UserGatewayFactory userGatewayFactory;
    private ViewOutputBoundary viewOutputBoundary;
    private ViewInputBoundary viewInputBoundary;

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
            public UserResponseModel prepareFail(String msg) {
                return null;
            }

            @Override
            public UserResponseModel prepareSuccess(UserResponseModel response) {
                ViewResponseModel responseModel = (ViewResponseModel) response;
                assertEquals(1, responseModel.getQuestionMap().size(), "The Question Map is not correct.");
                List<QuestionDisplayFormatter> arrayList;
                arrayList = new ArrayList<>(responseModel.getQuestionMap().values());
                assertEquals("test title", arrayList.get(0).getTitle());
                return null;
            }
        };

        viewInputBoundary = new ViewQuestionInteractor( viewOutputBoundary, questionGateway, userGatewayFactory);

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

    @Test
    public void TestClientViewUseCase(){
        setUpViewQuestionUseCase();
        UserResponseModel userResponseModel = new UserResponseModel(CLIENT_ID, CLIENT_USERNAME, CLIENT_TYPE);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);

        viewInputBoundary.viewQuestion();
        ClearAllRepository();
    }

    @Test
    public void TestAttorneyViewUseCase(){
        setUpViewQuestionUseCase();
        UserResponseModel userResponseModel = new UserResponseModel(ATTORNEY_ID, ATTORNEY_USERNAME, ATTORNEY_TYPE);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);

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