package usecasetesting;

import adapter.controller.ControlContainer;
import businessrule.gateway.ClientGateway;
import businessrule.inputboundary.ClientRegisterInputBoundary;
import businessrule.inputboundary.UserRegisterInputBoundary;
import businessrule.outputboundary.RegisterOutputBoundary;
import businessrule.requestmodel.ClientRegisterRequestModel;
import businessrule.requestmodel.RegistrationData;
import businessrule.responsemodel.RegisterResponseModel;
import businessrule.usecase.ClientRegisterInteractor;
import driver.database.ClientRepository;
import entity.factory.ClientFactory;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.fail;
;

public class ClientRegisterUseCaseTest {
    final static String PASSWORD = "abcdefghi";
    final static int USER_ID = 11234567;
    final static String USER_NAME = "joseph";
    final static String EMAIL = "test@gmail.com";
    final static String STATE_ABB = "ca";
    final static String POSTALCODE = "21500";
    final String ETHNICITY = "test ethnicity";
    final int AGE = 30;
    final static String GENDER = "male";
    final static String MARITAL_STATUS = "single";
    final static int NUMBER_OF_HOUSEHOLD = 1;
    final static float ANNUAL_INCOME = 1000.0f;
    final static String PASSWORD2 = "abcdefghif";

    private ClientFactory clientFactory;
    private ClientGateway clientGateway;
    private RegisterOutputBoundary registerOutputBoundary;
    private UserRegisterInputBoundary clientRegisterInputBoundary;
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

        clientRegisterInputBoundary = new ClientRegisterInteractor(clientGateway, clientFactory, registerOutputBoundary);

    }
    @Test
    public void TestSuccessfulRegistration(){
        setUpClientRegisterUseCase();

        RegistrationData inputData = new RegistrationData(USER_NAME, EMAIL, PASSWORD, PASSWORD2, STATE_ABB, POSTALCODE, ETHNICITY, AGE, GENDER, MARITAL_STATUS, NUMBER_OF_HOUSEHOLD, ANNUAL_INCOME);

        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    @Test
    public void TestRegistrationFailByAlreadyExists(){
        setUpClientRegisterUseCase();

        RegistrationData inputData = new RegistrationData(USER_NAME, EMAIL, PASSWORD, PASSWORD2, STATE_ABB, POSTALCODE, ETHNICITY, AGE, GENDER, MARITAL_STATUS, NUMBER_OF_HOUSEHOLD, ANNUAL_INCOME);
        clientRegisterInputBoundary.create(inputData);
        clientRegisterInputBoundary.create(inputData);

        ClearAllRepository();
    }

    @Test
    public void TestRegistrationFailByPasswordDoesNotMatch(){
        setUpClientRegisterUseCase();

        RegistrationData inputData = new RegistrationData(USER_NAME, EMAIL, PASSWORD, "a", STATE_ABB, POSTALCODE, ETHNICITY, AGE, GENDER, MARITAL_STATUS, NUMBER_OF_HOUSEHOLD, ANNUAL_INCOME);
        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }
    @Test
    public void TestRegistrationFailByPasswordLengthTooSmall(){
        setUpClientRegisterUseCase();

        RegistrationData inputData = new RegistrationData(USER_NAME, EMAIL, "a", "a", STATE_ABB, POSTALCODE, ETHNICITY, AGE, GENDER, MARITAL_STATUS, NUMBER_OF_HOUSEHOLD, ANNUAL_INCOME);

        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    @Test
    public void TestRegistrationFailByInvalidEmail(){
        setUpClientRegisterUseCase();

        RegistrationData inputData = new RegistrationData(USER_NAME, "abcd", PASSWORD, PASSWORD2, STATE_ABB, POSTALCODE, ETHNICITY, AGE, GENDER, MARITAL_STATUS, NUMBER_OF_HOUSEHOLD, ANNUAL_INCOME);

        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    @Test
    public void TestRegistrationFailByInvalidAge(){
        setUpClientRegisterUseCase();

        RegistrationData inputData = new RegistrationData(USER_NAME, EMAIL, PASSWORD, PASSWORD2, STATE_ABB, POSTALCODE, ETHNICITY, 300, GENDER, MARITAL_STATUS, NUMBER_OF_HOUSEHOLD, ANNUAL_INCOME);

        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    @Test
    public void TestRegistrationFailByInvalidPostalCode(){
        setUpClientRegisterUseCase();

        RegistrationData inputData = new RegistrationData(USER_NAME, EMAIL, PASSWORD, PASSWORD2, STATE_ABB, "test code", ETHNICITY, AGE, GENDER, MARITAL_STATUS, NUMBER_OF_HOUSEHOLD, ANNUAL_INCOME);

        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    public void ClearAllRepository(){
        clientGateway = new ClientRepository();
        clientGateway.deleteAll();;
    }
}
