package usecasetesting;

import usecases.session.SessionManager;
import usecases.gateway.ClientGateway;
import infrastructure.database.UserGatewayFactory;
import usecases.inputboundary.UserLoginInputBoundary;
import usecases.outputboundary.UserOutputBoundary;
import usecases.requests.UserLoginRequestModel;
import usecases.responses.UserResponseModel;
import usecases.interactors.UserLoginInteractor;
import infrastructure.database.*;
import entities.user.Client;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains test cases for the UserLoginUseCase.
 */

public class UserLoginUseCaseTest {
    final static int CLIENT_ID = 21345678;
    final static String PASSWORD = "abcdefg";
    private ClientGateway clientGateway;
    private UserLoginInputBoundary userLoginInputBoundary;

    /**
     * Set up the test environment by initializing the UserLoginUseCase instance.
     */
    public void setUpUserLoginUseCase(){
        UserGatewayFactory userGatewayFactory = new UserGatewayFactory();
        clientGateway = new ClientRepository();

        UserOutputBoundary userOutputBoundary = new UserOutputBoundary() {

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
    /**
     * Test a successful user login scenario.
     */
    @Test
    public void TestLoginPass(){
        setUpUserLoginUseCase();
        UserLoginRequestModel inputData = new UserLoginRequestModel(CLIENT_ID, PASSWORD);
        userLoginInputBoundary.login(inputData);
        ClearAllRepository();
    }
    /**
     * Test user login fails because the provided ID does not exist.
     */
    @Test
    public void TestLoginFailIdDNE(){
        setUpUserLoginUseCase();
        UserLoginRequestModel inputData = new UserLoginRequestModel(1, PASSWORD);
        userLoginInputBoundary.login(inputData);
        ClearAllRepository();
    }
    /**
     * Test the scenario where user login fails because the provided password is incorrect.
     */
    @Test
    public void TestLoginFailWrongPassword(){
        setUpUserLoginUseCase();
        UserLoginRequestModel inputData = new UserLoginRequestModel(CLIENT_ID, "a");
        userLoginInputBoundary.login(inputData);
        ClearAllRepository();
    }

    /**
     * Delete all data in clientGateway.
     */
    public void ClearAllRepository(){
        clientGateway = new ClientRepository();
        clientGateway.deleteAll();
    }
}
