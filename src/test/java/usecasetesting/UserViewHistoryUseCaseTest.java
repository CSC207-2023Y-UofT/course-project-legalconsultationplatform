package usecasetesting;

import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import businessrule.responsemodel.ViewResponseModel;
import businessrule.usecase.ViewQuestionInteractor;
import driver.database.QuestionGateway;
import driver.database.QuestionRepo;
import entity.Attorney;
import entity.Client;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

public class UserViewHistoryUseCaseTest {
    final static int CLIENT_ID = 11345678;
    final static int ATTORNEY_ID = 21345678;
    private QuestionGateway questionGateway;
    private ClientGateway clientGateway;
    private AttorneyGateway attorneyGateway;
    private UserGatewayFactory userGatewayFactory;
    private ViewOutputBoundary viewOutputBoundary;
    private ViewInputBoundary viewInputBoundary;
    @BeforeClass
    public void setUpViewQuestionUseCase(){
        userGatewayFactory = new UserGatewayFactory();
        questionGateway = new QuestionRepo();
        viewOutputBoundary = new ViewOutputBoundary() {
            @Override
            public ViewResponseModel prepareFail(String msg) {
                return null;
            }

            @Override
            public ViewResponseModel prepareSuccess(ViewResponseModel response) {
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
        ViewRequestModel inputData = new ViewRequestModel(CLIENT_ID);

        viewInputBoundary.viewQuestion(inputData);
    }

    @Test
    public void TestAttorneyViewUseCase(){
        ViewRequestModel inputData = new ViewRequestModel(ATTORNEY_ID);

        viewInputBoundary.viewQuestion(inputData);
    }
}