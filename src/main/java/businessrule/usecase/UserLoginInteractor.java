package businessrule.usecase;

import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.UserLoginInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.UserLoginRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.gateway.UserGateway;
import entity.User;
import driver.screen.ApplicationException;

import java.time.LocalDateTime;

/**
 * This class represents the interactor for the user login use case.
 */
public class UserLoginInteractor implements UserLoginInputBoundary {

    private final UserGatewayFactory userGatewayFactory;
    private final HomePageOutputBoundary outputBoundary;

    /**
     * Constructs a UserLoginInteractor with the given dependencies.
     *
     * @param userGatewayFactory Factory to create UserGateway instances.
     * @param outputBoundary     Output boundary for the home page.
     */
    public UserLoginInteractor(UserGatewayFactory userGatewayFactory, HomePageOutputBoundary outputBoundary) {
        this.userGatewayFactory = userGatewayFactory;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Attempts to log in a user using the provided login credentials.
     *
     * @param requestModel The login request model containing user credentials.
     * @return A response model indicating the login status and user information.
     */
    @Override
    public HomePageResponseModel login(UserLoginRequestModel requestModel) {
        // Get input data
        int inputUserId = requestModel.getUserId();
        String inputPassword = requestModel.getPassword();

        // Use user gateway factory to retrieve the correct type of repository
        UserGateway userGateway;
        try {
            userGateway = userGatewayFactory.createUserGateway(inputUserId);
        } catch (ApplicationException e) {
            return outputBoundary.prepareFail("User ID does not exist");
        }

        // Handle login logic
        if (!userGateway.existsById(inputUserId)) {
            return outputBoundary.prepareFail("User ID does not exist");
        }
        User filedUser = userGateway.getUser(inputUserId);
        String filedPassword = filedUser.getPassword();
        if (!inputPassword.equals(filedPassword)) {
            return outputBoundary.prepareFail("Password is incorrect");
        }

        // Construct response model
        String userType;
        User user = userGateway.getUser(inputUserId);
        if (user.isClient()) {
            userType = "Client";
        } else {
            userType = "Attorney";
        }
        HomePageResponseModel accountResponseModel = new HomePageResponseModel(inputUserId,
                userGateway.getUser(inputUserId).getUserName(), userType);
        return outputBoundary.prepareSuccess(accountResponseModel);
    }
}