package usecasetesting;

import usecases.gateway.ClientGateway;
import usecases.inputboundary.UserRegisterInputBoundary;
import usecases.outputboundary.BaseOutputBoundary;
import usecases.requests.ClientRegistrationData;
import usecases.requests.RegistrationData;
import usecases.responses.BaseResponseModel;
import usecases.interactors.ClientRegisterInteractor;
import infrastructure.database.ClientRepository;
import entities.factories.ClientFactory;
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

        clientRegisterInputBoundary = new ClientRegisterInteractor(clientGateway, baseOutputBoundary, clientFactory);

    }

    /**
     * Test the successful registration scenario.
     */
    @Test
    public void TestSuccessfulRegistration(){
        setUpClientRegisterUseCase();

        RegistrationData data = new RegistrationData(USER_NAME, EMAIL, PASSWORD, PASSWORD2, STATE_ABB, POSTALCODE);
        ClientRegistrationData inputData = new ClientRegistrationData.Builder(data)
                                                                    .age(AGE)
                                                                    .annualIncome(ANNUAL_INCOME)
                                                                    .gender(GENDER)
                                                                    .maritalStatus(MARITAL_STATUS)
                                                                    .numberOfHousehold(NUMBER_OF_HOUSEHOLD)
                                                                    .ethnicity(ETHNICITY)
                                                                    .build();

        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    /**
     * Test the registration failure scenario due to an existing user.
     */
    @Test
    public void TestRegistrationFailByAlreadyExists(){
        setUpClientRegisterUseCase();

        RegistrationData data = new RegistrationData(USER_NAME, EMAIL, PASSWORD, PASSWORD2, STATE_ABB, POSTALCODE);
        ClientRegistrationData inputData = new ClientRegistrationData.Builder(data)
                .age(AGE)
                .annualIncome(ANNUAL_INCOME)
                .gender(GENDER)
                .maritalStatus(MARITAL_STATUS)
                .numberOfHousehold(NUMBER_OF_HOUSEHOLD)
                .ethnicity(ETHNICITY)
                .build();
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

        RegistrationData data = new RegistrationData(USER_NAME, EMAIL, PASSWORD, "a", STATE_ABB, POSTALCODE);
        ClientRegistrationData inputData = new ClientRegistrationData.Builder(data)
                .age(AGE)
                .annualIncome(ANNUAL_INCOME)
                .gender(GENDER)
                .maritalStatus(MARITAL_STATUS)
                .numberOfHousehold(NUMBER_OF_HOUSEHOLD)
                .ethnicity(ETHNICITY)
                .build();
        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    /**
     * Test registration failure due to password length being too small.
     */
    @Test
    public void TestRegistrationFailByPasswordLengthTooSmall(){
        setUpClientRegisterUseCase();

        RegistrationData data = new RegistrationData(USER_NAME, EMAIL, "a", "a", STATE_ABB, POSTALCODE);
        ClientRegistrationData inputData = new ClientRegistrationData.Builder(data)
                .age(AGE)
                .annualIncome(ANNUAL_INCOME)
                .gender(GENDER)
                .maritalStatus(MARITAL_STATUS)
                .numberOfHousehold(NUMBER_OF_HOUSEHOLD)
                .ethnicity(ETHNICITY)
                .build();

        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    /**
     * Test registration failure due to invalid email format.
     */
    @Test
    public void TestRegistrationFailByInvalidEmail(){
        setUpClientRegisterUseCase();

        RegistrationData data = new RegistrationData(USER_NAME, "abcd", PASSWORD, PASSWORD2, STATE_ABB, POSTALCODE);
        ClientRegistrationData inputData = new ClientRegistrationData.Builder(data)
                .age(AGE)
                .annualIncome(ANNUAL_INCOME)
                .gender(GENDER)
                .maritalStatus(MARITAL_STATUS)
                .numberOfHousehold(NUMBER_OF_HOUSEHOLD)
                .ethnicity(ETHNICITY)
                .build();
        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    /**
     * Test registration failure due to invalid age.
     */
    @Test
    public void TestRegistrationFailByInvalidAge(){
        setUpClientRegisterUseCase();

        RegistrationData data = new RegistrationData(USER_NAME, EMAIL, PASSWORD, PASSWORD2, STATE_ABB, POSTALCODE);
        ClientRegistrationData inputData = new ClientRegistrationData.Builder(data)
                .age(300)
                .annualIncome(ANNUAL_INCOME)
                .gender(GENDER)
                .maritalStatus(MARITAL_STATUS)
                .numberOfHousehold(NUMBER_OF_HOUSEHOLD)
                .ethnicity(ETHNICITY)
                .build();

        clientRegisterInputBoundary.create(inputData);
        ClearAllRepository();
    }

    /**
     * Test registration failure due to invalid postal code.
     */
    @Test
    public void TestRegistrationFailByInvalidPostalCode(){
        setUpClientRegisterUseCase();

        RegistrationData data = new RegistrationData(USER_NAME, EMAIL, PASSWORD, PASSWORD2, STATE_ABB, "test code");
        ClientRegistrationData inputData = new ClientRegistrationData.Builder(data)
                .age(AGE)
                .annualIncome(ANNUAL_INCOME)
                .gender(GENDER)
                .maritalStatus(MARITAL_STATUS)
                .numberOfHousehold(NUMBER_OF_HOUSEHOLD)
                .ethnicity(ETHNICITY)
                .build();

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
