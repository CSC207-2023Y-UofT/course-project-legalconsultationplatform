package usecasetesting;

import adapter.controller.ControlContainer;
import businessrule.gateway.ClientGateway;
import businessrule.inputboundary.ClientRegisterInputBoundary;
import businessrule.outputboundary.RegisterOutputBoundary;
import businessrule.requestmodel.ClientRegisterRequestModel;
import businessrule.responsemodel.RegisterResponseModel;
import businessrule.usecase.ClientRegisterInteractor;
import driver.database.ClientRepository;
import entity.factory.ClientFactory;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.fail;
;

public class ClientRegisterUseCaseTest {
    final static String PASSWORD = "abcdefghi";
    private ClientFactory clientFactory;
    private ClientGateway clientGateway;
    private RegisterOutputBoundary registerOutputBoundary;
    private ClientRegisterInputBoundary clientRegisterInputBoundary;
    public void setUpClientRegisterUseCase(){
        clientGateway = new ClientRepository();
        clientFactory = new ClientFactory();
        registerOutputBoundary = new RegisterOutputBoundary() {
            @Override
            public void setControlContainer(ControlContainer controlContainer) {

            }

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
        setUpClientRegisterUseCase();

        ClientRegisterRequestModel inputData = new ClientRegisterRequestModel("joseph", "test@gmail.com", PASSWORD, PASSWORD, "test state", "21000", "test ethinicity", 18, "male", "single", 1, 1000.0F);

        clientRegisterInputBoundary.create(inputData);
    }

    @Test
    public void TestRegistrationFailByAlreadyExists(){
        setUpClientRegisterUseCase();

        ClientRegisterRequestModel inputData = new ClientRegisterRequestModel("joseph", "test@gmail.com", PASSWORD, PASSWORD, "test state", "21000", "test ethinicity", 18, "male", "single", 1, 1000.0F);

        clientRegisterInputBoundary.create(inputData);

    }

    @Test
    public void TestRegistrationFailByPasswordDoesNotMatch(){
        setUpClientRegisterUseCase();

        ClientRegisterRequestModel inputData = new ClientRegisterRequestModel("joseph", "test@gmail.com", PASSWORD, "a", "test state", "21000", "test ethinicity", 18, "male", "single", 1, 1000.0F);

        clientRegisterInputBoundary.create(inputData);

    }
    @Test
    public void TestRegistrationFailByPasswordLengthTooSmall(){
        setUpClientRegisterUseCase();

        ClientRegisterRequestModel inputData = new ClientRegisterRequestModel("joseph", "test@gmail.com", "a", "a", "test state", "21000", "test ethinicity", 18, "male", "single", 1, 1000.0F);

        clientRegisterInputBoundary.create(inputData);

    }

    @Test
    public void TestRegistrationFailByInvalidEmail(){
        setUpClientRegisterUseCase();

        ClientRegisterRequestModel inputData = new ClientRegisterRequestModel("joseph", "invalid email", PASSWORD, PASSWORD, "test state", "21000", "test ethinicity", 18, "male", "single", 1, 1000.0F);

        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    @Test
    public void TestRegistrationFailByInvalidAge(){
        setUpClientRegisterUseCase();

        ClientRegisterRequestModel inputData = new ClientRegisterRequestModel("joseph", "test@gmail.com", PASSWORD, PASSWORD, "test state", "21000", "test ethinicity", 222, "male", "single", 1, 1000.0F);

        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    @Test
    public void TestRegistrationFailByInvalidPostalCode(){
        setUpClientRegisterUseCase();

        ClientRegisterRequestModel inputData = new ClientRegisterRequestModel("joseph", "test@gmail.com", PASSWORD, PASSWORD, "test state", "abcdefg", "test ethinicity", 18, "male", "single", 1, 1000.0F);

        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    public void ClearAllRepository(){
        clientGateway = new ClientRepository();
        clientGateway.deleteAll();;
    }
}
