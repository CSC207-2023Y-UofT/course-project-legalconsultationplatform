package businessrule.usecase;

import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.UserLoginInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.UserLoginRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.gateway.UserGateway;
import entity.User;
import entity.ApplicationException;

/**
 * This class represents the interactor responsible for handling user login.
 *
 * This interactor handles the process of authenticating user login credentials,retrieving user information,
 * and preparing the appropriate response model.
 */
public class UserLoginInteractor implements UserLoginInputBoundary {

    final UserGatewayFactory userGatewayFactory;
    final HomePageOutputBoundary outputBoundary;

    /**
     * Constructor for UserLoginInteractor.
     *
     * @param userGatewayFactory The factory for creating user gateways.
     * @param outputBoundary The output boundary for preparing home page response models.
     */
    public UserLoginInteractor(UserGatewayFactory userGatewayFactory, HomePageOutputBoundary outputBoundary) {
        this.userGatewayFactory = userGatewayFactory;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Authenticate user login and prepare the response model.
     *
     * @param requestModel The request model containing user login details.
     * @return The response model for the home page.
     */
    @Override
    public HomePageResponseModel login(UserLoginRequestModel requestModel) {
        int inputUserId = requestModel.getUserId();
        String inputPassword = requestModel.getPassword();

        UserGateway userGateway;
        try {
            userGateway = userGatewayFactory.createUserGateway(inputUserId);
        } catch (ApplicationException e) {
            return outputBoundary.prepareFail("User ID does not exist");
        }

        if (!userGateway.existsById(inputUserId)) {
            return outputBoundary.prepareFail("User ID does not exist");
        }
        User filedUser = userGateway.get(inputUserId);
        String filedPassword = filedUser.getPassword();
        if (!inputPassword.equals(filedPassword)) {
            return outputBoundary.prepareFail("Password is incorrect");
        }

        String userType;
        User user = userGateway.get(inputUserId);
        if (user.isClient()) {
            userType = "Client";
        } else {
            userType = "Attorney";
        }
        HomePageResponseModel accountResponseModel = new HomePageResponseModel(inputUserId,
                userGateway.get(inputUserId).getUserName(), userType);
        return outputBoundary.prepareSuccess(accountResponseModel);
    }
}