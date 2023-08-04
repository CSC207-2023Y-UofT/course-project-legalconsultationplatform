package usecasetesting;

import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import businessrule.responsemodel.ViewResponseModel;
import businessrule.usecase.ViewQuestionInteractor;
import driver.database.AttorneyRepository;
import driver.database.ClientRepository;
import driver.database.QuestionGateway;
import driver.database.QuestionRepo;
import entity.Attorney;
import entity.Client;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserViewHistoryUseCaseTest {
    final static int CLIENT_ID = 21345678;
    final static int ATTORNEY_ID = 11345678;
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
        clientGateway.deleteAllUser();
        questionGateway.deleteAllQuestion();
        attorneyGateway.deleteAllUser();

        viewOutputBoundary = new ViewOutputBoundary() {
            @Override
            public ViewResponseModel prepareFail(String msg) {
                return null;
            }

            @Override
            public ViewResponseModel prepareSuccess(ViewResponseModel response) {
                assertEquals(0, response.getQuestionMap().size(), "The Question map is not correct." );
                return null;
            }
        };

        viewInputBoundary = new ViewQuestionInteractor(questionGateway, viewOutputBoundary, userGatewayFactory);

        Client client = new Client();
        client.setUserId(CLIENT_ID);
        clientGateway.addUser(client);

        Attorney attorney = new Attorney();
        attorney.setUserId(ATTORNEY_ID);
        attorneyGateway.addUser(attorney);
    }

    @Test
    public void TestClientViewUseCase(){
        setUpViewQuestionUseCase();
        ViewRequestModel inputData = new ViewRequestModel(CLIENT_ID);

        viewInputBoundary.viewQuestion(inputData);// null pointer caused by getUser, it cant get the question list out
    }

    @Test
    public void TestAttorneyViewUseCase(){
        setUpViewQuestionUseCase();
        ViewRequestModel inputData = new ViewRequestModel(ATTORNEY_ID);

        viewInputBoundary.viewQuestion(inputData);
    }
}