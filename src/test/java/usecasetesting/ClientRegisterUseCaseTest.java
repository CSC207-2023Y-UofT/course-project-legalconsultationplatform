package usecasetesting;

import businessrule.gateway.ClientGateway;
import businessrule.inputboundary.UserRegisterInputBoundary;
import businessrule.outputboundary.BaseOutputBoundary;
import businessrule.requestmodel.RegistrationData;
import businessrule.responsemodel.BaseResponseModel;
import businessrule.usecase.ClientRegisterInteractor;
import driver.database.ClientRepository;
import entity.factory.ClientFactory;
import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests for the ClientRegisterUseCase class.
 */
public class ClientRegisterUseCaseTest {
    final static String PASSWORD = "abcdefghi";
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

    private ClientGateway clientGateway;
    private UserRegisterInputBoundary clientRegisterInputBoundary;

    /**
     * Set up the test environment by initializing the ClientRegisterUseCase instance.
     */
    public void setUpClientRegisterUseCase(){
        clientGateway = new ClientRepository();
        ClientFactory clientFactory = new ClientFactory();
        BaseOutputBoundary baseOutputBoundary = new BaseOutputBoundary() {

            @Override
            public BaseResponseModel prepareSuccess(String msg) {
                System.out.println(msg);
                System.out.println("Registration is approved.");
                return null;
            }

            @Override
            public BaseResponseModel prepareFail(String msg) {
                System.out.println(msg);
                return null;
            }
        };

        clientRegisterInputBoundary = new ClientRegisterInteractor(clientGateway, clientFactory, baseOutputBoundary);

    }

    /**
     * Test the successful registration scenario.
     */
    @Test
    public void TestSuccessfulRegistration(){
        setUpClientRegisterUseCase();

        RegistrationData inputData = new RegistrationData(USER_NAME, EMAIL, PASSWORD, PASSWORD2, STATE_ABB, POSTALCODE, ETHNICITY, AGE, GENDER, MARITAL_STATUS, NUMBER_OF_HOUSEHOLD, ANNUAL_INCOME);

        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    /**
     * Test the registration failure scenario due to an existing user.
     */
    @Test
    public void TestRegistrationFailByAlreadyExists(){
        setUpClientRegisterUseCase();

        RegistrationData inputData = new RegistrationData(USER_NAME, EMAIL, PASSWORD, PASSWORD2, STATE_ABB, POSTALCODE, ETHNICITY, AGE, GENDER, MARITAL_STATUS, NUMBER_OF_HOUSEHOLD, ANNUAL_INCOME);
        clientRegisterInputBoundary.create(inputData);
        clientRegisterInputBoundary.create(inputData);

        ClearAllRepository();
    }

    /**
     * Test registration failure due to password not matching.
     */
    @Test
    public void TestRegistrationFailByPasswordDoesNotMatch(){
        setUpClientRegisterUseCase();

        RegistrationData inputData = new RegistrationData(USER_NAME, EMAIL, PASSWORD, "a", STATE_ABB, POSTALCODE, ETHNICITY, AGE, GENDER, MARITAL_STATUS, NUMBER_OF_HOUSEHOLD, ANNUAL_INCOME);
        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    /**
     * Test registration failure due to password length being too small.
     */
    @Test
    public void TestRegistrationFailByPasswordLengthTooSmall(){
        setUpClientRegisterUseCase();

        RegistrationData inputData = new RegistrationData(USER_NAME, EMAIL, "a", "a", STATE_ABB, POSTALCODE, ETHNICITY, AGE, GENDER, MARITAL_STATUS, NUMBER_OF_HOUSEHOLD, ANNUAL_INCOME);

        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    /**
     * Test registration failure due to invalid email format.
     */
    @Test
    public void TestRegistrationFailByInvalidEmail(){
        setUpClientRegisterUseCase();

        RegistrationData inputData = new RegistrationData(USER_NAME, "abcd", PASSWORD, PASSWORD2, STATE_ABB, POSTALCODE, ETHNICITY, AGE, GENDER, MARITAL_STATUS, NUMBER_OF_HOUSEHOLD, ANNUAL_INCOME);

        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    /**
     * Test registration failure due to invalid age.
     */
    @Test
    public void TestRegistrationFailByInvalidAge(){
        setUpClientRegisterUseCase();

        RegistrationData inputData = new RegistrationData(USER_NAME, EMAIL, PASSWORD, PASSWORD2, STATE_ABB, POSTALCODE, ETHNICITY, 300, GENDER, MARITAL_STATUS, NUMBER_OF_HOUSEHOLD, ANNUAL_INCOME);

        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    /**
     * Test registration failure due to invalid postal code.
     */
    @Test
    public void TestRegistrationFailByInvalidPostalCode(){
        setUpClientRegisterUseCase();

        RegistrationData inputData = new RegistrationData(USER_NAME, EMAIL, PASSWORD, PASSWORD2, STATE_ABB, "test code", ETHNICITY, AGE, GENDER, MARITAL_STATUS, NUMBER_OF_HOUSEHOLD, ANNUAL_INCOME);

        clientRegisterInputBoundary.create(inputData);
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
