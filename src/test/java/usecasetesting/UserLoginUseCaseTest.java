package usecasetesting;

import businessrule.gateway.ClientGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.UserLoginInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.UserLoginRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.usecase.UserLoginInteractor;
import driver.database.*;
import entity.Client;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserLoginUseCaseTest {
    final static int CLIENT_ID = 11345678;
    final static String PASSWORD = "abcdefg";
    private UserGatewayFactory userGatewayFactory;
    private ClientGateway clientGateway;
    private HomePageOutputBoundary homePageOutputBoundary;
    private UserLoginInputBoundary userLoginInputBoundary;
    @BeforeClass
    public void setUpReplyUseCase(){
        userGatewayFactory = new UserGatewayFactory();
        clientGateway = new ClientRepository();
        homePageOutputBoundary = new HomePageOutputBoundary() {
            @Override
            public HomePageResponseModel prepareFail(String msg) {
                assertEquals("You cannot rate this question!", msg);
                return null;
            }

            @Override
            public HomePageResponseModel prepareSuccess(HomePageResponseModel homePageResponseModel) {
                return null;
            }
        };
        userLoginInputBoundary = new UserLoginInteractor(userGatewayFactory, homePageOutputBoundary);

        Client client = new Client();
        client.setUserId(CLIENT_ID);
        client.setPassword(PASSWORD);
        clientGateway.addUser(client);
    }
    @Test
    public void TestLoginPass(){;
        UserLoginRequestModel inputData = new UserLoginRequestModel(CLIENT_ID, PASSWORD);
        userLoginInputBoundary.login(inputData);
    }
    @Test
    public void TestLoginFailIdDNE(){;
        UserLoginRequestModel inputData = new UserLoginRequestModel(1, PASSWORD);
        userLoginInputBoundary.login(inputData);
    }
    @Test
    public void TestLoginFailWrongPassword(){;
        UserLoginRequestModel inputData = new UserLoginRequestModel(CLIENT_ID, "a");
        userLoginInputBoundary.login(inputData);
    }
}
