package usecasetesting;

import businessrule.gateway.ClientGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.ClientRegisterInputBoundary;
import businessrule.inputboundary.UserLoginInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.outputboundary.RegisterOutputBoundary;
import businessrule.requestmodel.ClientRegisterRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.responsemodel.RegisterResponseModel;
import businessrule.usecase.ClientRegisterInteractor;
import businessrule.usecase.UserLoginInteractor;
import driver.database.ClientRepository;
import entity.Client;
import entity.ClientFactory;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.time.LocalDate;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
;

public class ClientRegisterUseCaseTest {
    final static String PASSWORD = "abcdefg";
    private ClientFactory clientFactory;
    private ClientGateway clientGateway;
    private RegisterOutputBoundary registerOutputBoundary;
    private ClientRegisterInputBoundary clientRegisterInputBoundary;
    @BeforeClass
    public void setUpReplyUseCase(){
        clientGateway = new ClientRepository();
        clientFactory = new ClientFactory();
        registerOutputBoundary = new RegisterOutputBoundary() {
            @Override
            public RegisterResponseModel prepareSuccess(String msg) {
                System.out.println(msg);
                System.out.println("Registration is approved.");
                return null;
            }

            @Override
            public RegisterResponseModel prepareFail(String msg) {
                System.out.println(msg);
                return null;
            }
        };

        clientRegisterInputBoundary = new ClientRegisterInteractor(clientGateway, registerOutputBoundary, clientFactory);

    }
    @Test
    public void TestSuccessfulRegistration(){

        ClientRegisterRequestModel inputData = new ClientRegisterRequestModel("joseph", "test@gmail.com", PASSWORD, PASSWORD, "test state", "M5A 1R1", "test ethinicity", 18, "male", "single", 1, 1000.0F);

        clientRegisterInputBoundary.create(inputData);

    }

    @Test
    public void TestRegistrationFailByAlreadyExists(){

        ClientRegisterRequestModel inputData = new ClientRegisterRequestModel("joseph", "test@gmail.com", PASSWORD, PASSWORD, "test state", "M5A 1R1", "test ethinicity", 18, "male", "single", 1, 1000.0F);

        clientRegisterInputBoundary.create(inputData);

    }

    @Test
    public void TestRegistrationFailByPasswordDoesNotMatch(){

        ClientRegisterRequestModel inputData = new ClientRegisterRequestModel("joseph", "test@gmail.com", PASSWORD, "a", "test state", "M5A 1R1", "test ethinicity", 18, "male", "single", 1, 1000.0F);

        clientRegisterInputBoundary.create(inputData);

    }
    @Test
    public void TestRegistrationFailByPasswordLengthTooSmall(){

        ClientRegisterRequestModel inputData = new ClientRegisterRequestModel("joseph", "test@gmail.com", "a", "a", "test state", "M5A 1R1", "test ethinicity", 18, "male", "single", 1, 1000.0F);

        clientRegisterInputBoundary.create(inputData);

    }

    @Test
    public void TestRegistrationFailByInvalidEmail(){

        ClientRegisterRequestModel inputData = new ClientRegisterRequestModel("joseph", "invalid email", PASSWORD, PASSWORD, "test state", "M5A 1R1", "test ethinicity", 18, "male", "single", 1, 1000.0F);

        clientRegisterInputBoundary.create(inputData);

    }

    @Test
    public void TestRegistrationFailByInvalidAge(){

        ClientRegisterRequestModel inputData = new ClientRegisterRequestModel("joseph", "test@gmail.com", PASSWORD, PASSWORD, "test state", "M5A 1R1", "test ethinicity", 222, "male", "single", 1, 1000.0F);

        clientRegisterInputBoundary.create(inputData);

    }

    @Test
    public void TestRegistrationFailByInvalidPostalCode(){

        ClientRegisterRequestModel inputData = new ClientRegisterRequestModel("joseph", "test@gmail.com", PASSWORD, PASSWORD, "test state", "abcdefg", "test ethinicity", 18, "male", "single", 1, 1000.0F);

        clientRegisterInputBoundary.create(inputData);

    }
}
