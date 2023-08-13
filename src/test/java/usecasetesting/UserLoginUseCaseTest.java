package usecasetesting;

import adapter.controller.ControlContainer;
import businessrule.SessionManager;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.UserLoginInputBoundary;

import businessrule.outputboundary.UserOutputBoundary;
import businessrule.requestmodel.UserLoginRequestModel;

import businessrule.responsemodel.UserResponseModel;
import businessrule.usecase.UserLoginInteractor;
import driver.database.*;
import entity.Client;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserLoginUseCaseTest {
    final static int CLIENT_ID = 21345678;
    final static String PASSWORD = "abcdefg";
    private UserGatewayFactory userGatewayFactory;
    private ClientGateway clientGateway;
    private UserOutputBoundary userOutputBoundary;
    private UserLoginInputBoundary userLoginInputBoundary;

    public void setUpUserLoginUseCase(){
        userGatewayFactory = new UserGatewayFactory();
        clientGateway = new ClientRepository();

        userOutputBoundary = new UserOutputBoundary() {
            @Override
            public void setControlContainer(ControlContainer controlContainer) {

            }

            @Override
            public UserResponseModel prepareFail(String msg) {
                System.out.println(msg);
                return null;
            }

            @Override
            public UserResponseModel prepareSuccess(UserResponseModel userResponseModel) {
                assertNotEquals(null, SessionManager.getSession());
                return null;
            }
        };
        userLoginInputBoundary = new UserLoginInteractor(userGatewayFactory, userOutputBoundary);

        Client client = new Client();
        client.setUserId(CLIENT_ID);
        client.setPassword(PASSWORD);
        clientGateway.save(client);
    }
    @Test
    public void TestLoginPass(){
        setUpUserLoginUseCase();
        UserLoginRequestModel inputData = new UserLoginRequestModel(CLIENT_ID, PASSWORD);
        userLoginInputBoundary.login(inputData);
        ClearAllRepository();
    }
    @Test
    public void TestLoginFailIdDNE(){
        setUpUserLoginUseCase();
        UserLoginRequestModel inputData = new UserLoginRequestModel(1, PASSWORD);
        userLoginInputBoundary.login(inputData);
        ClearAllRepository();
    }
    @Test
    public void TestLoginFailWrongPassword(){
        setUpUserLoginUseCase();
        UserLoginRequestModel inputData = new UserLoginRequestModel(CLIENT_ID, "a");
        userLoginInputBoundary.login(inputData);
        ClearAllRepository();
    }

    public void ClearAllRepository(){
        clientGateway = new ClientRepository();
        clientGateway.deleteAll();
    }
}
